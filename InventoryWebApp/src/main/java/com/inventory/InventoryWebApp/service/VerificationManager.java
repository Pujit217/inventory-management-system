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

@Service
public class VerificationManager {

    private BomItemRepository bomItemRepository;
    private ComponentRepository componentRepository;
    private ProductionPlanRepository productionPlanRepository;
    
    public VerificationManager(BomItemRepository bomItemRepository,
            				   ComponentRepository componentRepository,
            				   ProductionPlanRepository productionPlanRepository) {
    			this.bomItemRepository = bomItemRepository;
    			this.componentRepository = componentRepository;
    			this.productionPlanRepository = productionPlanRepository;
    }

    public List<VerificationRequirement> verifyPlan(ProductionPlan plan) {
        List<VerificationRequirement> result = new ArrayList<>();

        List<BomItem> bomItems = bomItemRepository.findByProduct(plan.getProduct());

        for (BomItem bomItem : bomItems) {
            Component component = bomItem.getComponent();

            int requiredQuantity =
                    bomItem.getQuantityRequired() * plan.getProductionQuantity();

            int availableQuantity = component.getQuantity();
            int remainingQuantity = availableQuantity - requiredQuantity;

            String status = "Sufficient";

            if (remainingQuantity < 0) {
                status = "Insufficient";
            }

            VerificationRequirement row = new VerificationRequirement(
                    component.getComponentId(),
                    component.getPart(),
                    requiredQuantity,
                    availableQuantity,
                    remainingQuantity,
                    status
            );

            result.add(row);
        }

        return result;
    }

    public boolean canProceed(ProductionPlan plan) {
        List<VerificationRequirement> result = verifyPlan(plan);

        for (VerificationRequirement row : result) {
            if (row.getStatus().equals("Insufficient")) {
                return false;
            }
        }

        return true;
    }

    public void proceedProduction(ProductionPlan plan) {
        if (!canProceed(plan)) {
            return;
        }

        List<BomItem> bomItems = bomItemRepository.findByProduct(plan.getProduct());

        for (BomItem bomItem : bomItems) {
            Component component = bomItem.getComponent();

            int requiredQuantity =
                    bomItem.getQuantityRequired() * plan.getProductionQuantity();

            component.setQuantity(component.getQuantity() - requiredQuantity);

            componentRepository.save(component);
        }

        plan.setStatus("Inventory Verified");
        productionPlanRepository.save(plan);
    }
}