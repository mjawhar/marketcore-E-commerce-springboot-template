package com.marketcore.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "delivery_modes")
public class DeliveryMode {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    ...
    private boolean active = true;
}