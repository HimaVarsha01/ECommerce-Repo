package com.example.ECommerce.ECommercepro.Service;



import com.example.ECommerce.ECommercepro.Dto.Cartdto;
import com.example.ECommerce.ECommercepro.Dto.CartItemdto;
import com.example.ECommerce.ECommercepro.Model.Cart;
import com.example.ECommerce.ECommercepro.Model.CartItem;
import com.example.ECommerce.ECommercepro.Model.Product;
import com.example.ECommerce.ECommercepro.Repository.CartRepo;
import com.example.ECommerce.ECommercepro.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired private CartRepo cartRepository;
    @Autowired private ProductRepo productRepository;

    public Cart createOrUpdateCart(Cartdto cartDTO) {
        List<CartItem> cartItems = new ArrayList<>();
        double totalPrice = 0;

        for (CartItemdto itemDTO : cartDTO.getItems()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            int qty = itemDTO.getQuantity();
            CartItem item = new CartItem();
            item.setProductId(product.getId());
            item.setQuantity(qty);

            cartItems.add(item);
            totalPrice += product.getPrice() * qty;
        }

        Cart cart = cartRepository.findByUserId(cartDTO.getUserId())
                .orElse(new Cart());

        cart.setUserId(cartDTO.getUserId());
        cart.setItems(cartItems);
        cart.setTotalPrice(totalPrice);

        return cartRepository.save(cart);
    }

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }

    public void clearCart(String userId) {
        Cart cart = getCartByUserId(userId);
        cart.setItems(new ArrayList<>());
        cart.setTotalPrice(0);
        cartRepository.save(cart);
    }
}
