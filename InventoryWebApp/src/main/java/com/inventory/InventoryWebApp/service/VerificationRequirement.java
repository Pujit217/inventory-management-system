package com.inventory.InventoryWebApp.service;

public class VerificationRequirement {

    private int componentId;
    private String componentName;
    private int requiredQuantity;
    private int availableQuantity;
    private int remainingQuantity;
    private String status;

    public VerificationRequirement(int componentId,
                                   String componentName,
                                   int requiredQuantity,
                                   int availableQuantity,
                                   int remainingQuantity,
                                   String status) {
        this.componentId = componentId;
        this.componentName = componentName;
        this.requiredQuantity = requiredQuantity;
        this.availableQuantity = availableQuantity;
        this.remainingQuantity = remainingQuantity;
        this.status = status;
    }

    public int getComponentId() {
        return componentId;
    }

    public String getComponentName() {
        return componentName;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    public String getStatus() {
        return status;
    }
}