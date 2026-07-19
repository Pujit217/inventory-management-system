	📦 Inventory Management System
	Smart Inventory & Production Management Platform
	
	📖 Project Overview

	The Inventory Management System is a full-stack web application developed to
	help organizations efficiently manage inventory components, suppliers, products,
	and production workflows through a centralized and intuitive interface.
	
	The platform enables users to maintain detailed inventory records, organize 
	supplier information, manage Bills of Materials (BOM), create production plans,
	verify production readiness, and generate inventory reports. It also includes 
	stock monitoring and production analysis features that help determine material
	availability before manufacturing begins.
	
	Built using Java, Spring Boot, MySQL, and Thymeleaf, the application follows a
	layered architecture that separates the presentation, business logic, and data 
	access layers, making the system modular, maintainable, and scalable.
	
> Dashboard Screenshot



✨ Features

	Inventory Management
	- Add, view, and delete inventory components.
	- Search components by part name, category, manufacturer part number, or component ID.
	- Filter components by category.
	- Update inventory stock quantities.
	- Automatically detect and monitor low-stock components.

	Supplier Management
	- Store supplier contact information and purchase links.
	- Associate suppliers with inventory components.
	- Maintain supplier records for inventory tracking.

	Product Management
	- Create and manage finished products.
	- Store product descriptions and production status.
	- Prevent duplicate product entries.

	Bill of Materials (BOM) Management
	- Associate components with products.
	- Define required quantities for each component.
	- Record component costs for production planning.
	- Prevent duplicate BOM entries.

	Production Planning
	- Create production plans for finished products.
	- Track planned and current production.
	- Prevent duplicate production plans for the same product.

	Production Analysis
	- Calculate required component quantities.
	- Compare required inventory with available stock.
	- Detect component shortages.
	- Estimate total production material costs.
	- Download production analysis reports as PDF.

	Production Verification
	- Verify inventory availability before production.
	- Prevent production when inventory is insufficient.
	- Automatically deduct inventory after production begins.
	- Update production status throughout the workflow.

	Reports & Dashboard
	- Interactive dashboard displaying inventory statistics.
	- Total components, inventory quantity, and low-stock summary.
	- Inventory reports with stock status indicators.
	- Monthly inventory reporting.
	- Production overview and inventory insights.
	
🛠 Technology Stack

		Category	                  		Technologies
	Programming Language 				  		Java 21
	Framework							   		  Spring Boot
	Frontend								 ThymeLeaf, HTML, CSS
	Database							      		MySQL
	Data Access						 Spring Data JPA, Hibernate
	Build Tool							   		Maven
	Development Environment				Eclipse IDE
	Version Control					    	Git, GitHub
	
🏗 Project Architecture

	The Inventory Management System follows a layered architecture that
	separates the presentation, business logic, and data access layers.
	This design improves code organization, maintainability, and scalability
	by assigning each layer a specific responsibility.
	
										 					User
									                     │
									                     ▼
									        ┌─────────────────────────┐
									        │   Thymeleaf Templates   │
									        │(HTML / CSS / JavaScript)│
									        └─────────────────────────┘
									                     │
									                     ▼
									        ┌─────────────────────────┐
									        │       Controllers       │
									        │   Handle HTTP Requests  │
									        └─────────────────────────┘
									                     │
									                     ▼
									        ┌─────────────────────────┐
									        │        Services         │
									        │ Business Logic & Rules  │
									        └─────────────────────────┘
									                     │
									                     ▼
									        ┌─────────────────────────┐
									        │      Repositories       │
									        │ Spring Data JPA Access  │
									        └─────────────────────────┘
									                     │
									                     ▼
									        ┌─────────────────────────┐
									        │         MySQL           │
									        │       Database          │
									        └─────────────────────────┘
        
        
	Architecture Layers
	
	- Presentation Layer	Provides the user interface using Thymeleaf templates, HTML, CSS, and JavaScript.
	- Controller Layer	   Handles incoming HTTP requests, validates user input, and coordinates application workflows.
	- Service Layer	      Implements business logic for inventory, suppliers, products, BOM management, 
	                       production planning, verification, and reporting.
	- Repository Layer	   Uses Spring Data JPA repositories to perform CRUD operations and database queries.
	- Database Layer	      Stores application data including suppliers, inventory components, products, Bills 
	                      of Materials, and production plans in MySQL.
	                      
🗄 Database Design

	The application uses a relational MySQL database to maintain inventory records
	and production data. The database is designed with normalized tables and foreign
	key relationships to ensure data consistency and integrity.

	Entity Relationship Diagram (ERD)
														Supplier
														    │
														    │ 1
														    │
														    ▼
														Component
														    ▲
														    │
														    │
														Bill of Materials
														    │
														    ▼
														Product
														    │
														    ▼
														Production Plan
														

🗄 Database Tables

	supplier	       Stores supplier details including contact information and purchase links.
	component	       Stores inventory components, stock quantities, locations, manufacturers, and supplier references.
	product	       Stores finished products that can be manufactured using inventory components.
	bom_item	       Represents the Bill of Materials by linking products with their required components and quantities.
	production_plan	Stores production schedules, quantities, status, and production planning information.
	
Key Relationships
One supplier can provide multiple inventory components.
One product can contain multiple components through the Bill of Materials (BOM).
A single component can be used in multiple products.
Each production plan is associated with one finished product.

Database Schema Screenshot