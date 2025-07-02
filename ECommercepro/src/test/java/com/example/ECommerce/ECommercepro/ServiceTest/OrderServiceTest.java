package com.example.ECommerce.ECommercepro.ServiceTest;



import com.example.ECommerce.ECommercepro.Dto.Orderdto;
import com.example.ECommerce.ECommercepro.Dto.OrderItemdto;
import com.example.ECommerce.ECommercepro.Exception.BadRequestException;
import com.example.ECommerce.ECommercepro.Exception.ProductNotFoundException;
import com.example.ECommerce.ECommercepro.Model.Orders;
import com.example.ECommerce.ECommercepro.Model.Product;
import com.example.ECommerce.ECommercepro.Repository.OrderRepo;
import com.example.ECommerce.ECommercepro.Repository.ProductRepo;
import com.example.ECommerce.ECommercepro.Service.OrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepo orderRepository;

    @Mock
    private ProductRepo productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPlaceOrder_Success() {
        // Prepare input DTO with one order item
        OrderItemdto itemdto = OrderItemdto.builder()
                .productId("prod123")
                .quantity(2)
                .build();

        Orderdto orderDto = new Orderdto();
        orderDto.setUserId("user123");
        orderDto.setItems(List.of(itemdto));

        // Prepare product in DB
        Product product = Product.builder()
                .id("prod123")
                .name("Test Product")
                .price(100.0)
                .stock(5)
                .build();

        when(productRepository.findById("prod123")).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        when(orderRepository.save(any(Orders.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Call service
        Orders placedOrder = orderService.placeOrder(orderDto);

        // Verify
        assertEquals("user123", placedOrder.getUserId());
        assertEquals(1, placedOrder.getItems().size());
        assertEquals("PLACED", placedOrder.getStatus());
        assertEquals(200.0, placedOrder.getTotalAmount());
        assertNotNull(placedOrder.getOrderDate());

        // Stock should be reduced
        assertEquals(3, product.getStock());

        verify(productRepository, times(1)).save(product);
        verify(orderRepository, times(1)).save(any(Orders.class));
    }

    @Test
    public void testPlaceOrder_ProductNotFound() {
        OrderItemdto itemdto = OrderItemdto.builder()
                .productId("prod123")
                .quantity(2)
                .build();

        Orderdto orderDto = new Orderdto();
        orderDto.setUserId("user123");
        orderDto.setItems(List.of(itemdto));

        when(productRepository.findById("prod123")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> orderService.placeOrder(orderDto));
    }

    @Test
    public void testPlaceOrder_InsufficientStock() {
        OrderItemdto itemdto = OrderItemdto.builder()
                .productId("prod123")
                .quantity(10)  // more than available stock
                .build();

        Orderdto orderDto = new Orderdto();
        orderDto.setUserId("user123");
        orderDto.setItems(List.of(itemdto));

        Product product = Product.builder()
                .id("prod123")
                .name("Test Product")
                .price(100.0)
                .stock(5)
                .build();

        when(productRepository.findById("prod123")).thenReturn(Optional.of(product));

        assertThrows(BadRequestException.class, () -> orderService.placeOrder(orderDto));
    }
}
