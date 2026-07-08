package com.inventory.InventoryWebApp.service;

public class ProductionRequirement {

    private String componentName;
    private int quantityPerProduct;
    private int requiredQuantity;
    private int availableQuantity;
    private int shortageQuantity;
    private double costPerComponent;
    private double totalCost;
    private String status;

    public ProductionRequirement(String componentName,
                                 int quantityPerProduct,
                                 int requiredQuantity,
                                 int availableQuantity,
                                 int shortageQuantity,
                                 double costPerComponent,
                                 double totalCost,
                                 String status) {
        this.componentName = componentName;
        this.quantityPerProduct = quantityPerProduct;
        this.requiredQuantity = requiredQuantity;
        this.availableQuantity = availableQuantity;
        this.shortageQuantity = shortageQuantity;
        this.costPerComponent = costPerComponent;
        this.totalCost = totalCost;
        this.status = status;
    }

    public String getComponentName() {
        return componentName;
    }

    public int getQuantityPerProduct() {
        return quantityPerProduct;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public int getShortageQuantity() {
        return shortageQuantity;
    }

    public double getCostPerComponent() {
        return costPerComponent;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }
}