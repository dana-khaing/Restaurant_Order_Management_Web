package com.oaxaca.cart_service.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.cart_service.model.Cart;
import com.oaxaca.cart_service.model.CartItem;
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

    @PostMapping("/addItem")
    public ResponseEntity<?> addItem(@RequestBody CartItem menuItem, @CookieValue("JSESSIONID") String sessionId){
        if (menuItem == null) {
            return ResponseEntity.badRequest().body("Cart item cannot be null");
        }

        if (menuItem.getQuantity() <= 0) {
            return ResponseEntity.badRequest().body("Quantity must be greater than 0");
        }

        if (menuItem.getProductId() <= 0) {
            return ResponseEntity.badRequest().body("Product ID must be greater than 0");
        }

        if (menuItem.getPrice() <= 0) {
            return ResponseEntity.badRequest().body("Price must be greater than 0");
        }
        
        Cart currentCart = cartService.fetchCart(sessionId);
        
        cartService.addCartItem(sessionId, currentCart, menuItem);

        return ResponseEntity.ok(Map.of("Cart: ", "Cart updated." ));
        
    }

    @PostMapping("/deleteItem")
    public ResponseEntity<?> deleteItem(@RequestParam int productId, @CookieValue("JSESSIONID") String sessionId) {
        Cart currentCart = cartService.fetchCart(sessionId);

        if (currentCart == null) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }


        cartService.deleteItemCart(sessionId, currentCart, productId);
        return ResponseEntity.ok(Map.of("Cart: ", "Item deleted."));
    }


}
