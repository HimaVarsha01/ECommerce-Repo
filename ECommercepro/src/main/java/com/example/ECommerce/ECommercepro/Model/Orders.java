package com.example.ECommerce.ECommercepro.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    private String id;

    private String userId;
    private List<OrderItem> items;
    private double totalAmount;
    private String status; // e.g., "PLACED", "CANCELLED", "SHIPPED"
    private LocalDateTime orderDate;
}


