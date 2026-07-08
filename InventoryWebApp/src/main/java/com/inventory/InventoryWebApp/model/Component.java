package com.inventory.InventoryWebApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Column;

@Entity
public class Component {

    @Id
    private int componentId;

    private String category;
    private int item;
    private int quantity;

    @Column(length = 2000)
    private String referenceList;

    private String part;
    private String pcbFootprint;
    private String manufacturer;
    private String manufacturerPn;

    @Column(length = 3000)
    private String description;

    @Column(length = 3000)
    private String stockDetails;

    @Column(length = 2000)
    private String locationList;

    private int lowStockLimit;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Component() {
    }

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(String referenceList) {
        this.referenceList = referenceList;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getPcbFootprint() {
        return pcbFootprint;
    }

    public void setPcbFootprint(String pcbFootprint) {
        this.pcbFootprint = pcbFootprint;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getManufacturerPn() {
        return manufacturerPn;
    }

    public void setManufacturerPn(String manufacturerPn) {
        this.manufacturerPn = manufacturerPn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStockDetails() {
        return stockDetails;
    }

    public void setStockDetails(String stockDetails) {
        this.stockDetails = stockDetails;
    }

    public String getLocationList() {
        return locationList;
    }

    public void setLocationList(String locationList) {
        this.locationList = locationList;
    }

    public int getLowStockLimit() {
        return lowStockLimit;
    }

    public void setLowStockLimit(int lowStockLimit) {
        this.lowStockLimit = lowStockLimit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public boolean isLowStock() {
        return quantity < lowStockLimit;
    }
}