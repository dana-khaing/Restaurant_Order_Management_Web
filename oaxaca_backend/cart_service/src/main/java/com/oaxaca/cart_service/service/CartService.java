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
        cart.getItems().add(cartItem);
        redisTemplate.opsForValue().set("cart", cart);
        return cart;
    }

    
    
}
