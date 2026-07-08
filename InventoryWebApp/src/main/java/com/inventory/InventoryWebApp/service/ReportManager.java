package com.inventory.InventoryWebApp.service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.inventory.InventoryWebApp.model.Component;

@Service
public class ReportManager {

    private InventoryManager inventoryManager;

    public ReportManager(InventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    public int getTotalComponents() {
        return inventoryManager.getTotalComponents();
    }

    public int getTotalQuantity() {
        return inventoryManager.getTotalQuantity();
    }

    public int getLowStockCount() {
        return inventoryManager.getLowStockCount();
    }

    public List<Component> getLowStockComponents() {
        return inventoryManager.checkLowStock();
    }

    public List<Component> getAllComponents() {
        return inventoryManager.getAllComponents();
    }

    public long getPassiveComponentCount() {
        return inventoryManager.getAllComponents()
                .stream()
                .filter(component -> component.getCategory().equalsIgnoreCase("Passive"))
                .count();
    }

    public long getActiveComponentCount() {
        return inventoryManager.getAllComponents()
                .stream()
                .filter(component -> component.getCategory().equalsIgnoreCase("Active"))
                .count();
    }

    public String getCurrentMonthReportName() {
        LocalDate currentDate = LocalDate.now();

        String month = currentDate.getMonth()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        int year = currentDate.getYear();

        return month + " " + year;
    }

    public String getStockStatus(Component component) {
        if (component.getQuantity() < component.getLowStockLimit()) {
            return "Low Stock";
        } else if (component.getQuantity() <= component.getLowStockLimit() * 2) {
            return "Moderate Stock";
        } else {
            return "Healthy Stock";
        }
    }
}