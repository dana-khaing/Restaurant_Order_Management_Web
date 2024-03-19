package com.oaxaca.cart_service.service;

import java.util.ArrayList;

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

    public Cart addCartItem(String sessionId, Cart cart, CartItem cartItem) {

        if (cart == null) {
            cart = new Cart(sessionId, new ArrayList<>());

        }

        if (sessionId == null) {
            throw new IllegalArgumentException("Session ID cannot be null");
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

        CartItem existingCartItem = (CartItem) cart.getItems().stream()
                .filter(item -> item.getProductId() == cartItem.getProductId())
                .findFirst().orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            redisTemplate.opsForValue().set(sessionId, cart);

        } else {
            cart.getItems().add(cartItem);
            redisTemplate.opsForValue().set(sessionId, cart);

        }

        return cart;

    }

    public Cart deleteItemCart(String sessionId, Cart cart, int productId) {

        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        if (sessionId == null) {
            throw new IllegalArgumentException("Session ID cannot be null");
        }

        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than 0");
        }

        cart.getItems().removeIf(item -> item.getProductId() == productId);
        redisTemplate.opsForValue().set(sessionId, cart);

        return cart;
    }

    public Cart fetchCart(String sessionId) {

        if (sessionId == null) {
            throw new IllegalArgumentException("Session ID cannot be null");
        }

        return (Cart) redisTemplate.opsForValue().get(sessionId);
    }

    @SuppressWarnings("null")
    public void modifyItemQuantity(String sessionId, Cart cart, int productId, int quantity) {

        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than 0");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        CartItem existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProductId() == productId)
                .findFirst().orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(quantity);
            redisTemplate.opsForValue().set(sessionId, cart);
        }

    }

}
