package com.marketcore.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ProductSearchDto {
    private Long id;
    private String title;
    private String brand;
    private String shortDescription;
    private BigDecimal price;
    private BigDecimal discountedPrice;
    private String categoryName;
    private String sellerName;
    private String image1Path;
    private String image1Url;
    private Boolean active;
}
