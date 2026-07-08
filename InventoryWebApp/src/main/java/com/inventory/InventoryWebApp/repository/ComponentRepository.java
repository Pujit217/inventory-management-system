package com.inventory.InventoryWebApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.InventoryWebApp.model.Component;

public interface ComponentRepository extends JpaRepository<Component, Integer> {

    Component findByPartIgnoreCase(String part);

    List<Component> findByCategoryIgnoreCase(String category);
}