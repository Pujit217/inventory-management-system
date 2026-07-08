package com.inventory.InventoryWebApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.BomItem;
import com.inventory.InventoryWebApp.model.Product;

public interface BomItemRepository extends JpaRepository<BomItem, Integer> {

    List<BomItem> findByProduct(Product product);
}