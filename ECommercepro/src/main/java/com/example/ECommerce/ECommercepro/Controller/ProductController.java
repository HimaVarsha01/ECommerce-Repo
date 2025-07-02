package com.example.ECommerce.ECommercepro.Controller;



import com.example.ECommerce.ECommercepro.Dto.Productdto;
import com.example.ECommerce.ECommercepro.Model.Product;
import com.example.ECommerce.ECommercepro.Service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Product createProduct(@RequestBody @Valid Productdto dto) {
        return productService.createProduct(dto);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
