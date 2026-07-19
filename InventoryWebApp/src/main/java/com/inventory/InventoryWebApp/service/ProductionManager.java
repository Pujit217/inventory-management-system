package com.inventory.InventoryWebApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.InventoryWebApp.model.BomItem;
import com.inventory.InventoryWebApp.model.ProductionPlan;
import com.inventory.InventoryWebApp.repository.BomItemRepository;

/**
 * Provides production analysis operations.
 *
 * This service calculates component requirements, available inventory,
 * shortages, and estimated costs for a production plan.
 *
 * @author Pujit Varma Muppala
 */
@Service
public class ProductionManager {

    private final BomItemRepository bomItemRepository;

    /**
     * Creates a ProductionManager with the required BOM repository.
     *
     * @param bomItemRepository repository used to retrieve BOM items
     */
    public ProductionManager(BomItemRepository bomItemRepository) {
        this.bomItemRepository = bomItemRepository;
    }

    /**
     * Calculates the component requirements for a production plan.
     *
     * Each requirement contains the quantity needed per product,
     * total required quantity, available inventory, shortage quantity,
     * component cost, total cost, and availability status.
     *
     * @param plan production plan to analyze
     * @return list of calculated production requirements
     */
    public List<ProductionRequirement> calculateRequirements(
            ProductionPlan plan) {

        List<ProductionRequirement> requirements = new ArrayList<>();

        List<BomItem> bomItems =
                bomItemRepository.findByProduct(plan.getProduct());

        for (BomItem bomItem : bomItems) {
            int quantityPerProduct = bomItem.getQuantityRequired();

            int requiredQuantity =
                    quantityPerProduct * plan.getProductionQuantity();

            int availableQuantity =
                    bomItem.getComponent().getQuantity();

            int shortageQuantity = 0;
            String status = "Available";

            if (availableQuantity < requiredQuantity) {
                shortageQuantity =
                        requiredQuantity - availableQuantity;

                status = "Shortage";
            }

            double totalCost =
                    requiredQuantity * bomItem.getCostPerComponent();

            ProductionRequirement requirement =
                    new ProductionRequirement(
                            bomItem.getComponent().getPart(),
                            quantityPerProduct,
                            requiredQuantity,
                            availableQuantity,
                            shortageQuantity,
                            bomItem.getCostPerComponent(),
                            totalCost,
                            status
                    );

            requirements.add(requirement);
        }

        return requirements;
    }
}