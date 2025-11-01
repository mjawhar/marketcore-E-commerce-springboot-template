package com.marketcore.model;

import com.marketcore.model.enums.ProductType;
import com.marketcore.model.enums.ProductStatus;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import java.math.BigDecimal;
import java.util.Set;

@Entity @Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    // Marque du produit
    private String brand;

    @Column(length=500)
    private String shortDescription;

    @Column(columnDefinition = "TEXT")
    private String description;

    private BigDecimal price;


    private String externalID;

    // Prix après réduction
    private BigDecimal discountedPrice;

    @ManyToOne
    @JsonIgnoreProperties({"products", "children", "parent"})
    private Category category;

    @ManyToOne
    @JsonIgnoreProperties({"products", "roles", "addresses", "orders"})
    private User seller;

    // Type de produit (Neuf ou d'occasion)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ProductType productType = ProductType.NEW;

    // Statut du produit (Nouveau, En cours, Validé)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ProductStatus status = ProductStatus.NEW;

    // Statut du stock
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StockStatus stock = StockStatus.INSTOCK;

    // Détermine si le produit est actif (visible sur le front)
    // NULL ou TRUE = actif, FALSE = inactif
    @Builder.Default
    private Boolean active = true;

    // Chemins des images stockées sur disque
    private String image1Path;
    private String image1Type;

    private String image2Path;
    private String image2Type;

    private String image3Path;
    private String image3Type;

    // URLs des images externes
    private String image1Url;
    private String image2Url;
    private String image3Url;

    // Produits qui pourraient intéresser (produits recommandés)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "product_recommendations",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "recommended_product_id")
    )
    @JsonIgnoreProperties({"produitsQuiPourraientInteresser", "recommandePar", "seller", "category"})
    private Set<Product> produitsQuiPourraientInteresser;

    // Produits qui recommandent ce produit (relation inverse)
    @ManyToMany(mappedBy = "produitsQuiPourraientInteresser", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"produitsQuiPourraientInteresser", "recommandePar", "seller", "category"})
    private Set<Product> recommandePar;
}
