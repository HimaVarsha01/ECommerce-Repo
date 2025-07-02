package com.example.ECommerce.ECommercepro.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "carts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    private String id;

    private String userId;
    private List<CartItem> items;
    private double totalPrice;

    //Getters and setters
}

