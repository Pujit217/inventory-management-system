package com.inventory.InventoryWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents a supplier that provides inventory components.
 *
 * A supplier stores contact details and a purchase link used
 * when ordering components.
 *
 * @author Pujit Varma Muppala
 */
@Entity
public class Supplier {

    /**
     * Unique identifier for the supplier.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    /**
     * Name of the supplier.
     */
    private String supplierName;

    /**
     * Supplier contact number.
     */
    private String contactNumber;

    /**
     * Supplier email address.
     */
    private String email;

    /**
     * Link used to purchase components from the supplier.
     */
    private String purchaseLink;

    /**
     * Creates an empty supplier.
     *
     * This constructor is required by JPA.
     */
    public Supplier() {
    }

    /**
     * Creates a supplier with the provided contact information.
     *
     * @param supplierName supplier name
     * @param contactNumber supplier contact number
     * @param email supplier email address
     * @param purchaseLink supplier purchase link
     */
    public Supplier(String supplierName,
                    String contactNumber,
                    String email,
                    String purchaseLink) {

        this.supplierName = supplierName;
        this.contactNumber = contactNumber;
        this.email = email;
        this.purchaseLink = purchaseLink;
    }

    /**
     * Returns the supplier ID.
     *
     * @return supplier ID
     */
    public int getSupplierId() {
        return supplierId;
    }

    /**
     * Returns the supplier name.
     *
     * @return supplier name
     */
    public String getSupplierName() {
        return supplierName;
    }

    /**
     * Returns the supplier contact number.
     *
     * @return supplier contact number
     */
    public String getContactNumber() {
        return contactNumber;
    }

    /**
     * Returns the supplier email address.
     *
     * @return supplier email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the supplier purchase link.
     *
     * @return supplier purchase link
     */
    public String getPurchaseLink() {
        return purchaseLink;
    }

    /**
     * Sets the supplier ID.
     *
     * @param supplierId supplier ID
     */
    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    /**
     * Sets the supplier name.
     *
     * @param supplierName supplier name
     */
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * Sets the supplier contact number.
     *
     * @param contactNumber supplier contact number
     */
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * Sets the supplier email address.
     *
     * @param email supplier email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the supplier purchase link.
     *
     * @param purchaseLink supplier purchase link
     */
    public void setPurchaseLink(String purchaseLink) {
        this.purchaseLink = purchaseLink;
    }
}