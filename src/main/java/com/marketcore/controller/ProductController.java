package com.marketcore.controller;

import com.marketcore.dto.ProductRecommendationDto;
import com.marketcore.model.Category;
import com.marketcore.model.Product;
import com.marketcore.repository.CategoryRepository;
import com.marketcore.service.CategoryService;
import com.marketcore.service.ProductService;
import com.marketcore.service.UserService;
import com.marketcore.util.SlugUtil;
import com.marketcore.config.SiteConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final UserService userService;
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;
    private final SiteConfig siteConfig;
    private final RestTemplate restTemplate = new RestTemplate();

    // Routes SEO-friendly avec les noms
    @GetMapping("/produit/{slug}")
    public String detailBySlug(@PathVariable String slug, Model model){
        // Extraire l'ID du slug (format: nom-produit-123)
        Long id = extractIdFromSlug(slug);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable");
        }

        var product = productService.findById(id);
        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable");
        }

        // Vérifier si le slug est correct, sinon rediriger vers la bonne URL
        String correctSlug = SlugUtil.toSlugWithId(product.getTitle(), product.getId());
        if (!slug.equals(correctSlug)) {
            return "redirect:/produit/" + correctSlug;
        }

        return buildProductDetailResponse(product, model, "/produit/" + correctSlug);
    }

    @GetMapping("/categorie/{slug}")
    public String categoryBySlug(@PathVariable String slug, Model model){
        // Extraire l'ID du slug (format: nom-categorie-123)
        Long id = extractIdFromSlug(slug);
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie introuvable");
        }

        var categoryOpt = categoryRepository.findById(id);
        if(categoryOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie introuvable");
        }

        var category = categoryOpt.get();

        // Vérifier si le slug est correct, sinon rediriger vers la bonne URL
        String correctSlug = SlugUtil.toSlugWithId(category.getName(), category.getId());
        if (!slug.equals(correctSlug)) {
            return "redirect:/categorie/" + correctSlug;
        }

        return buildCategoryResponse(category, model, "/categorie/" + correctSlug);
    }

    // Anciennes routes avec ID - redirection vers les nouvelles URLs SEO
    @GetMapping("/product/{id}")
    public String detail(@PathVariable Long id, Model model){
        var product = productService.findById(id);
        if(product == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit introuvable");
        }

        // Rediriger vers la nouvelle URL SEO-friendly
        String slug = SlugUtil.toSlugWithId(product.getTitle(), product.getId());
        return "redirect:/produit/" + slug;
    }

    @GetMapping("/category/{id}")
    public String byCategory(@PathVariable("id") Long id, Model model){
        var categoryOpt = categoryRepository.findById(id);
        if(categoryOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Catégorie introuvable");
        }

        var category = categoryOpt.get();

        // Rediriger vers la nouvelle URL SEO-friendly
        String slug = SlugUtil.toSlugWithId(category.getName(), category.getId());
        return "redirect:/categorie/" + slug;
    }

    @GetMapping("/product/list")
    public String listAllProducts(Model model) {
        var products = productService.findAllActiveProducts(); // Utiliser tous les produits actifs au lieu de seulement les 8 derniers
        var brands = productService.findDistinctActiveBrands(); // Utiliser seulement les marques des produits actifs

        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("pageTitle", "Tous les produits");

        // Meta description pour la page de tous les produits
        String metaDescription = "Découvrez tous nos produits disponibles sur " + siteConfig.getSiteName() + ". " +
                               products.size() + " produits avec livraison rapide et paiement sécurisé.";
        model.addAttribute("metaDescription", metaDescription);

        return "product/list";
    }

    @GetMapping("/vendeur/{sellerId}")
    public String sellerProducts(@PathVariable Long sellerId, Model model) {
        // Récupérer le vendeur
        var seller = userService.findById(sellerId);
        if (seller == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendeur introuvable");
        }

        // Vérifier que le vendeur est professionnel
        if (!seller.isSellerPro()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cette page n'est accessible que pour les vendeurs professionnels");
        }

        // Récupérer les produits actifs du vendeur
        var products = productService.findActiveProductsBySellerId(sellerId);

        model.addAttribute("seller", seller);
        model.addAttribute("products", products);
        model.addAttribute("pageTitle", "Produits de " + (seller.getMarquee() != null ? seller.getMarquee() : seller.getFullName()));

        // Meta description pour la page vendeur
        String metaDescription = "Découvrez tous les produits de " +
                               (seller.getMarquee() != null ? seller.getMarquee() : seller.getFullName()) +
                               " sur . " + products.size() + " produits disponibles.";
        model.addAttribute("metaDescription", metaDescription);

        return "product/seller";
    }

    // Méthodes utilitaires
    private Long extractIdFromSlug(String slug) {
        try {
            int lastDashIndex = slug.lastIndexOf('-');
            if (lastDashIndex == -1) {
                return null;
            }
            return Long.parseLong(slug.substring(lastDashIndex + 1));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private String buildProductDetailResponse(Product product, Model model, String canonicalUrl) {
        model.addAttribute("product", product);
        model.addAttribute("pageTitle", product.getTitle());

        // Ajouter le chemin complet des catégories parentes pour le breadcrumb
        if (product.getCategory() != null) {
            List<Category> categoryPath = categoryService.getCategoryAncestors(product.getCategory());
            model.addAttribute("categoryPath", categoryPath);
        }

        // Génération d'une meta description dynamique à partir de la description du produit
        String metaDescription = product.getDescription();
        if (metaDescription != null && !metaDescription.isEmpty()) {
            // Limiter la description à environ 160 caractères pour les meta tags
            if (metaDescription.length() > 160) {
                metaDescription = metaDescription.substring(0, 157) + "...";
            }
        } else {
            metaDescription = "Découvrez " + product.getTitle() + " sur " + siteConfig.getSiteName() + ". Prix: " + product.getPrice() + " EUR. Livraison rapide et paiement sécurisé.";
        }
        model.addAttribute("metaDescription", metaDescription);

        // Génération des meta keywords basés sur les caractéristiques du produit
        StringBuilder keywordsBuilder = new StringBuilder();
        keywordsBuilder.append(product.getTitle());
        if (product.getBrand() != null && !product.getBrand().isEmpty()) {
            keywordsBuilder.append(", ").append(product.getBrand());
        }
        if (product.getCategory() != null) {
            keywordsBuilder.append(", ").append(product.getCategory().getName());
            // Ajouter la catégorie parent si elle existe
            if (product.getCategory().getParent() != null) {
                keywordsBuilder.append(", ").append(product.getCategory().getParent().getName());
            }
        }
        keywordsBuilder.append(", achat ").append(product.getTitle().split(" ")[0]);
        keywordsBuilder.append(", ").append(product.getTitle().split(" ")[0]).append(" ").append(siteConfig.getSiteCountry());
        keywordsBuilder.append(", ").append(siteConfig.getSiteName());

        model.addAttribute("metaKeywords", keywordsBuilder.toString());

        // Ajouter l'URL canonique SEO-friendly
        model.addAttribute("canonicalUrl", canonicalUrl);

        // Image principale pour Open Graph et Twitter Cards
        String ogImage = product.getImage1Path() != null ? "/images/" + product.getId() + "/1" : "/img/marketcore-og-image.jpg";
        model.addAttribute("ogImage", ogImage);

        // Récupérer les produits recommandés directement depuis le service
        try {
            // Récupérer les recommandations manuelles depuis la base de données
            Set<Product> manualRecommendations = productService.getProduitsQuiPourraientInteresser(product.getId());

            // Convertir en DTO pour l'affichage
            List<ProductRecommendationDto> recommendedProducts = manualRecommendations.stream()
                .filter(p -> p.getActive() == null || p.getActive()) // Filtrer les produits actifs seulement
                .map(p -> ProductRecommendationDto.builder()
                    .id(p.getId())
                    .title(p.getTitle())
                    .brand(p.getBrand())
                    .shortDescription(p.getShortDescription())
                    .price(p.getPrice())
                    .discountedPrice(p.getDiscountedPrice())
                    .categoryName(p.getCategory() != null ? p.getCategory().getName() : null)
                    .image1Path(p.getImage1Path())
                    .image1Url(p.getImage1Url())
                    .active(p.getActive())
                    .build())
                .limit(10) // Limiter à 10 recommandations
                .collect(Collectors.toList());

            model.addAttribute("recommendedProducts", recommendedProducts);
        } catch (Exception e) {
            // En cas d'erreur, on continue sans recommandations
            model.addAttribute("recommendedProducts", new ArrayList<>());
        }

        return "product/detail";
    }

    private String buildCategoryResponse(Category category, Model model, String canonicalUrl) {
        // Utiliser la nouvelle méthode qui trie par statut de stock
        var products = productService.findAllActiveUnderCategorySortedByStock(category.getId());
        var brands = productService.findDistinctActiveBrandsByCategory(category.getId());

        model.addAttribute("products", products);
        model.addAttribute("brands", brands);
        model.addAttribute("category", category);
        model.addAttribute("pageTitle", category.getName());

        // Ajouter le chemin complet des catégories parentes pour le breadcrumb
        List<Category> categoryPath = categoryService.getCategoryAncestors(category);
        model.addAttribute("categoryPath", categoryPath);

        // Ajouter les sous-catégories pour l'affichage des tags
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            model.addAttribute("subCategories", category.getChildren());
        }

        String metaDescription = "Découvrez notre sélection de produits dans la catégorie " + category.getName() +
                               ". " + products.size() + " produits disponibles avec livraison rapide et paiement sécurisé sur " + siteConfig.getSiteName() + ".";
        model.addAttribute("metaDescription", metaDescription);

        // Génération des meta keywords basés sur la catégorie
        StringBuilder keywordsBuilder = new StringBuilder();
        keywordsBuilder.append(category.getName());

        // Ajouter la catégorie parent si elle existe
        if (category.getParent() != null) {
            keywordsBuilder.append(", ").append(category.getParent().getName());
        }

        // Ajouter les sous-catégories (limiter à 3 pour éviter la surcharge)
        if (category.getChildren() != null && !category.getChildren().isEmpty()) {
            category.getChildren().stream()
                .limit(3)
                .forEach(subcat -> keywordsBuilder.append(", ").append(subcat.getName()));
        }

        // Ajouter les marques les plus courantes (limiter à 3)
        if (brands != null && !brands.isEmpty()) {
            brands.stream()
                .filter(brand -> brand != null && !brand.isEmpty())
                .limit(3)
                .forEach(brand -> keywordsBuilder.append(", ").append(brand));
        }

        keywordsBuilder.append(", ").append(category.getName()).append(" ").append(siteConfig.getSiteCountry());
        keywordsBuilder.append(", acheter ").append(category.getName());
        keywordsBuilder.append(", ").append(siteConfig.getSiteName());

        model.addAttribute("metaKeywords", keywordsBuilder.toString());
        model.addAttribute("canonicalUrl", canonicalUrl);

        return "product/list";
    }
}
