package com.example.ECommerce.ECommercepro.ServiceTest;

import com.example.ECommerce.ECommercepro.Dto.Cartdto;
import com.example.ECommerce.ECommercepro.Model.Cart;
import com.example.ECommerce.ECommercepro.Repository.CartRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceTest {

    private final CartRepo cartRepository;

    public Cart getCartByUserId(String userId) {
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        return cart.orElseGet(() -> {
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            return cartRepository.save(newCart);
        });
    }

    public Cart updateCart(Cart cart) {
        return cartRepository.save(cart);
    }

}
