package com.example.ECommerce.ECommercepro.ServiceTest;


import com.example.ECommerce.ECommercepro.Dto.Productdto;
import com.example.ECommerce.ECommercepro.Model.Product;
import com.example.ECommerce.ECommercepro.Repository.ProductRepo;
import com.example.ECommerce.ECommercepro.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProduct_ShouldSaveAndReturnProduct() {
        // Arrange
        Productdto dto = Productdto.builder()
                .name("Test Product")
                .description("Test Description")
                .price(99.99)
                .stock(10)
                .category("Test Category")
                .build();

        Product savedProduct = Product.builder()
                .id("123")
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .category(dto.getCategory())
                .build();

        when(productRepo.save(any(Product.class))).thenReturn(savedProduct);

        // Act
        Product result = productService.createProduct(dto);

        // Assert
        assertNotNull(result);
        assertEquals("123", result.getId());
        assertEquals(dto.getName(), result.getName());
        assertEquals(dto.getDescription(), result.getDescription());
        assertEquals(dto.getPrice(), result.getPrice());
        assertEquals(dto.getStock(), result.getStock());
        assertEquals(dto.getCategory(), result.getCategory());

        verify(productRepo, times(1)).save(any(Product.class));
    }
}

