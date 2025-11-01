package com.marketcore.service;

import com.marketcore.model.Product;
import com.marketcore.model.User;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Product> findAll();
    List<Product> findBySeller(User seller);
    List<Product> findByCategory(Long catId);
    List<Product> findAllUnderCategory(Long catId);
    List<Product> search(String q);
    List<Product> searchBySeller(String sellerName); // Nouvelle méthode pour rechercher par vendeur
    List<Product> findProductsWithDiscount();
    List<Product> findLatest8Products();
    List<String> findDistinctBrands(); // Nouvelle méthode pour récupérer les marques distinctes
    List<String> findDistinctBrandsByCategory(Long catId); // Nouvelle méthode pour récupérer les marques par catégorie

    // Méthodes pour le front-end (produits actifs uniquement)
    List<Product> findActiveByCategoryId(Long catId);
    List<Product> findAllActiveUnderCategory(Long catId);
    List<Product> searchActive(String q);
    List<Product> findActiveProductsWithDiscount();
    List<Product> findLatest8ActiveProducts();
    List<Product> findAllActiveProducts(); // Nouvelle méthode pour tous les produits actifs
    List<String> findDistinctActiveBrands();
    List<String> findDistinctActiveBrandsByCategory(Long catId);
    List<Product> findActiveProductsBySellerId(Long sellerId); // Nouvelle méthode pour les produits actifs d'un vendeur

    // Méthode pour trier les produits par statut de stock (INSTOCK en premier, OUTOFSTOCK à la fin)
    List<Product> findAllActiveUnderCategorySortedByStock(Long catId);

    Product save(Product product);
    Product findById(Long id);
    void delete(Long id);

    // Méthodes pour gérer les produits recommandés
    void ajouterProduitRecommande(Long productId, Long recommendedProductId);
    void supprimerProduitRecommande(Long productId, Long recommendedProductId);
    Set<Product> getProduitsQuiPourraientInteresser(Long productId);
    List<Product> getRecommandationsAutomatiques(Long productId, int limit);

    // explore this in complete version
}
