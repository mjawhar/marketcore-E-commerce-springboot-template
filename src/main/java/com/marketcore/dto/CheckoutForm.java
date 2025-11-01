package com.marketcore.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CheckoutForm {
    @NotBlank(message = "L'adresse est requise")
    private String shippingAddress;

    @NotBlank(message = "La ville est requise")
    private String shippingCity;
    ..
}
