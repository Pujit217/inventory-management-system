package com.inventory.InventoryWebApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.Component;

/**
 * Repository interface for managing component records.
 *
 * Provides standard database operations for components and additional
 * queries for searching components by part, category, and manufacturer
 * part number.
 *
 * @author Pujit Varma Muppala
 */
public interface ComponentRepository
        extends JpaRepository<Component, Integer> {

    /**
     * Finds a component by its part name without considering letter case.
     *
     * @param part part name to search for
     * @return matching component, or null when no component is found
     */
    Component findByPartIgnoreCase(String part);

    /**
     * Finds all components belonging to a category without considering
     * letter case.
     *
     * @param category category to search for
     * @return list of matching components
     */
    List<Component> findByCategoryIgnoreCase(String category);

    /**
     * Searches components using partial matches against the category,
     * part name, or manufacturer part number.
     *
     * @param category text used to search component categories
     * @param part text used to search component part names
     * @param manufacturerPn text used to search manufacturer part numbers
     * @return list of components matching any of the search values
     */
    List<Component>
            findByCategoryContainingIgnoreCaseOrPartContainingIgnoreCaseOrManufacturerPnContainingIgnoreCase(
                    String category,
                    String part,
                    String manufacturerPn
            );
}