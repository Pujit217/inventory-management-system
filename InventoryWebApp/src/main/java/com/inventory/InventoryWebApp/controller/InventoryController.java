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

    @GetMapping("/add")
    public String showAddComponentForm() {
        return "add-component";
    }

    @PostMapping("/add")
    public String addComponent(@RequestParam int supplierId,
                               @RequestParam String supplierName,
                               @RequestParam String contactNumber,
                               @RequestParam String email,
                               @RequestParam String purchaseLink,
                               @RequestParam int componentId,
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
                supplierId,
                supplierName,
                contactNumber,
                email,
                purchaseLink
        );

        Component component = new Component();

        component.setComponentId(componentId);
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

    @GetMapping("/components")
    public String viewComponents(Model model) {
        model.addAttribute("components", inventoryManager.getAllComponents());
        return "components";
    }

    @GetMapping("/search")
    public String searchComponent(@RequestParam String name, Model model) {
        Component component = inventoryManager.searchByName(name);
        model.addAttribute("component", component);
        return "search-result";
    }

    @GetMapping("/filter")
    public String filterByCategory(@RequestParam String category, Model model) {
        List<Component> filteredComponents = inventoryManager.filterByCategory(category);
        model.addAttribute("components", filteredComponents);
        model.addAttribute("category", category);
        return "filter-result";
    }

    @GetMapping("/delete")
    public String deleteComponent(@RequestParam int id) {
        inventoryManager.deleteComponent(id);
        return "redirect:/components";
    }

    @PostMapping("/update-stock")
    public String updateStock(@RequestParam int componentId,
                              @RequestParam int quantity) {
        inventoryManager.updateStock(componentId, quantity);
        return "redirect:/components";
    }

    @GetMapping("/low-stock")
    public String lowStock(Model model) {
        model.addAttribute("components", reportManager.getLowStockComponents());
        return "low-stock";
    }

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

    @GetMapping("/production")
    public String productionPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("components", componentRepository.findAll());
        model.addAttribute("plans", productionPlanRepository.findAll());

        return "production";
    }

    @PostMapping("/production/add-product")
    public String addProduct(@RequestParam int productId,
                             @RequestParam String productName,
                             @RequestParam String description,
                             @RequestParam String status) {

        Product product = new Product(productId, productName, description, status);
        productRepository.save(product);

        return "redirect:/production";
    }

    @PostMapping("/production/add-bom")
    public String addBomItem(@RequestParam int bomId,
                             @RequestParam int productId,
                             @RequestParam int componentId,
                             @RequestParam int quantityRequired,
                             @RequestParam double costPerComponent) {

        Optional<Product> product = productRepository.findById(productId);
        Optional<Component> component = componentRepository.findById(componentId);

        if (product.isPresent() && component.isPresent()) {
            BomItem bomItem = new BomItem();

            bomItem.setBomId(bomId);
            bomItem.setProduct(product.get());
            bomItem.setComponent(component.get());
            bomItem.setQuantityRequired(quantityRequired);
            bomItem.setCostPerComponent(costPerComponent);

            bomItemRepository.save(bomItem);
        }

        return "redirect:/production";
    }

    @PostMapping("/production/add-plan")
    public String addProductionPlan(@RequestParam int planId,
                                    @RequestParam int productId,
                                    @RequestParam int productionQuantity,
                                    @RequestParam String status) {

        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            ProductionPlan plan = new ProductionPlan();

            plan.setPlanId(planId);
            plan.setProduct(product.get());
            plan.setProductionQuantity(productionQuantity);
            plan.setStatus(status);

            productionPlanRepository.save(plan);
        }

        return "redirect:/production";
    }

    @GetMapping("/production/analysis")
    public String productionAnalysisPage(Model model) {
        model.addAttribute("plans", productionPlanRepository.findAll());
        return "production-analysis";
    }
    
    @GetMapping("/production/analyze")
    public String analyzeProduction(@RequestParam int planId,
                                    Model model) {

        Optional<ProductionPlan> plan = productionPlanRepository.findById(planId);

        if (plan.isPresent()) {
            model.addAttribute("plan", plan.get());
            model.addAttribute("requirements",
                    productionManager.calculateRequirements(plan.get()));
        }

        model.addAttribute("plans", productionPlanRepository.findAll());

        return "production-analysis";
    }
    
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
    
    @GetMapping("/verify")
    public String verifyPage(Model model) {
        model.addAttribute("components", componentRepository.findAll());

        model.addAttribute(
                "plans",
                productionPlanRepository.findByStatusIgnoreCase("Planned")
        );

        return "verify";
    }

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

    @PostMapping("/verify/proceed")
    public String proceedVerification(@RequestParam int planId) {
        Optional<ProductionPlan> plan = productionPlanRepository.findById(planId);

        if (plan.isPresent()) {
            verificationManager.proceedProduction(plan.get());
        }

        return "redirect:/verify";
    }
}