package com.inventory.InventoryWebApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.InventoryWebApp.model.BomItem;
import com.inventory.InventoryWebApp.model.Component;
import com.inventory.InventoryWebApp.model.Product;
import com.inventory.InventoryWebApp.model.ProductionPlan;
import com.inventory.InventoryWebApp.model.Supplier;
import com.inventory.InventoryWebApp.repository.BomItemRepository;
import com.inventory.InventoryWebApp.repository.ComponentRepository;
import com.inventory.InventoryWebApp.repository.ProductRepository;
import com.inventory.InventoryWebApp.repository.ProductionPlanRepository;
import com.inventory.InventoryWebApp.service.InventoryManager;
import com.inventory.InventoryWebApp.service.ProductionManager;
import com.inventory.InventoryWebApp.service.ProductionRequirement;
import com.inventory.InventoryWebApp.service.ReportManager;
import com.inventory.InventoryWebApp.service.VerificationManager;

/**
 * Controller responsible for handling all web requests for the
 * Inventory Management System.
 *
 * This controller manages inventory operations, reporting,
 * production planning, production analysis, and production
 * verification.
 *
 * @author Pujit Varma Muppala
 */
@Controller
public class InventoryController {

    private InventoryManager inventoryManager;
    private ReportManager reportManager;
    private ProductRepository productRepository;
    private ComponentRepository componentRepository;
    private BomItemRepository bomItemRepository;
    private ProductionPlanRepository productionPlanRepository;
    private ProductionManager productionManager;
    private VerificationManager verificationManager;

    /**
     * Creates an InventoryController with all required services
     * and repositories.
     *
     * @param inventoryManager service for inventory operations
     * @param reportManager service for inventory reports
     * @param productRepository repository for products
     * @param componentRepository repository for components
     * @param bomItemRepository repository for BOM items
     * @param productionPlanRepository repository for production plans
     * @param productionManager service for production analysis
     * @param verificationManager service for production verification
     */
    public InventoryController(InventoryManager inventoryManager,
                               ReportManager reportManager,
                               ProductRepository productRepository,
                               ComponentRepository componentRepository,
                               BomItemRepository bomItemRepository,
                               ProductionPlanRepository productionPlanRepository,
                               ProductionManager productionManager,
                               VerificationManager verificationManager) {

        this.inventoryManager = inventoryManager;
        this.reportManager = reportManager;
        this.productRepository = productRepository;
        this.componentRepository = componentRepository;
        this.bomItemRepository = bomItemRepository;
        this.productionPlanRepository = productionPlanRepository;
        this.productionManager = productionManager;
        this.verificationManager = verificationManager;
    }

    /**
     * Displays the dashboard page.
     *
     * Loads inventory statistics, component information,
     * and production summaries for display.
     *
     * @param model Spring model used to pass data to the view
     * @return dashboard page
     */
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("totalComponents",
                reportManager.getTotalComponents());

        model.addAttribute("lowStockCount",
                reportManager.getLowStockCount());

        model.addAttribute("components",
                componentRepository.findAll());

        model.addAttribute("currentProductionPlans",
                productionPlanRepository
                        .findByStatusIgnoreCase("Current Production"));

        model.addAttribute("plannedProductionPlans",
                productionPlanRepository
                        .findByStatusIgnoreCase("Planned"));

        return "index";
    }

    /**
     * Displays the Add Component page.
     *
     * @return add-component page
     */
    @GetMapping("/add")
    public String showAddComponentForm() {
        return "add-component";
    }

    /**
     * Creates a supplier and component, then stores them
     * in the inventory database.
     *
     * @param supplierId supplier ID
     * @param supplierName supplier name
     * @param contactNumber supplier contact number
     * @param email supplier email
     * @param purchaseLink supplier purchase link
     * @param componentId component ID
     * @param category component category
     * @param item item number
     * @param quantity available quantity
     * @param referenceList schematic reference list
     * @param part part name
     * @param pcbFootprint PCB footprint
     * @param manufacturer manufacturer name
     * @param manufacturerPn manufacturer part number
     * @param description component description
     * @param stockDetails stock information
     * @param locationList storage locations
     * @param lowStockLimit low stock threshold
     * @return redirects to the components page
     */
    @PostMapping("/add")
    public String addComponent(@RequestParam String supplierName,
                               @RequestParam String contactNumber,
                               @RequestParam String email,
                               @RequestParam String purchaseLink,
                               @RequestParam String category,
                               @RequestParam int item,
                               @RequestParam int quantity,
                               @RequestParam String referenceList,
                               @RequestParam String part,
                               @RequestParam String pcbFootprint,
                               @RequestParam String manufacturer,
                               @RequestParam String manufacturerPn,
                               @RequestParam String description,
                               @RequestParam String stockDetails,
                               @RequestParam String locationList,
                               @RequestParam int lowStockLimit) {

    	Supplier supplier = new Supplier(
    	        supplierName,
    	        contactNumber,
    	        email,
    	        purchaseLink
    	);

    	Component component = new Component();

    	component.setCategory(category);
    	component.setItem(item);
    	component.setQuantity(quantity);
    	component.setReferenceList(referenceList);
    	component.setPart(part);
    	component.setPcbFootprint(pcbFootprint);
    	component.setManufacturer(manufacturer);
    	component.setManufacturerPn(manufacturerPn);
    	component.setDescription(description);
    	component.setStockDetails(stockDetails);
    	component.setLocationList(locationList);
    	component.setLowStockLimit(lowStockLimit);
    	component.setSupplier(supplier);

    	inventoryManager.addSupplier(supplier);
    	inventoryManager.addComponent(component);

    	return "redirect:/components";
    }

    /**
     * Displays all inventory components.
     *
     * @param model Spring model used to pass data to the view
     * @return components page
     */
    @GetMapping("/components")
    public String viewComponents(Model model) {
        model.addAttribute("components", inventoryManager.getAllComponents());
        return "components";
    }

    /**
     * Searches inventory components by name.
     *
     * @param name component name
     * @param model Spring model used to pass data to the view
     * @return search result page
     */
    @GetMapping("/search")
    public String searchComponent(@RequestParam String name, Model model) {

        List<Component> components =
                inventoryManager.searchComponents(name);

        model.addAttribute("components", components);
        model.addAttribute("searchText", name);

        return "search-result";
    }

    /**
     * Displays components belonging to a selected category.
     *
     * @param category component category
     * @param model Spring model used to pass data to the view
     * @return filtered component page
     */
    @GetMapping("/filter")
    public String filterByCategory(@RequestParam String category, Model model) {

        List<Component> filteredComponents =
                inventoryManager.filterByCategory(category);

        model.addAttribute("components", filteredComponents);
        model.addAttribute("category", category);

        return "filter-result";
    }

    /**
     * Deletes a component from the inventory.
     *
     * @param id component ID
     * @return redirects to the components page
     */
    @GetMapping("/delete")
    public String deleteComponent(@RequestParam int id) {
        inventoryManager.deleteComponent(id);
        return "redirect:/components";
    }

    /**
     * Updates the stock quantity of a component.
     *
     * @param componentId component ID
     * @param quantity updated quantity
     * @return redirects to the components page
     */
    @PostMapping("/update-stock")
    public String updateStock(@RequestParam int componentId,
                              @RequestParam int quantity) {
        inventoryManager.updateStock(componentId, quantity);
        return "redirect:/components";
    }

    /**
     * Displays all low-stock components.
     *
     * @param model Spring model used to pass data to the view
     * @return low-stock page
     */
    @GetMapping("/low-stock")
    public String lowStock(Model model) {
        model.addAttribute("components", reportManager.getLowStockComponents());
        return "low-stock";
    }

    /**
     * Displays the inventory report dashboard.
     *
     * @param model Spring model used to pass data to the view
     * @return report page
     */
    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("totalComponents", reportManager.getTotalComponents());
        model.addAttribute("totalQuantity", reportManager.getTotalQuantity());
        model.addAttribute("lowStockCount", reportManager.getLowStockCount());
        model.addAttribute("passiveCount", reportManager.getPassiveComponentCount());
        model.addAttribute("activeCount", reportManager.getActiveComponentCount());
        model.addAttribute("monthName", reportManager.getCurrentMonthReportName());
        model.addAttribute("components", reportManager.getAllComponents());

        return "report";
    }

    /**
     * Displays the Production Planning page.
     *
     * Loads products, components,
     * current production plans,
     * and planned production plans.
     *
     * @param model Spring model used to pass data to the view
     * @return production page
     */
    @GetMapping("/production")
    public String productionPage(Model model) {

        model.addAttribute(
                "products",
                productRepository.findAll()
        );

        model.addAttribute(
                "components",
                componentRepository.findAll()
        );

        model.addAttribute(
                "currentProductionPlans",
                productionPlanRepository.findByStatusIgnoreCase("Current Production")
        );

        model.addAttribute(
                "plannedProductionPlans",
                productionPlanRepository.findByStatusIgnoreCase("Planned")
        );

        return "production";
    }

    /**
     * Adds a new product to the system after validation.
     *
     * @param productId product ID
     * @param productName product name
     * @param description product description
     * @param status product status
     * @return redirects to the production page
     */
    @PostMapping("/production/add-product")
    public String addProduct(@RequestParam String productName,
                             @RequestParam String description,
                             @RequestParam String status) {

        if (productName == null || productName.trim().isEmpty()) {
            return "redirect:/production";
        }

        if (description == null || description.trim().isEmpty()) {
            return "redirect:/production";
        }

        if (productRepository.existsByProductNameIgnoreCase(
                productName.trim())) {

            return "redirect:/production";
        }

        Product product = new Product(
                productName.trim(),
                description.trim(),
                status
        );

        productRepository.save(product);

        return "redirect:/production";
    }

    /**
     * Adds a Bill of Materials item to a product.
     *
     * @param bomId BOM ID
     * @param productId product ID
     * @param componentId component ID
     * @param quantityRequired quantity required per product
     * @param costPerComponent cost of each component
     * @return redirects to the production page
     */
    @PostMapping("/production/add-bom")
    public String addBomItem(@RequestParam int productId,
                             @RequestParam int componentId,
                             @RequestParam int quantityRequired,
                             @RequestParam double costPerComponent) {

        Optional<Product> product =
                productRepository.findById(productId);

        Optional<Component> component =
                componentRepository.findById(componentId);

        if (product.isEmpty() || component.isEmpty()) {
            return "redirect:/production";
        }

        if (quantityRequired <= 0 || costPerComponent <= 0) {
            return "redirect:/production";
        }

        if (bomItemRepository.existsByProductAndComponent(
                product.get(), component.get())) {

            return "redirect:/production";
        }

        BomItem bomItem = new BomItem();

        bomItem.setProduct(product.get());
        bomItem.setComponent(component.get());
        bomItem.setQuantityRequired(quantityRequired);
        bomItem.setCostPerComponent(costPerComponent);

        bomItemRepository.save(bomItem);

        return "redirect:/production";
    }

    /**
     * Creates a production plan for a product.
     *
     * @param planId production plan ID
     * @param productId product ID
     * @param productionQuantity production quantity
     * @return redirects to the production page
     */
    @PostMapping("/production/add-plan")
    public String addProductionPlan(@RequestParam int productId,
                                    @RequestParam int productionQuantity) {

        Optional<Product> product =
                productRepository.findById(productId);

        if (product.isEmpty()) {
            return "redirect:/production";
        }

        if (productionQuantity <= 0) {
            return "redirect:/production";
        }

        List<BomItem> bomItems =
                bomItemRepository.findByProduct(product.get());

        if (bomItems.isEmpty()) {
            return "redirect:/production";
        }

        if (productionPlanRepository
                .existsByProductAndStatusIgnoreCase(
                        product.get(), "Planned")) {

            return "redirect:/production";
        }

        ProductionPlan plan = new ProductionPlan();

        plan.setProduct(product.get());
        plan.setProductionQuantity(productionQuantity);
        plan.setStatus("Planned");

        productionPlanRepository.save(plan);

        return "redirect:/production";
    }

    /**
     * Displays the Production Analysis page.
     *
     * @param model Spring model used to pass data to the view
     * @return production-analysis page
     */
    @GetMapping("/production/analysis")
    public String productionAnalysisPage(Model model) {
    	model.addAttribute("plans",
    	        productionPlanRepository.findByStatusIgnoreCase("Planned"));
        return "production-analysis";
    }
    
    /**
     * Calculates production requirements for a selected plan.
     *
     * @param planId production plan ID
     * @param model Spring model used to pass data to the view
     * @return production-analysis page
     */
    @GetMapping("/production/analyze")
    public String analyzeProduction(@RequestParam int planId,
                                    Model model) {

        Optional<ProductionPlan> plan =
                productionPlanRepository.findById(planId);

        if (plan.isEmpty()) {
            return "redirect:/production/analysis";
        }

        if (!"Planned".equalsIgnoreCase(plan.get().getStatus())) {
            return "redirect:/production/analysis";
        }

        model.addAttribute("plan", plan.get());

        model.addAttribute(
                "requirements",
                productionManager.calculateRequirements(plan.get())
        );

        model.addAttribute(
                "plans",
                productionPlanRepository.findByStatusIgnoreCase("Planned")
        );

        return "production-analysis";
    }
    
    /**
     * Generates and downloads the BOM report
     * as a CSV file.
     *
     * @param planId production plan ID
     * @return CSV report
     */
    @GetMapping("/download/bom-report")
    public ResponseEntity<String> downloadBomReport(@RequestParam int planId) {

        ProductionPlan plan = productionPlanRepository.findById(planId).get();

        List<ProductionRequirement> requirements =
                productionManager.calculateRequirements(plan);

        String csv = "";

        csv += "Product," + plan.getProduct().getProductName() + "\n";
        csv += "Production Quantity," + plan.getProductionQuantity() + "\n\n";

        csv += "Component,Qty Per Product,Required Qty,Available Qty,Status\n";

        for (ProductionRequirement item : requirements) {

            csv += item.getComponentName() + ",";
            csv += item.getQuantityPerProduct() + ",";
            csv += item.getRequiredQuantity() + ",";
            csv += item.getAvailableQuantity() + ",";
            csv += item.getStatus() + "\n";

        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=BOM_Report.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csv);

    }
    
    /**
     * Displays the Production Verification page.
     *
     * @param model Spring model used to pass data to the view
     * @return verification page
     */
    @GetMapping("/verify")
    public String verifyPage(Model model) {
        model.addAttribute("components", componentRepository.findAll());

        model.addAttribute(
                "plans",
                productionPlanRepository.findByStatusIgnoreCase("Planned")
        );

        return "verify";
    }

    /**
     * Verifies inventory availability
     * for a selected production plan.
     *
     * @param planId production plan ID
     * @param model Spring model used to pass data to the view
     * @return verification page
     */
    @GetMapping("/verify/check")
    public String checkVerification(@RequestParam int planId, Model model) {
        Optional<ProductionPlan> plan = productionPlanRepository.findById(planId);

        if (plan.isPresent()) {
            model.addAttribute("selectedPlan", plan.get());
            model.addAttribute("verificationRows",
                    verificationManager.verifyPlan(plan.get()));
            model.addAttribute("canProceed",
                    verificationManager.canProceed(plan.get()));
        }

        model.addAttribute("components", componentRepository.findAll());
        model.addAttribute(
                "plans",
                productionPlanRepository.findByStatusIgnoreCase("Planned")
        );

        return "verify";
    }

    /**
     * Starts production for a verified plan.
     *
     * Inventory is updated and the production
     * plan status changes to Current Production.
     *
     * @param planId production plan ID
     * @return redirects to the verification page
     */
    @PostMapping("/verify/proceed")
    public String proceedVerification(@RequestParam int planId) {
        Optional<ProductionPlan> plan = productionPlanRepository.findById(planId);

        if (plan.isPresent()) {
            verificationManager.proceedProduction(plan.get());
        }

        return "redirect:/verify";
    }
}