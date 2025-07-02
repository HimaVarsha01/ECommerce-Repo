package com.example.ECommerce.ECommercepro.Model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private String productId;
    private int quantity;

    //Getters and Setters
}

