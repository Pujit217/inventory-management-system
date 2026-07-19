package com.inventory.InventoryWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents one item in a product's Bill of Materials.
 *
 * A BOM item connects a product to an inventory component and stores
 * the quantity and cost of that component required to manufacture
 * one unit of the product.
 *
 * @author Pujit Varma Muppala
 */
@Entity
public class BomItem {

    /**
     * Unique identifier for the BOM item.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bomId;

    /**
     * Product that uses this BOM item.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Inventory component required by the product.
     */
    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    /**
     * Number of component units required to manufacture one product.
     */
    private int quantityRequired;

    /**
     * Cost of one unit of the component.
     */
    private double costPerComponent;

    /**
     * Creates an empty BOM item.
     *
     * This constructor is required by JPA.
     */
    public BomItem() {
    }

    /**
     * Returns the BOM item ID.
     *
     * @return BOM item ID
     */
    public int getBomId() {
        return bomId;
    }

    /**
     * Sets the BOM item ID.
     *
     * @param bomId BOM item ID
     */
    public void setBomId(int bomId) {
        this.bomId = bomId;
    }

    /**
     * Returns the product associated with this BOM item.
     *
     * @return associated product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with this BOM item.
     *
     * @param product product that uses the component
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the component associated with this BOM item.
     *
     * @return associated component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * Sets the component associated with this BOM item.
     *
     * @param component component required by the product
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * Returns the component quantity required per product.
     *
     * @return required quantity
     */
    public int getQuantityRequired() {
        return quantityRequired;
    }

    /**
     * Sets the component quantity required per product.
     *
     * @param quantityRequired required quantity
     */
    public void setQuantityRequired(int quantityRequired) {
        this.quantityRequired = quantityRequired;
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
     * Sets the cost of one component unit.
     *
     * @param costPerComponent cost per component
     */
    public void setCostPerComponent(double costPerComponent) {
        this.costPerComponent = costPerComponent;
    }
}