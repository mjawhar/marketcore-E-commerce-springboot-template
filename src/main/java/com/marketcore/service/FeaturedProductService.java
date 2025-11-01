package com.marketcore.service;

import com.marketcore.model.FeaturedProduct;
import com.marketcore.model.Product;
import com.marketcore.repository.FeaturedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FeaturedProductService {

    private final FeaturedProductRepository featuredProductRepository;

    public List<FeaturedProduct> getFeaturedProductsBySection(String sectionName) {
        // explore this in complete version
    }

    public List<FeaturedProduct> getAllFeaturedProductsBySection(String sectionName) {
        // explore this in complete version
    }

    public void addFeaturedProduct(Product product, String sectionName, Integer displayOrder) {
        // explore this in complete version
    }

    public void removeFeaturedProduct(Long productId, String sectionName) {
        // explore this in complete version
    }

    public void updateDisplayOrder(Long featuredProductId, Integer newOrder) {
        // explore this in complete version
    }

    public void toggleActive(Long featuredProductId) {
        // explore this in complete version
    }

    public List<Product> getFeaturedProductsForGlowEnergySection() {
        // explore this in complete version
    }
}
