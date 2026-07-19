package com.inventory.InventoryWebApp.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.inventory.InventoryWebApp.model.Component;

/**
 * Provides inventory reporting operations.
 *
 * This service gathers inventory statistics, stock information,
 * category totals, and report date information.
 *
 * @author Pujit Varma Muppala
 */
@Service
public class ReportManager {

    private final InventoryManager inventoryManager;

    /**
     * Creates a ReportManager with the required inventory service.
     *
     * @param inventoryManager service used to retrieve inventory data
     */
    public ReportManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    /**
     * Returns the total number of component records.
     *
     * @return total number of components
     */
    public int getTotalComponents() {
        return inventoryManager.getTotalComponents();
    }

    /**
     * Returns the total quantity of all component units.
     *
     * @return total inventory quantity
     */
    public int getTotalQuantity() {
        return inventoryManager.getTotalQuantity();
    }

    /**
     * Returns the number of components currently below
     * their low-stock limits.
     *
     * @return low-stock component count
     */
    public int getLowStockCount() {
        return inventoryManager.getLowStockCount();
    }

    /**
     * Returns all components currently below their
     * low-stock limits.
     *
     * @return list of low-stock components
     */
    public List<Component> getLowStockComponents() {
        return inventoryManager.checkLowStock();
    }

    /**
     * Returns all components stored in inventory.
     *
     * @return list of all components
     */
    public List<Component> getAllComponents() {
        return inventoryManager.getAllComponents();
    }

    /**
     * Counts components belonging to the Passive category.
     *
     * @return number of passive components
     */
    public long getPassiveComponentCount() {
        return inventoryManager.getAllComponents()
                .stream()
                .filter(component ->
                        "Passive".equalsIgnoreCase(
                                component.getCategory()))
                .count();
    }

    /**
     * Counts components belonging to the Active category.
     *
     * @return number of active components
     */
    public long getActiveComponentCount() {
        return inventoryManager.getAllComponents()
                .stream()
                .filter(component ->
                        "Active".equalsIgnoreCase(
                                component.getCategory()))
                .count();
    }

    /**
     * Returns the current month and year used as the report name.
     *
     * @return current month and year
     */
    public String getCurrentMonthReportName() {
        LocalDate currentDate = LocalDate.now();

        String month = currentDate.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        return month + " " + currentDate.getYear();
    }

    /**
     * Determines the stock status of a component based on its
     * available quantity and low-stock limit.
     *
     * @param component component whose stock status is evaluated
     * @return Low Stock, Moderate Stock, or Healthy Stock
     */
    public String getStockStatus(Component component) {
        if (component.getQuantity() < component.getLowStockLimit()) {
            return "Low Stock";
        }

        if (component.getQuantity()
                <= component.getLowStockLimit() * 2) {

            return "Moderate Stock";
        }

        return "Healthy Stock";
    }
}