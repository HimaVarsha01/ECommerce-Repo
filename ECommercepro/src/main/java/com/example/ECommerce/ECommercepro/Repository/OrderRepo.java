package com.example.ECommerce.ECommercepro.Repository;

import com.example.ECommerce.ECommercepro.Model.Orders;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepo extends MongoRepository<Orders, String> {
    List<Orders> findByUserId(String userId);
}

