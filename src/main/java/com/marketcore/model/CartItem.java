package com.marketcore.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne private Cart cart;
    @ManyToOne private Product product;
    private int quantity;
    private BigDecimal price;
    
    @Transient
    public User getBuyer() {
        return cart != null ? cart.getUser() : null;
    }
}
