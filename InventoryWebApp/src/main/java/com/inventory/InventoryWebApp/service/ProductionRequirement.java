package com.inventory.InventoryWebApp.service;

/**
 * Stores the calculated component requirement for a production plan.
 *
 * This class is used to transfer production analysis information
 * from the service layer to the controller and view.
 *
 * @author Pujit Varma Muppala
 */
public class ProductionRequirement {

    private final String componentName;
    private final int quantityPerProduct;
    private final int requiredQuantity;
    private final int availableQuantity;
    private final int shortageQuantity;
    private final double costPerComponent;
    private final double totalCost;
    private final String status;

    /**
     * Creates a production requirement with calculated inventory
     * and cost information.
     *
     * @param componentName name of the required component
     * @param quantityPerProduct component quantity needed per product
     * @param requiredQuantity total component quantity required
     * @param availableQuantity component quantity currently available
     * @param shortageQuantity additional component quantity required
     * @param costPerComponent cost of one component unit
     * @param totalCost total cost of the required component quantity
     * @param status component availability status
     */
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

    /**
     * Returns the component name.
     *
     * @return component name
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * Returns the quantity required for one product.
     *
     * @return quantity required per product
     */
    public int getQuantityPerProduct() {
        return quantityPerProduct;
    }

    /**
     * Returns the total quantity required for production.
     *
     * @return total required quantity
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
     * Returns the additional quantity required when inventory
     * is insufficient.
     *
     * @return shortage quantity
     */
    public int getShortageQuantity() {
        return shortageQuantity;
    }

    /**
     * Returns the cost of one component unit.
     *
     * @return cost per component
     */
    public double getCostPerComponent() {
        return costPerComponent;
    }

    /**
     * Returns the total cost of the required component quantity.
     *
     * @return total component cost
     */
    public double getTotalCost() {
        return totalCost;
    }

    /**
     * Returns the component availability status.
     *
     * @return availability status
     */
    public String getStatus() {
        return status;
    }
}