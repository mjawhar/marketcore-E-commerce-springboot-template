package com.marketcore.controller;

import com.marketcore.model.*;
import com.marketcore.model.enums.OrderStatus;
import com.marketcore.model.enums.ProductType;
import com.marketcore.model.enums.ProductStatus;
import com.marketcore.service.*;
import com.marketcore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private SellerContactService sellerContactService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private OrderService orderService;

    // Auth related endpoints
    @GetMapping("/login")
    public String loginSeller() {
        // explore this in complete version
    }

    @GetMapping("/dashboard")
    public String sellerDashboard(Model model) {
        // explore this in complete version
    }

    // Product management endpoints
    @GetMapping("/products")
    public String sellerProducts(Model model) {
        // explore this in complete version
    }

    @PostMapping("/products")
    public String saveProduct(@RequestParam("title") String title,
                            @RequestParam(value="brand", required=false) String brand,
                            @RequestParam("categoryId") Long catId,
                            @RequestParam("priceStr") String priceStr,
                            @RequestParam(value="discountedPriceStr", required=false) String discountedPriceStr,
                            @RequestParam("stock") String stock,
                            @RequestParam("shortDescription") String shortDescription,
                            @RequestParam(value="description", required=false) String description,
                            @RequestParam(value="img1", required=false) MultipartFile img1,
                            @RequestParam(value="img2", required=false) MultipartFile img2,
                            @RequestParam(value="img3", required=false) MultipartFile img3,
                            @RequestParam("productType") String productType,
                            RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        // explore this in complete version
    }

    // Contact related endpoints
    @PostMapping("/contact")
    public String handleSellerContact(@RequestParam String name,
                                    @RequestParam String phone,
                                    @RequestParam String email,
                                    @RequestParam String message) {
        // explore this in complete version
    }

    // Orders management endpoints
    @GetMapping("/orders")
    public String sellerOrders(@RequestParam(value = "status", required = false) String status, Model model) {
        // explore this in complete version
    }

    @GetMapping("/orders/{id}")
    public String viewOrder(@PathVariable Long id, Model model) {
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
                              @RequestParam("priceStr") String priceStr,
                              @RequestParam(value="discountedPriceStr", required=false) String discountedPriceStr,
                              @RequestParam("stock") String stock,
                              @RequestParam("shortDescription") String shortDescription,
                              @RequestParam(value="description", required=false) String description,
                              @RequestParam(value="img1", required=false) MultipartFile img1,
                              @RequestParam(value="img2", required=false) MultipartFile img2,
                              @RequestParam(value="img3", required=false) MultipartFile img3,
                              @RequestParam("productType") String productType,
                              @RequestParam(value="image1Url", required=false) String image1Url,
                              @RequestParam(value="image2Url", required=false) String image2Url,
                              @RequestParam(value="image3Url", required=false) String image3Url,
                              RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }

    // Profile management endpoints
    @GetMapping("/profile")
    public String sellerProfile(Model model) {
        // explore this in complete version
    }

    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam(value = "personName", required = false) String personName,
                               @RequestParam(value = "fullName", required = false) String fullName,
                               @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
                               @RequestParam(value = "facebookUrl", required = false) String facebookUrl,
                               @RequestParam(value = "instagramUrl", required = false) String instagramUrl,
                               @RequestParam(value = "marquee", required = false) String marquee,
                               RedirectAttributes redirectAttributes) {
        // explore this in complete version
    }
}
