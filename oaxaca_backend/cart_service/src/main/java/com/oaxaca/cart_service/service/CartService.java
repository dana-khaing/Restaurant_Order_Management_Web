package com.oaxaca.cart_service.service;

import java.util.ArrayList;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.oaxaca.cart_service.model.Cart;
import com.oaxaca.cart_service.model.CartItem;

/**
 * Service class for managing cart operations.
 */
@Service
public class CartService {

    private final RedisTemplate<String, Cart> redisTemplate;
    
    /**
     * Constructor for CartService.
     *
     * @param redisTemplate The Redis template for accessing Redis data store.
     */

    public CartService(RedisTemplate<String, Cart> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * Adds a cart item to the cart.
     *
     * @param sessionId The session ID associated with the cart.
     * @param cart      The cart to which the item is to be added.
     * @param cartItem  The cart item to be added.
     * @return The updated cart after adding the item.
     * @throws IllegalArgumentException If session ID, cart, or cart item is null, or if quantity, product ID, or price is not valid.
     */
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
    /**
     * Deletes a cart item from the cart.
     *
     * @param sessionId The session ID associated with the cart.
     * @param cart      The cart from which the item is to be deleted.
     * @param productId The ID of the product to be deleted.
     * @return The updated cart after deleting the item.
     * @throws IllegalArgumentException If cart is null or if session ID or product ID is not valid.
     */

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
    /**
     * Fetches the cart associated with the given session ID.
     *
     * @param sessionId The session ID associated with the cart.
     * @return The cart associated with the given session ID, or an empty cart if session ID is null.
     */

    public Cart fetchCart(String sessionId) {

        if (sessionId == null) {
           return new Cart(); 
        }

        return (Cart) redisTemplate.opsForValue().get(sessionId);
    }
    /**
     * Modifies the quantity of a specific item in the cart.
     *
     * @param sessionId The session ID associated with the cart.
     * @param cart      The cart containing the item to be modified.
     * @param productId The ID of the product to be modified.
     * @param quantity  The new quantity for the item.
     * @throws IllegalArgumentException If cart is null, or if product ID or quantity is not valid.
     */

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
    /**
     * Clears the cart associated with the given session ID.
     *
     * @param sessionId The session ID associated with the cart.
     * @throws IllegalArgumentException If session ID is null.
     */

    public void clearCart(String sessionId) {

        if (sessionId == null) {
            throw new IllegalArgumentException("Session ID cannot be null");
        }

        redisTemplate.delete(sessionId);
    }

}
