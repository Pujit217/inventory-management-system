package com.inventory.InventoryWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}