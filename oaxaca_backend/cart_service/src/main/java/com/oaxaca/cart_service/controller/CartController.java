package com.oaxaca.cart_service.controller;

import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.cart_service.model.Cart;
import com.oaxaca.cart_service.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/fetch")
    public ResponseEntity<?> fetchCart(@RequestParam String sessionId) {

        if (sessionId == null) {
            return ResponseEntity.badRequest().body("Session ID cannot be null");
        }

        Cart cart = cartService.fetchCart(sessionId);

        if (cart == null) {
            return ResponseEntity.ok(Map.of("message", "Cart is empty"));
        }

        return ResponseEntity.ok(Map.of("Cart: ", cart));

    }

}
