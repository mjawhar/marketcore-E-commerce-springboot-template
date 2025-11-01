package com.marketcore.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "featured_products")
@Data
public class FeaturedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "section_name", nullable = false)
    private String sectionName = "glow-energy";

    public FeaturedProduct() {}

    public FeaturedProduct(Product product, Integer displayOrder) {
        this.product = product;
        this.displayOrder = displayOrder;
    }
}
