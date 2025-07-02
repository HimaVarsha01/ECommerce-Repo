package com.example.ECommerce.ECommercepro.Model;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private String productId;
    private String productName;  // Snapshot of product at order time
    private int quantity;
    private double price;        // Price at time of order

    //Getters and Setters

}

