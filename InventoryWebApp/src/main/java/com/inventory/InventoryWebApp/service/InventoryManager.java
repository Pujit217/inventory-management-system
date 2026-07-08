package com.inventory.InventoryWebApp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.inventory.InventoryWebApp.model.Component;
import com.inventory.InventoryWebApp.model.Supplier;
import com.inventory.InventoryWebApp.repository.ComponentRepository;
import com.inventory.InventoryWebApp.repository.SupplierRepository;

@Service
public class InventoryManager {

    private ComponentRepository componentRepository;
    private SupplierRepository supplierRepository;

    public InventoryManager(ComponentRepository componentRepository,
                            SupplierRepository supplierRepository) {
        this.componentRepository = componentRepository;
        this.supplierRepository = supplierRepository;
    }

    public void addComponent(Component component) {
        componentRepository.save(component);
    }

    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Component searchByName(String part) {
        return componentRepository.findByPartIgnoreCase(part);
    }

    public boolean deleteComponent(int componentId) {
        if (componentRepository.existsById(componentId)) {
            componentRepository.deleteById(componentId);
            return true;
        }

        return false;
    }

    public List<Component> filterByCategory(String category) {
        return componentRepository.findByCategoryIgnoreCase(category);
    }

    public void updateStock(int componentId, int quantity) {
        Component component = componentRepository.findById(componentId).orElse(null);

        if (component != null) {
            component.setQuantity(quantity);
            componentRepository.save(component);
        }
    }

    public List<Component> checkLowStock() {
        return componentRepository.findAll()
                .stream()
                .filter(component -> component.getQuantity() < component.getLowStockLimit())
                .toList();
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;

        for (Component component : componentRepository.findAll()) {
            totalQuantity += component.getQuantity();
        }

        return totalQuantity;
    }

    public int getTotalComponents() {
        return (int) componentRepository.count();
    }

    public int getLowStockCount() {
        return checkLowStock().size();
    }
}