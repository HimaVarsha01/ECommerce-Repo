package com.example.ECommerce.ECommercepro.Controller;

import com.example.ECommerce.ECommercepro.Dto.Cartdto;
import com.example.ECommerce.ECommercepro.Model.Cart;
import com.example.ECommerce.ECommercepro.Service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired private CartService cartService;

    @PostMapping("/update")
    public Cart updateCart(@RequestBody @Valid Cartdto cartDTO) {
        return cartService.createOrUpdateCart(cartDTO);
    }

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId) {
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{userId}")
    public void clearCart(@PathVariable String userId) {
        cartService.clearCart(userId);
    }
}

