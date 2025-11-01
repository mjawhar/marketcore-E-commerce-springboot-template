package com.marketcore.service;

import com.marketcore.model.Category;
import com.marketcore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        // explore this in complete version
    }

    public List<Category> findAllHierarchical() {
        // explore this in complete version
    }

    public List<Category> findByParentId(Long parentId) {
        // explore this in complete version
    }

    /**
     * Récupère la liste des catégories parentes (ancêtres) d'une catégorie, dans l'ordre hiérarchique
     * (du niveau le plus haut vers le niveau le plus bas).
     *
     * @param category La catégorie pour laquelle récupérer les ancêtres
     * @return Liste des catégories parentes, du niveau le plus haut vers le plus bas, incluant la catégorie donnée
     */
    public List<Category> getCategoryAncestors(Category category) {
        // explore this in complete version
    }
}
