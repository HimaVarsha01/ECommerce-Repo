package com.example.ECommerce.ECommercepro.Dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartItemdto {

    @NotBlank(message = "Product ID must not be empty")
    private String productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
}


