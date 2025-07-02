package com.example.ECommerce.ECommercepro.Dto;

import jakarta.validation.Valid;
import lombok.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cartdto {

    @NotBlank(message = "User ID must not be empty")
    private String userId;

    @NotEmpty(message = "Cart items list cannot be empty")
    private List<@Valid CartItemdto> items;
}


