package com.example.ECommerce.ECommercepro.Controller;

import com.example.ECommerce.ECommercepro.Dto.Orderdto;
import com.example.ECommerce.ECommercepro.Model.Orders;
import com.example.ECommerce.ECommercepro.Service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @PostMapping
    public Orders placeOrder(@RequestBody @Valid Orderdto orderDTO) {
        return orderService.placeOrder(orderDTO);
    }

    @GetMapping("/user/{userId}")
    public List<Orders> getUserOrders(@PathVariable String userId) {
        return orderService.getOrdersByUser(userId);
    }

    @GetMapping("/{id}")
    public Orders getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }

    @PutMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable String id) {
        orderService.cancelOrder(id);
    }
}

