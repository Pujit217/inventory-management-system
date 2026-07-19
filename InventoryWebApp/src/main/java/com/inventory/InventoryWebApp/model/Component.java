package com.inventory.InventoryWebApp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents an inventory component stored in the system.
 *
 * A component contains identification, stock, manufacturer,
 * location, and supplier information used for inventory
 * and production planning.
 *
 * @author Pujit Varma Muppala
 */
@Entity
public class Component {

    /**
     * Unique identifier for the component.
     */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int componentId;

    /**
     * Category assigned to the component.
     */
    private String category;

    /**
     * Item number associated with the component.
     */
    private int item;

    /**
     * Current quantity available in inventory.
     */
    private int quantity;

    /**
     * List of schematic or design references for the component.
     */
    @Column(length = 2000)
    private String referenceList;

    /**
     * Component part name or value.
     */
    private String part;

    /**
     * Printed circuit board footprint used by the component.
     */
    private String pcbFootprint;

    /**
     * Name of the component manufacturer.
     */
    private String manufacturer;

    /**
     * Manufacturer part number.
     */
    private String manufacturerPn;

    /**
     * Detailed description of the component.
     */
    @Column(length = 3000)
    private String description;

    /**
     * Additional stock information for the component.
     */
    @Column(length = 3000)
    private String stockDetails;

    /**
     * List of storage locations for the component.
     */
    @Column(length = 2000)
    private String locationList;

    /**
     * Minimum quantity allowed before the component is considered low stock.
     */
    private int lowStockLimit;

    /**
     * Supplier associated with the component.
     */
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    /**
     * Creates an empty component.
     *
     * This constructor is required by JPA.
     */
    public Component() {
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
     * Sets the component ID.
     *
     * @param componentId component ID
     */
    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    /**
     * Returns the component category.
     *
     * @return component category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the component category.
     *
     * @param category component category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Returns the item number.
     *
     * @return item number
     */
    public int getItem() {
        return item;
    }

    /**
     * Sets the item number.
     *
     * @param item item number
     */
    public void setItem(int item) {
        this.item = item;
    }

    /**
     * Returns the current inventory quantity.
     *
     * @return available quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the current inventory quantity.
     *
     * @param quantity available quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns the component reference list.
     *
     * @return reference list
     */
    public String getReferenceList() {
        return referenceList;
    }

    /**
     * Sets the component reference list.
     *
     * @param referenceList reference list
     */
    public void setReferenceList(String referenceList) {
        this.referenceList = referenceList;
    }

    /**
     * Returns the component part name.
     *
     * @return part name
     */
    public String getPart() {
        return part;
    }

    /**
     * Sets the component part name.
     *
     * @param part part name
     */
    public void setPart(String part) {
        this.part = part;
    }

    /**
     * Returns the PCB footprint.
     *
     * @return PCB footprint
     */
    public String getPcbFootprint() {
        return pcbFootprint;
    }

    /**
     * Sets the PCB footprint.
     *
     * @param pcbFootprint PCB footprint
     */
    public void setPcbFootprint(String pcbFootprint) {
        this.pcbFootprint = pcbFootprint;
    }

    /**
     * Returns the manufacturer name.
     *
     * @return manufacturer name
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the manufacturer name.
     *
     * @param manufacturer manufacturer name
     */
    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    /**
     * Returns the manufacturer part number.
     *
     * @return manufacturer part number
     */
    public String getManufacturerPn() {
        return manufacturerPn;
    }

    /**
     * Sets the manufacturer part number.
     *
     * @param manufacturerPn manufacturer part number
     */
    public void setManufacturerPn(String manufacturerPn) {
        this.manufacturerPn = manufacturerPn;
    }

    /**
     * Returns the component description.
     *
     * @return component description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the component description.
     *
     * @param description component description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the stock details.
     *
     * @return stock details
     */
    public String getStockDetails() {
        return stockDetails;
    }

    /**
     * Sets the stock details.
     *
     * @param stockDetails stock details
     */
    public void setStockDetails(String stockDetails) {
        this.stockDetails = stockDetails;
    }

    /**
     * Returns the component location list.
     *
     * @return location list
     */
    public String getLocationList() {
        return locationList;
    }

    /**
     * Sets the component location list.
     *
     * @param locationList location list
     */
    public void setLocationList(String locationList) {
        this.locationList = locationList;
    }

    /**
     * Returns the low-stock limit.
     *
     * @return low-stock limit
     */
    public int getLowStockLimit() {
        return lowStockLimit;
    }

    /**
     * Sets the low-stock limit.
     *
     * @param lowStockLimit low-stock limit
     */
    public void setLowStockLimit(int lowStockLimit) {
        this.lowStockLimit = lowStockLimit;
    }

    /**
     * Returns the component supplier.
     *
     * @return component supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Sets the component supplier.
     *
     * @param supplier component supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * Determines whether the component quantity is below
     * the configured low-stock limit.
     *
     * @return true when the quantity is below the low-stock limit;
     *         false otherwise
     */
    public boolean isLowStock() {
        return quantity < lowStockLimit;
    }
}