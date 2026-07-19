package com.inventory.InventoryWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents a production plan for manufacturing a product.
 *
 * A production plan stores the selected product, the number of units
 * to manufacture, and the current production status.
 *
 * @author Pujit Varma Muppala
 */
@Entity
public class ProductionPlan {

    /**
     * Unique identifier for the production plan.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;

    /**
     * Product included in the production plan.
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /**
     * Number of product units scheduled for production.
     */
    private int productionQuantity;

    /**
     * Current status of the production plan.
     */
    private String status;

    /**
     * Creates an empty production plan.
     *
     * This constructor is required by JPA.
     */
    public ProductionPlan() {
    }

    /**
     * Returns the production plan ID.
     *
     * @return production plan ID
     */
    public int getPlanId() {
        return planId;
    }

    /**
     * Sets the production plan ID.
     *
     * @param planId production plan ID
     */
    public void setPlanId(int planId) {
        this.planId = planId;
    }

    /**
     * Returns the product associated with the production plan.
     *
     * @return planned product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with the production plan.
     *
     * @param product product to manufacture
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Returns the quantity scheduled for production.
     *
     * @return production quantity
     */
    public int getProductionQuantity() {
        return productionQuantity;
    }

    /**
     * Sets the quantity scheduled for production.
     *
     * @param productionQuantity production quantity
     */
    public void setProductionQuantity(int productionQuantity) {
        this.productionQuantity = productionQuantity;
    }

    /**
     * Returns the production plan status.
     *
     * @return production plan status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the production plan status.
     *
     * @param status production plan status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}