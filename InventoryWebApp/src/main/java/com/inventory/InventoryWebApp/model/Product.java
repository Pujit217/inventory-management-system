package com.inventory.InventoryWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents a product that can be manufactured by the system.
 *
 * A product contains identifying information, a description,
 * and its current product status.
 *
 * @author Pujit Varma Muppala
 */
@Entity
public class Product {

    /**
     * Unique identifier for the product.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    /**
     * Name of the product.
     */
    private String productName;

    /**
     * Description of the product.
     */
    private String description;

    /**
     * Current status of the product.
     */
    private String status;

    /**
     * Creates an empty product.
     *
     * This constructor is required by JPA.
     */
    public Product() {
    }

    /**
     * Creates a product with the provided information.
     *
     * @param productName product name
     * @param description product description
     * @param status product status
     */
    public Product(String productName,
                   String description,
                   String status) {

        this.productName = productName;
        this.description = description;
        this.status = status;
    }

    /**
     * Returns the product ID.
     *
     * @return product ID
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Returns the product name.
     *
     * @return product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Returns the product description.
     *
     * @return product description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the product status.
     *
     * @return product status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the product ID.
     *
     * @param productId product ID
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Sets the product name.
     *
     * @param productName product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * Sets the product description.
     *
     * @param description product description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the product status.
     *
     * @param status product status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}