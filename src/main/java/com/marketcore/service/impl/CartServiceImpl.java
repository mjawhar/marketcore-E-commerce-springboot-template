package com.marketcore.service.impl;

import com.marketcore.model.Cart;
import com.marketcore.model.CartItem;
import com.marketcore.model.Product;
import com.marketcore.model.User;
import com.marketcore.repository.CartItemRepository;
import com.marketcore.repository.CartRepository;
import com.marketcore.service.CartService;
import com.marketcore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final UserService userService;

    @Override
    @Transactional
    public void addToCart(Product product, int qty) {
        // explore this in complete version
    }

    @Override
    public List<CartItem> getCartItems() {
        // explore this in complete version
        return null;
    }

    @Override
    @Transactional
    public void removeItem(Long id) {
        // explore this in complete version
    }

    @Override
    @Transactional
    public void clear() {
        // explore this in complete version
    }
    
    @Override
    @Transactional(readOnly = true)
    public Cart getCurrentCart() {
        // explore this in complete version
        return null;
    }
    
    @Override
    public BigDecimal getCartTotalPrice() {
        // explore this in complete version
        return null;
    }
    
    @Override
    public BigDecimal getDeliveryCost() {
        // explore this in complete version
        return null;
    }
    
    @Override
    @Transactional
    public void setDeliveryCost(BigDecimal deliveryCost) {
        // explore this in complete version
    }

    @Override
    @Transactional(readOnly = true)
    public int getCartItemCount() {
        // explore this in complete version
        return 0;
    }

    @Override
    @Transactional
    public void increaseQuantity(Long cartItemId) {
        // explore this in complete version
    }

    @Override
    @Transactional
    public void decreaseQuantity(Long cartItemId) {
        // explore this in complete version
    }
}
