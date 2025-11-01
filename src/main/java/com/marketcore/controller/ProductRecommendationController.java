package com.marketcore.controller;

import com.marketcore.dto.ProductSearchDto;
import com.marketcore.dto.ProductRecommendationDto;
import com.marketcore.model.Product;
import com.marketcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductRecommendationController {

    private final ProductService productService;

    /**
     * Endpoint de recherche de produits pour l'interface d'administration
     */
    @GetMapping(value = "/search")
    public ResponseEntity<List<ProductSearchDto>> searchProducts(@RequestParam("q") String query) {
        // explore this in complete version
    }

    /**
     * Méthode utilitaire pour convertir un Product en ProductSearchDto
     */
    private ProductSearchDto convertToSearchDto(Product product) {
        // explore this in complete version
    }

    /**
     * Méthode utilitaire pour convertir un Product en ProductRecommendationDto
     */
    private ProductRecommendationDto convertToRecommendationDto(Product product) {
        // explore this in complete version
    }

    /**
     * Ajouter un produit recommandé à un produit
     */
    @PostMapping("/recommendations/{productId}/add/{recommendedProductId}")
    public ResponseEntity<String> ajouterProduitRecommande(
            @PathVariable Long productId,
            @PathVariable Long recommendedProductId) {
        // explore this in complete version
    }

    /**
     * Supprimer un produit recommandé d'un produit
     */
    @DeleteMapping("/recommendations/{productId}/remove/{recommendedProductId}")
    public ResponseEntity<String> supprimerProduitRecommande(
            @PathVariable Long productId,
            @PathVariable Long recommendedProductId) {
        try {
            productService.supprimerProduitRecommande(productId, recommendedProductId);
            return ResponseEntity.ok("Produit recommandé supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    /**
     * Obtenir les produits recommandés manuellement pour un produit
     */
    @GetMapping("/recommendations/{productId}/manual")
    public ResponseEntity<List<ProductRecommendationDto>> getProduitsRecommandesManuel(@PathVariable Long productId) {
        try {
            Set<Product> recommendations = productService.getProduitsQuiPourraientInteresser(productId);
            List<ProductRecommendationDto> dtos = recommendations.stream()
                .map(this::convertToRecommendationDto)
                .collect(Collectors.toList());

            // Définir explicitement les headers pour éviter l'erreur 406
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return ResponseEntity.ok()
                .headers(headers)
                .body(dtos);
        } catch (Exception e) {
            e.printStackTrace(); // Pour debug
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.badRequest()
                .headers(headers)
                .body(Collections.emptyList());
        }
    }

    /**
     * Obtenir les recommandations automatiques pour un produit (basées sur la catégorie)
     */
    @GetMapping("/recommendations/{productId}/automatic")
    public ResponseEntity<List<ProductRecommendationDto>> getRecommandationsAutomatiques(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "6") int limit) {
        try {
            List<Product> recommendations = productService.getRecommandationsAutomatiques(productId, limit);
            List<ProductRecommendationDto> dtos = recommendations.stream()
                .map(this::convertToRecommendationDto)
                .collect(Collectors.toList());

            // Définir explicitement les headers pour éviter l'erreur 406
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return ResponseEntity.ok()
                .headers(headers)
                .body(dtos);
        } catch (Exception e) {
            e.printStackTrace(); // Pour debug
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.badRequest()
                .headers(headers)
                .body(Collections.emptyList());
        }
    }

    /**
     * Obtenir toutes les recommandations (manuelles + automatiques) pour un produit
     */
    @GetMapping("/recommendations/{productId}/all")
    public ResponseEntity<List<ProductRecommendationDto>> getToutesLesRecommandations(
            @PathVariable Long productId,
            @RequestParam(defaultValue = "10") int limit) {
        try {
            // Récupérer d'abord les recommandations manuelles
            Set<Product> manualRecommendations = productService.getProduitsQuiPourraientInteresser(productId);

            // Si on n'a pas assez de recommandations manuelles, compléter avec les automatiques
            if (manualRecommendations.size() < limit) {
                List<Product> autoRecommendations = productService.getRecommandationsAutomatiques(
                        productId,
                        limit - manualRecommendations.size()
                );

                // Fusionner les deux listes en évitant les doublons
                manualRecommendations.addAll(autoRecommendations);
            }

            List<ProductRecommendationDto> dtos = manualRecommendations.stream()
                .limit(limit)
                .map(this::convertToRecommendationDto)
                .collect(Collectors.toList());

            // Définir explicitement les headers pour éviter l'erreur 406
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            return ResponseEntity.ok()
                .headers(headers)
                .body(dtos);
        } catch (Exception e) {
            e.printStackTrace(); // Pour debug
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return ResponseEntity.badRequest()
                .headers(headers)
                .body(Collections.emptyList());
        }
    }
}
