package com.example.ECommerce.ECommercepro.Service;

import com.example.ECommerce.ECommercepro.Dto.Orderdto;
import com.example.ECommerce.ECommercepro.Dto.OrderItemdto;
import com.example.ECommerce.ECommercepro.Exception.BadRequestException;
import com.example.ECommerce.ECommercepro.Exception.ProductNotFoundException;
import com.example.ECommerce.ECommercepro.Model.OrderItem;
import com.example.ECommerce.ECommercepro.Model.Orders;
import com.example.ECommerce.ECommercepro.Model.Product;
import com.example.ECommerce.ECommercepro.Repository.OrderRepo;
import com.example.ECommerce.ECommercepro.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepo orderRepository;
    @Autowired private ProductRepo productRepository;

    public Orders placeOrder(Orderdto orderDTO) {

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0;

        for (OrderItemdto itemDTO : orderDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

            if (product.getStock() < itemDTO.getQuantity()) {
                throw new BadRequestException("Insufficient stock for product: " + product.getName());
            }

            product.setStock(product.getStock() - itemDTO.getQuantity());
            productRepository.save(product);

            orderItems.add(OrderItem.builder()
                    .productId(product.getId())
                    .productName(product.getName())
                    .quantity(itemDTO.getQuantity())
                    .price(product.getPrice())
                    .build());

            totalAmount += product.getPrice() * itemDTO.getQuantity();
        }

        Orders order = Orders.builder()
                .userId(orderDTO.getUserId())
                .items(orderItems)
                .totalAmount(totalAmount)
                .status("PLACED")
                .orderDate(LocalDateTime.now())
                .build();

        Orders savedOrder = orderRepository.save(order);

        // Kafka event
        String event = String.format(
                "{\"event\":\"ORDER_PLACED\",\"userId\":\"%s\",\"orderId\":\"%s\",\"amount\":%.2f,\"timestamp\":\"%s\"}",
                savedOrder.getUserId(), savedOrder.getId(), savedOrder.getTotalAmount(), savedOrder.getOrderDate()
        );
        KafkaProducerService.send("order-placed", event);

        return savedOrder;
    }

    public List<Orders> getOrdersByUser(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public Orders getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    public void cancelOrder(String id) {
        Orders order = getOrderById(id);
        order.setStatus("CANCELLED");
        orderRepository.save(order);
    }
}

