package com.marketcore.service;

import com.marketcore.model.Cart;
import com.marketcore.model.CartItem;
import com.marketcore.model.Product;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    void addToCart(Product product, int qty);
    List<CartItem> getCartItems();
    void removeItem(Long id);
    void clear();
    
    // New methods for Cart
    Cart getCurrentCart();
    BigDecimal getCartTotalPrice();
    BigDecimal getDeliveryCost();
    void setDeliveryCost(BigDecimal deliveryCost);

    // Method to count the number of items in the cart
    int getCartItemCount();

    // Methods to update quantities
    void increaseQuantity(Long cartItemId);
    void decreaseQuantity(Long cartItemId);
}
