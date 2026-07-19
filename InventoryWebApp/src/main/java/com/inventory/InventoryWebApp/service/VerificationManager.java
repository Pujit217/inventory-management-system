package com.inventory.InventoryWebApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.InventoryWebApp.model.BomItem;
import com.inventory.InventoryWebApp.model.Component;
import com.inventory.InventoryWebApp.model.ProductionPlan;
import com.inventory.InventoryWebApp.repository.BomItemRepository;
import com.inventory.InventoryWebApp.repository.ComponentRepository;
import com.inventory.InventoryWebApp.repository.ProductionPlanRepository;

/**
 * Provides production verification and inventory deduction operations.
 *
 * This service verifies that sufficient inventory is available for
 * production, deducts the required component quantities, and updates
 * the production plan status.
 *
 * @author Pujit Varma Muppala
 */
@Service
public class VerificationManager {

    private static final String PLANNED_STATUS = "Planned";

    private static final String CURRENT_PRODUCTION_STATUS =
            "Current Production";

    private final BomItemRepository bomItemRepository;
    private final ComponentRepository componentRepository;
    private final ProductionPlanRepository productionPlanRepository;

    /**
     * Creates a VerificationManager with the required repositories.
     *
     * @param bomItemRepository repository used to retrieve BOM items
     * @param componentRepository repository used to update components
     * @param productionPlanRepository repository used to update plans
     */
    public VerificationManager(
            BomItemRepository bomItemRepository,
            ComponentRepository componentRepository,
            ProductionPlanRepository productionPlanRepository) {

        this.bomItemRepository = bomItemRepository;
        this.componentRepository = componentRepository;
        this.productionPlanRepository = productionPlanRepository;
    }

    /**
     * Verifies the inventory requirements for a production plan.
     *
     * Each verification row contains the required quantity,
     * available quantity, remaining quantity, and stock status
     * for one BOM component.
     *
     * @param plan production plan to verify
     * @return list of verification requirements
     */
    public List<VerificationRequirement> verifyPlan(
            ProductionPlan plan) {

        List<VerificationRequirement> results =
                new ArrayList<>();

        List<BomItem> bomItems =
                bomItemRepository.findByProduct(plan.getProduct());

        for (BomItem bomItem : bomItems) {
            Component component = bomItem.getComponent();

            int requiredQuantity =
                    bomItem.getQuantityRequired()
                    * plan.getProductionQuantity();

            int availableQuantity = component.getQuantity();

            int remainingQuantity =
                    availableQuantity - requiredQuantity;

            String status = "Sufficient";

            if (remainingQuantity < 0) {
                status = "Insufficient";
            }

            VerificationRequirement requirement =
                    new VerificationRequirement(
                            component.getComponentId(),
                            component.getPart(),
                            requiredQuantity,
                            availableQuantity,
                            remainingQuantity,
                            status
                    );

            results.add(requirement);
        }

        return results;
    }

    /**
     * Determines whether sufficient inventory exists for every
     * component required by a production plan.
     *
     * @param plan production plan to verify
     * @return true when every component has sufficient inventory;
     *         false otherwise
     */
    public boolean canProceed(ProductionPlan plan) {
        List<VerificationRequirement> requirements =
                verifyPlan(plan);

        for (VerificationRequirement requirement : requirements) {
            if ("Insufficient".equals(
                    requirement.getStatus())) {

                return false;
            }
        }

        return true;
    }

    /**
     * Starts production for a verified production plan.
     *
     * The method deducts the required inventory quantities and
     * changes the production plan status from Planned to
     * Current Production.
     *
     * Production is rejected when the plan is null, is not Planned,
     * or does not have sufficient inventory.
     *
     * @param plan production plan to start
     * @return true when production starts successfully;
     *         false otherwise
     */
    public boolean proceedProduction(ProductionPlan plan) {
        if (plan == null) {
            return false;
        }

        if (!PLANNED_STATUS.equalsIgnoreCase(plan.getStatus())) {
            return false;
        }

        if (!canProceed(plan)) {
            return false;
        }

        List<BomItem> bomItems =
                bomItemRepository.findByProduct(plan.getProduct());

        for (BomItem bomItem : bomItems) {
            Component component = bomItem.getComponent();

            int requiredQuantity =
                    bomItem.getQuantityRequired()
                    * plan.getProductionQuantity();

            int updatedQuantity =
                    component.getQuantity() - requiredQuantity;

            component.setQuantity(updatedQuantity);
            componentRepository.save(component);
        }

        plan.setStatus(CURRENT_PRODUCTION_STATUS);
        productionPlanRepository.save(plan);

        return true;
    }
}