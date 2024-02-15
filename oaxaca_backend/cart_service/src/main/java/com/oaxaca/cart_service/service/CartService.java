package com.oaxaca.cart_service.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.oaxaca.cart_service.model.Cart;
import com.oaxaca.cart_service.model.CartItem;

@Service
public class CartService {

    private final RedisTemplate<String, Cart> redisTemplate;

    public CartService(RedisTemplate<String, Cart> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Cart addCartItem(Cart cart, CartItem cartItem) {

        if (cart == null) {
            cart = new Cart();

        }
        if (cartItem == null) {
            throw new IllegalArgumentException("Cart item cannot be null");
        }

        if (cartItem.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        if (cartItem.getProductId() <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than 0");
        }

        if (cartItem.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        cart.getItems().add(cartItem);
        redisTemplate.opsForValue().set("cart", cart);
        return cart;
    }

}
