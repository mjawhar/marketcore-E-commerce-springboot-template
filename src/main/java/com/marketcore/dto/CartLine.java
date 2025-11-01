package com.marketcore.dto;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class CartLine {
    private Long productId;
    private String title;
    private BigDecimal priceUnit;
    private int qty;

    public BigDecimal getTotal(){ return priceUnit.multiply(BigDecimal.valueOf(qty)); }
}
