package com.marketcore.repository;

import com.marketcore.model.Product;
import com.marketcore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySeller(User seller);
    List<Product> findByCategoryId(Long catId);
    List<Product> findByTitleContainingIgnoreCase(String q);
    List<Product> findBySellerFullNameContainingIgnoreCase(String sellerName);
    List<Product> findByDiscountedPriceIsNotNull();
    List<Product> findTop8ByOrderByIdDesc();

    // Méthodes pour le front-end (produits actifs uniquement - inclut les produits où active est null ou true)
    @Query("SELECT p FROM Product p WHERE p.category.id = :catId AND (p.active = true OR p.active IS NULL)")
    List<Product> findByCategoryIdAndActiveTrue(Long catId);

    @Query("SELECT p FROM Product p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :q, '%')) AND (p.active = true OR p.active IS NULL)")
    List<Product> findByTitleContainingIgnoreCaseAndActiveTrue(String q);

    @Query("SELECT p FROM Product p WHERE p.discountedPrice IS NOT NULL AND (p.active = true OR p.active IS NULL)")
    List<Product> findByDiscountedPriceIsNotNullAndActiveTrue();

    @Query("SELECT p FROM Product p WHERE (p.active = true OR p.active IS NULL) ORDER BY p.id DESC")
    List<Product> findTop8ByActiveTrueOrderByIdDesc(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE (p.active = true OR p.active IS NULL)")
    List<Product> findAllActiveProducts();

    @Query("select p from Product p where p.category.id = :catId or (p.category.parent.id = :catId)")
    List<Product> findAllUnderCategory(Long catId);

    @Query("select p from Product p where (p.category.id = :catId or p.category.parent.id = :catId) and (p.active = true or p.active is null)")
    List<Product> findAllActivesUnderCategory(Long catId);

    @Query("select p from Product p where (p.category.id = :catId or p.category.parent.id = :catId) and (p.active = true or p.active is null) ORDER BY CASE WHEN p.stock = 'OUTOFSTOCK' THEN 1 ELSE 0 END, p.id DESC")
    List<Product> findAllActivesUnderCategorySortedByStock(Long catId);

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE p.brand IS NOT NULL AND p.brand != '' ORDER BY p.brand")
    List<String> findDistinctBrands();

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE (p.category.id = :catId OR p.category.parent.id = :catId) AND p.brand IS NOT NULL AND p.brand != '' ORDER BY p.brand")
    List<String> findDistinctBrandsByCategory(Long catId);

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE (p.active = true OR p.active IS NULL) AND p.brand IS NOT NULL AND p.brand != '' ORDER BY p.brand")
    List<String> findDistinctActiveBrands();

    // Méthode pour récupérer les produits actifs d'un vendeur
    @Query("SELECT p FROM Product p WHERE p.seller.id = :sellerId AND (p.active = true OR p.active IS NULL) ORDER BY p.id DESC")
    List<Product> findActiveProductsBySellerId(Long sellerId);

    @Query("SELECT DISTINCT p.brand FROM Product p WHERE (p.category.id = :catId OR p.category.parent.id = :catId) AND (p.active = true OR p.active IS NULL) AND p.brand IS NOT NULL AND p.brand != '' ORDER BY p.brand")
    List<String> findDistinctActiveBrandsByCategory(Long catId);

    // Méthode pour les recommandations automatiques
    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId AND p.id != :excludeProductId AND (p.active = true OR p.active IS NULL) ORDER BY p.id DESC")
    List<Product> findByCategoryIdAndIdNotAndActiveTrue(Long categoryId, Long excludeProductId, Pageable pageable);
}
