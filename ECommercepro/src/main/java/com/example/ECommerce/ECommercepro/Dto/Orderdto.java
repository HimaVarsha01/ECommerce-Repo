package com.example.ECommerce.ECommercepro.Dto;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Orderdto {

    private String userId;
    private List<OrderItemdto> items;
    private double totalAmount;

    //Getters and Setters
}

