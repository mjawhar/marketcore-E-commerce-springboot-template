package com.marketcore.util;

import com.marketcore.model.Category;
import com.marketcore.model.Product;
import org.springframework.stereotype.Component;

@Component("urlHelper")
public class UrlHelper {

    /**
     * Génère l'URL SEO-friendly pour un produit
     * @param product Le produit
     * @return L'URL avec le nom du produit (ex: /produit/smartphone-samsung-galaxy-123)
     */
    public String productUrl(Product product) {
        if (product == null || product.getId() == null) {
            return "#";
        }
        String slug = SlugUtil.toSlugWithId(product.getTitle(), product.getId());
        return "/produit/" + slug;
    }

    /**
     * Génère l'URL SEO-friendly pour un produit à partir de son ID et titre
     * @param id L'ID du produit
     * @param title Le titre du produit
     * @return L'URL avec le nom du produit (ex: /produit/smartphone-samsung-galaxy-123)
     */
    public String productUrl(Long id, String title) {
        if (id == null || title == null) {
            return "#";
        }
        String slug = SlugUtil.toSlugWithId(title, id);
        return "/produit/" + slug;
    }

    /**
     * Génère l'URL SEO-friendly pour une catégorie
     * @param category La catégorie
     * @return L'URL avec le nom de la catégorie (ex: /categorie/electronique-123)
     */
    public String categoryUrl(Category category) {
        if (category == null || category.getId() == null) {
            return "#";
        }
        String slug = SlugUtil.toSlugWithId(category.getName(), category.getId());
        return "/categorie/" + slug;
    }
}
