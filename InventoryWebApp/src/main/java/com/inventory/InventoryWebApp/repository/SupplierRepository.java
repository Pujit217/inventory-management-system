package com.inventory.InventoryWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.Supplier;

/**
 * Repository interface for managing supplier records.
 *
 * Provides standard database operations such as saving, finding,
 * updating, and deleting suppliers.
 *
 * @author Pujit Varma Muppala
 */
public interface SupplierRepository
        extends JpaRepository<Supplier, Integer> {
}