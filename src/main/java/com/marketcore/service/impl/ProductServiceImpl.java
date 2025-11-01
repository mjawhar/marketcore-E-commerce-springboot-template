package com.marketcore.service.impl;

import com.marketcore.model.Product;
import com.marketcore.model.User;
import com.marketcore.repository.ProductRepository;
import com.marketcore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findBySeller(User seller) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findByCategory(Long catId) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findAllUnderCategory(Long catId){
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> search(String q) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> searchBySeller(String sellerName) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findProductsWithDiscount() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findLatest8Products() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<String> findDistinctBrands() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<String> findDistinctBrandsByCategory(Long catId) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findActiveByCategoryId(Long catId) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findAllActiveUnderCategory(Long catId) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> searchActive(String q) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findActiveProductsWithDiscount() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findLatest8ActiveProducts() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findAllActiveProducts() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<String> findDistinctActiveBrands() {
        // explore this in complete version
        return null;
    }

    @Override
    public List<String> findDistinctActiveBrandsByCategory(Long catId) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findAllActiveUnderCategorySortedByStock(Long catId) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> findActiveProductsBySellerId(Long sellerId) {
        // explore this in complete version
        return null;
    }

    @Override
    public Product save(Product product) {
        // explore this in complete version
        return null;
    }

    @Override
    public Product findById(Long id) {
        // explore this in complete version
        return null;
    }

    @Override
    public void delete(Long id) {
        // explore this in complete version
    }

    @Override
    @Transactional
    public void ajouterProduitRecommande(Long productId, Long recommendedProductId) {
        // explore this in complete version
    }

    @Override
    @Transactional
    public void supprimerProduitRecommande(Long productId, Long recommendedProductId) {
        // explore this in complete version
    }

    @Override
    public Set<Product> getProduitsQuiPourraientInteresser(Long productId) {
        // explore this in complete version
        return null;
    }

    @Override
    public List<Product> getRecommandationsAutomatiques(Long productId, int limit) {
        // explore this in complete version
        return null;
    }
}
