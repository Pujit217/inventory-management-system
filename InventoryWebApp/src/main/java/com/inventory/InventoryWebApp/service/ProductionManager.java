package com.inventory.InventoryWebApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.InventoryWebApp.model.BomItem;
import com.inventory.InventoryWebApp.model.ProductionPlan;
import com.inventory.InventoryWebApp.repository.BomItemRepository;

@Service
public class ProductionManager {

    private BomItemRepository bomItemRepository;

    public ProductionManager(BomItemRepository bomItemRepository) {
        this.bomItemRepository = bomItemRepository;
    }

    public List<ProductionRequirement> calculateRequirements(ProductionPlan plan) {
        List<ProductionRequirement> requirements = new ArrayList<>();

        List<BomItem> bomItems = bomItemRepository.findByProduct(plan.getProduct());

        for (BomItem bomItem : bomItems) {
            int quantityPerProduct = bomItem.getQuantityRequired();
            int requiredQuantity = quantityPerProduct * plan.getProductionQuantity();
            int availableQuantity = bomItem.getComponent().getQuantity();

            int shortageQuantity = 0;
            String status = "Available";

            if (availableQuantity < requiredQuantity) {
                shortageQuantity = requiredQuantity - availableQuantity;
                status = "Shortage";
            }

            double totalCost = requiredQuantity * bomItem.getCostPerComponent();

            ProductionRequirement requirement = new ProductionRequirement(
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