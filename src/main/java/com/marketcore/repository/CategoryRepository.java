package com.marketcore.repository;

import com.marketcore.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByParentIsNull();
    List<Category> findByParentId(Long parentId);

    // Méthode pour récupérer les 5 dernières catégories ajoutées, triées par date de création décroissante
    @Query("SELECT c FROM Category c ORDER BY c.id DESC")
    List<Category> findTop5ByOrderByIdDesc();

    // Méthode pour récupérer les catégories qui ont des produits actifs ou null
    @Query("SELECT DISTINCT c FROM Category c " +
           "INNER JOIN Product p ON p.category = c " +
           "WHERE p.active = true OR p.active IS NULL " +
           "ORDER BY c.id DESC")
    List<Category> findCategoriesWithActiveProducts();
}
