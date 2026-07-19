package com.inventory.InventoryWebApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Inventory Management System application.
 * This class starts the Spring Boot application and initializes
 * the application context.
 *
 * @author Pujit Varma Muppala
 */
@SpringBootApplication
public class InventoryWebAppApplication {

    /**
     * Starts the Inventory Management System application.
     *
     * @param args command-line arguments passed when the application starts
     */
    public static void main(String[] args) {
        SpringApplication.run(InventoryWebAppApplication.class, args);
    }
}