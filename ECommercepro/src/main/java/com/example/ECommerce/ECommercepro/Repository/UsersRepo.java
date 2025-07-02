package com.example.ECommerce.ECommercepro.Repository;



import com.example.ECommerce.ECommercepro.Model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsersRepo extends MongoRepository<Users, String> {
    Optional<Users> findByEmail(String email);
}

