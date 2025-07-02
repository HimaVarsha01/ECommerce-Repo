package com.example.ECommerce.ECommercepro.Service;

import com.example.ECommerce.ECommercepro.Dto.Productdto;
import com.example.ECommerce.ECommercepro.Model.Product;
import com.example.ECommerce.ECommercepro.Repository.ProductRepo;
import com.example.ECommerce.ECommercepro.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product createProduct(Productdto dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setStock(dto.getStock());
        product.setCategory(dto.getCategory());

        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
}

