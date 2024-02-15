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

    public Cart addCartItem(String sessionId, Cart cart, CartItem cartItem) {

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

        CartItem existingCartItem = cart.getItems().stream()
                .filter(item -> item.getProductId() == cartItem.getProductId())
                .findFirst().orElse(null);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItem.getQuantity());
            existingCartItem.setPrice(cartItem.getPrice());
            existingCartItem.setProductName(cartItem.getProductName());
            redisTemplate.opsForValue().set(sessionId, cart);

        } else {
            cart.getItems().add(cartItem);
            redisTemplate.opsForValue().set(sessionId, cart);

        }

        return cart;

    }

    public void deleteItemCart(String sessionId, Cart cart, int productId) {

        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        if (productId <= 0) {
            throw new IllegalArgumentException("Product ID must be greater than 0");
        }

        cart.getItems().removeIf(item -> item.getProductId() == productId);
        redisTemplate.opsForValue().set(sessionId, cart);
    }

    public Cart fetchCart(String sessionId) {
        return (Cart) redisTemplate.opsForValue().get(sessionId);
    }

}
