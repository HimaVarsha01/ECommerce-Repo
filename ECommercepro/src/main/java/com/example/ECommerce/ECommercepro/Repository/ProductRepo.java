package com.example.ECommerce.ECommercepro.Repository;

import com.example.ECommerce.ECommercepro.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends MongoRepository<Product, String> {
    // You can add custom query methods here if needed, e.g.:
    // List<Product> findByCategory(String category);
}
