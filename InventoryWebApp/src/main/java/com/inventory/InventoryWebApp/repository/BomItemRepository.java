package com.inventory.InventoryWebApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.BomItem;
import com.inventory.InventoryWebApp.model.Component;
import com.inventory.InventoryWebApp.model.Product;

/**
 * Repository interface for managing BOM item records.
 *
 * Provides standard database operations for BOM items and additional
 * queries for finding BOM items by product and checking for duplicate
 * product-component combinations.
 *
 * @author Pujit Varma Muppala
 */
public interface BomItemRepository extends JpaRepository<BomItem, Integer> {

    /**
     * Finds all BOM items associated with a product.
     *
     * @param product product whose BOM items should be retrieved
     * @return list of BOM items belonging to the product
     */
    List<BomItem> findByProduct(Product product);

    /**
     * Determines whether a BOM item already exists for the given
     * product and component.
     *
     * @param product product associated with the BOM item
     * @param component component associated with the BOM item
     * @return true when the product-component combination exists;
     *         false otherwise
     */
    boolean existsByProductAndComponent(
            Product product,
            Component component
    );
}