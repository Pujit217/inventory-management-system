package com.inventory.InventoryWebApp.service;

/**
 * Stores the inventory verification result for one component.
 *
 * This class transfers production verification information from
 * the service layer to the controller and view.
 *
 * @author Pujit Varma Muppala
 */
public class VerificationRequirement {

    private final int componentId;
    private final String componentName;
    private final int requiredQuantity;
    private final int availableQuantity;
    private final int remainingQuantity;
    private final String status;

    /**
     * Creates a verification requirement with component and
     * inventory information.
     *
     * @param componentId component ID
     * @param componentName component name
     * @param requiredQuantity quantity required for production
     * @param availableQuantity quantity currently available
     * @param remainingQuantity quantity remaining after production
     * @param status inventory sufficiency status
     */
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

    /**
     * Returns the component ID.
     *
     * @return component ID
     */
    public int getComponentId() {
        return componentId;
    }

    /**
     * Returns the component name.
     *
     * @return component name
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * Returns the quantity required for production.
     *
     * @return required quantity
     */
    public int getRequiredQuantity() {
        return requiredQuantity;
    }

    /**
     * Returns the quantity currently available in inventory.
     *
     * @return available quantity
     */
    public int getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * Returns the expected quantity remaining after production.
     *
     * @return remaining quantity
     */
    public int getRemainingQuantity() {
        return remainingQuantity;
    }

    /**
     * Returns the inventory sufficiency status.
     *
     * @return verification status
     */
    public String getStatus() {
        return status;
    }
}