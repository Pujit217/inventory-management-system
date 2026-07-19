package com.inventory.InventoryWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.Product;

/**
 * Repository interface for managing product records.
 *
 * Provides standard database operations for products and a query
 * for checking whether a product name already exists.
 *
 * @author Pujit Varma Muppala
 */
public interface ProductRepository
        extends JpaRepository<Product, Integer> {

    /**
     * Determines whether a product exists with the given name
     * without considering letter case.
     *
     * @param productName product name to check
     * @return true when the product name already exists;
     *         false otherwise
     */
    boolean existsByProductNameIgnoreCase(String productName);
}