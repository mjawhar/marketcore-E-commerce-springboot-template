package com.marketcore.controller;

import com.marketcore.model.Category;
import com.marketcore.model.Product;
import com.marketcore.model.User;
import com.marketcore.model.StockStatus;
import com.marketcore.model.FeaturedProduct;
import com.marketcore.model.enums.ProductType;
import com.marketcore.model.enums.ProductStatus;
import com.marketcore.repository.CategoryRepository;
import com.marketcore.repository.UserRepository;
import com.marketcore.service.FileStorageService;
import com.marketcore.service.ProductService;
import com.marketcore.service.FeaturedProductService;
import com.marketcore.service.ImageOptimizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserRepository userRepository;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;
    private final FileStorageService fileStorageService;
    private final FeaturedProductService featuredProductService;
    private final ImageOptimizationService imageOptimizationService;

    @GetMapping
    public String dashboard(){
        // explore this in complete version
    }

    /* Sellers */
    @GetMapping("/sellers")
    public String sellers(@RequestParam(value="q", required=false) String q, Model model){
        // explore this in complete version
    }

    /* Categories (self-relation) */
    @GetMapping("/categories")
    public String categories(Model model){
        // explore this in complete version
    }

    @PostMapping("/categories")
    public String saveCategory(@ModelAttribute Category c,
                               @RequestParam(value="parentId", required=false) Long parentId){
        // explore this in complete version
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        // explore this in complete version
    }

    /* Products */
    @GetMapping("/products")
    public String products(@RequestParam(value="q", required=false) String q,
                          @RequestParam(value="seller", required=false) String sellerSearch,
                          Model model){
        // explore this in complete version
    }

    @PostMapping("/products")
    public String saveProduct(@RequestParam("title") String title,
                              @RequestParam(value="brand", required=false) String brand,
                              @RequestParam("categoryId") Long catId,
                              @RequestParam("sellerId") Long sellerId,
                              @RequestParam("priceStr") String priceStr,
                              @RequestParam(value="discountedPriceStr", required=false) String discountedPriceStr,
                              @RequestParam("stock") String stock,
                              @RequestParam("shortDescription") String shortDescription,
                              @RequestParam(value="description", required=false) String description,
                              @RequestParam(value="img1", required=false) MultipartFile img1,
                              @RequestParam(value="img2", required=false) MultipartFile img2,
                              @RequestParam(value="img3", required=false) MultipartFile img3,
                              @RequestParam("productType") String productType,
                              @RequestParam("status") String status,
                              @RequestParam(value="active", required=false) String active,
                              @RequestParam(value="image1Url", required=false) String image1Url,
                              @RequestParam(value="image2Url", required=false) String image2Url,
                              @RequestParam(value="image3Url", required=false) String image3Url,
                              RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        // explore this in complete version
    }


    public static String stripHtmlTags(String html) {
        // explore this in complete version
    }
    @GetMapping("/products/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        // explore this in complete version
    }

    @PostMapping("/products/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id,
                              @RequestParam("title") String title,
                              @RequestParam(value="brand", required=false) String brand,
                              @RequestParam("categoryId") Long catId,
                              @RequestParam("sellerId") Long sellerId,
                              @RequestParam("priceStr") String priceStr,
                              @RequestParam(value="discountedPriceStr", required=false) String discountedPriceStr,
                              @RequestParam("stock") String stock,
                              @RequestParam("shortDescription") String shortDescription,
                              @RequestParam(value="description", required=false) String description,
                              @RequestParam(value="img1", required=false) MultipartFile img1,
                              @RequestParam(value="img2", required=false) MultipartFile img2,
                              @RequestParam(value="img3", required=false) MultipartFile img3,
                              @RequestParam("productType") String productType,
                              @RequestParam("status") String status,
                              @RequestParam(value="active", required=false) String active,
                              @RequestParam(value="image1Url", required=false) String image1Url,
                              @RequestParam(value="image2Url", required=false) String image2Url,
                              @RequestParam(value="image3Url", required=false) String image3Url,
                              RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    /* CMS - Gestion des produits mis en avant */
    @GetMapping("/cms")
    public String cms(Model model) {
        // explore this in complete version
    }

    @PostMapping("/cms/add-featured")
    public String addFeaturedProduct(@RequestParam("productId") Long productId,
                                   @RequestParam("displayOrder") Integer displayOrder,
                                   RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @PostMapping("/cms/remove-featured/{id}")
    public String removeFeaturedProduct(@PathVariable("id") Long featuredProductId,
                                      RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @PostMapping("/cms/toggle-active/{id}")
    public String toggleActiveFeaturedProduct(@PathVariable("id") Long featuredProductId,
                                            RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @PostMapping("/cms/update-order/{id}")
    public String updateDisplayOrder(@PathVariable("id") Long featuredProductId,
                                   @RequestParam("newOrder") Integer newOrder,
                                   RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @GetMapping("/products/toggle-active/{id}")
    public String toggleProductActive(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    /* Gestion des recommandations de produits */
    @GetMapping("/product-recommendations")
    public String productRecommendations(Model model) {
        // explore this in complete version
    }
    
    /* Import de produits depuis XLSX */
    @GetMapping("/products/import")
    public String showImportProductsForm(Model model) {
        // explore this in complete version
    }
    
    @PostMapping("/products/import")
    public String importProductsFromXlsx(@RequestParam("xlsxFile") MultipartFile xlsxFile,
                                       @RequestParam("categoryId") Long categoryId,
                                       @RequestParam("sellerId") Long sellerId,
                                       @RequestParam("status") String status,
                                       @RequestParam("active") String active,
                                       RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }
    
    // Helper method to get cell value as string
    private String getCellValueAsString(Cell cell) {
        // explore this in complete version
    }

    /**
     * Endpoint pour optimiser toutes les images existantes
     * Accessible uniquement aux admins
     */
    @PostMapping("/optimize-images")
    @ResponseBody
    public String optimizeImages() {
        // explore this in complete version
    }

    /**
     * Page dédiée pour l'optimisation des images
     */
    @GetMapping("/optimize-images")
    public String optimizeImagesPage(Model model) {
        // explore this in complete version
    }

    /**
     * Page de test pour optimiser une seule image
     */
    @GetMapping("/optimize-images/test")
    public String optimizeImageTestPage(Model model) {
        // explore this in complete version
    }

    /**
     * Test d'optimisation sur une seule image uploadée
     */
    @PostMapping("/optimize-images/test-upload")
    @ResponseBody
    public TestOptimizationResponse testOptimizeImage(@RequestParam("testImage") MultipartFile testImage) {
        // explore this in complete version
    }

    /**
     * API pour lancer l'optimisation (appelée en AJAX)
     */
    @PostMapping("/optimize-images/run")
    @ResponseBody
    public OptimizationResponse runOptimization() {
        // explore this in complete version
    }

    // Classes pour la réponse JSON du test
    public static class TestOptimizationResponse {
        // explore this in complete version
    }

    public static class ImageTestResult {
        // explore this in complete version
    }

    // Classe pour la réponse JSON
    public static class OptimizationResponse {
        // explore this in complete version
    }
}
