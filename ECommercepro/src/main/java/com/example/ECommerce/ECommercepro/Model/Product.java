package com.example.ECommerce.ECommercepro.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
        @Id
        private String id;

        private String name;
        private String description;
        private double price;
        private String category;
        private int stock;

        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        //Getters and Setters
}



