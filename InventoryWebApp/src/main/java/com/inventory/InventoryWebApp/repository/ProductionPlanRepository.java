package com.inventory.InventoryWebApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.Product;
import com.inventory.InventoryWebApp.model.ProductionPlan;

/**
 * Repository interface for managing production plan records.
 *
 * Provides standard database operations for production plans and
 * additional queries for retrieving plans by status and preventing
 * duplicate active plans.
 *
 * @author Pujit Varma Muppala
 */
public interface ProductionPlanRepository
        extends JpaRepository<ProductionPlan, Integer> {

    /**
     * Finds all production plans with the given status without
     * considering letter case.
     *
     * @param status production status to search for
     * @return list of production plans with the given status
     */
    List<ProductionPlan> findByStatusIgnoreCase(String status);

    /**
     * Determines whether a production plan exists for a product
     * with the specified status.
     *
     * @param product product associated with the plan
     * @param status production plan status
     * @return true when a matching production plan exists;
     *         false otherwise
     */
    boolean existsByProductAndStatusIgnoreCase(
            Product product,
            String status
    );
}