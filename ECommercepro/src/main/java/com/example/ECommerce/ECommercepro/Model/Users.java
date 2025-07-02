package com.example.ECommerce.ECommercepro.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Set;

@Document(collection = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    private String id;

    private String name;
    private String email;
    private String password;

    private Set<String> roles; // e.g., Set.of("ROLE_USER", "ROLE_ADMIN")

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


