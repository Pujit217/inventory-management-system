package com.inventory.InventoryWebApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.ProductionPlan;

public interface ProductionPlanRepository extends JpaRepository<ProductionPlan, Integer> {
}