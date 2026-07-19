package com.inventory.InventoryWebApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.InventoryWebApp.model.Component;
import com.inventory.InventoryWebApp.model.Supplier;
import com.inventory.InventoryWebApp.repository.ComponentRepository;
import com.inventory.InventoryWebApp.repository.SupplierRepository;

/**
 * Provides business operations for managing inventory components
 * and suppliers.
 *
 * This service handles creating, retrieving, searching, filtering,
 * updating, and deleting inventory records.
 *
 * @author Pujit Varma Muppala
 */
@Service
public class InventoryManager {

    private final ComponentRepository componentRepository;
    private final SupplierRepository supplierRepository;

    /**
     * Creates an InventoryManager with the required repositories.
     *
     * @param componentRepository repository used to manage components
     * @param supplierRepository repository used to manage suppliers
     */
    public InventoryManager(ComponentRepository componentRepository,
                            SupplierRepository supplierRepository) {

        this.componentRepository = componentRepository;
        this.supplierRepository = supplierRepository;
    }

    /**
     * Adds or updates a component in the database.
     *
     * @param component component to save
     */
    public void addComponent(Component component) {
        componentRepository.save(component);
    }

    /**
     * Returns all components stored in the database.
     *
     * @return list of all components
     */
    public List<Component> getAllComponents() {
        return componentRepository.findAll();
    }

    /**
     * Adds or updates a supplier in the database.
     *
     * @param supplier supplier to save
     */
    public void addSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    /**
     * Returns all suppliers stored in the database.
     *
     * @return list of all suppliers
     */
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    /**
     * Searches for a component using its exact part name without
     * considering letter case.
     *
     * @param part component part name
     * @return matching component, or null when no component is found
     */
    public Component searchByName(String part) {
        return componentRepository.findByPartIgnoreCase(part);
    }

    /**
     * Deletes a component when the provided component ID exists.
     *
     * @param componentId ID of the component to delete
     * @return true when the component was deleted; false when the
     *         component was not found
     */
    public boolean deleteComponent(int componentId) {
        if (!componentRepository.existsById(componentId)) {
            return false;
        }

        componentRepository.deleteById(componentId);
        return true;
    }

    /**
     * Returns all components belonging to the selected category.
     *
     * @param category category used to filter components
     * @return list of components in the selected category
     */
    public List<Component> filterByCategory(String category) {
        return componentRepository.findByCategoryIgnoreCase(category);
    }

    /**
     * Updates the available stock quantity of a component.
     *
     * The update is ignored when the component ID does not exist.
     *
     * @param componentId ID of the component to update
     * @param quantity new stock quantity
     */
    public void updateStock(int componentId, int quantity) {
        Optional<Component> component =
                componentRepository.findById(componentId);

        if (component.isPresent()) {
            component.get().setQuantity(quantity);
            componentRepository.save(component.get());
        }
    }

    /**
     * Returns all components whose quantities are below their
     * configured low-stock limits.
     *
     * @return list of low-stock components
     */
    public List<Component> checkLowStock() {
        return componentRepository.findAll()
                .stream()
                .filter(Component::isLowStock)
                .toList();
    }

    /**
     * Calculates the total quantity of all component units
     * currently stored in inventory.
     *
     * @return total inventory quantity
     */
    public int getTotalQuantity() {
        int totalQuantity = 0;

        for (Component component : componentRepository.findAll()) {
            totalQuantity += component.getQuantity();
        }

        return totalQuantity;
    }

    /**
     * Returns the total number of component records in the database.
     *
     * @return total number of components
     */
    public int getTotalComponents() {
        return (int) componentRepository.count();
    }

    /**
     * Returns the total number of components currently below
     * their low-stock limits.
     *
     * @return number of low-stock components
     */
    public int getLowStockCount() {
        return checkLowStock().size();
    }

    /**
     * Searches components using category, part name, manufacturer
     * part number, or component ID.
     *
     * @param searchText text entered by the user
     * @return list of matching components
     */
    public List<Component> searchComponents(String searchText) {
        List<Component> results = new ArrayList<>(
                componentRepository
                        .findByCategoryContainingIgnoreCaseOrPartContainingIgnoreCaseOrManufacturerPnContainingIgnoreCase(
                                searchText,
                                searchText,
                                searchText
                        )
        );

        try {
            int componentId = Integer.parseInt(searchText);

            Optional<Component> component =
                    componentRepository.findById(componentId);

            if (component.isPresent()
                    && !results.contains(component.get())) {

                results.add(component.get());
            }
        } catch (NumberFormatException exception) {
            // The search text is not a numeric component ID.
        }

        return results;
    }
}