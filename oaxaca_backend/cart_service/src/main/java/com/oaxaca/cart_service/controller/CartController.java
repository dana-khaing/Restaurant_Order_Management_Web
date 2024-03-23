package com.oaxaca.cart_service.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.cart_service.model.Cart;
import com.oaxaca.cart_service.model.CartItem;
import com.oaxaca.cart_service.service.CartService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/fetch")
    @Operation(summary = "Fetch cart", description = "Fetches the cart for the current session")
    @ApiResponse(responseCode = "200", description = "Cart fetched")
    @ApiResponse(responseCode = "400", description = "Session ID cannot be null")
    public ResponseEntity<?> fetchCart(@CookieValue("JSESSIONID") String sessionId) {

        if (sessionId == null) {
            return ResponseEntity.badRequest().body("Session ID cannot be null");
        }

        Cart cart = cartService.fetchCart(sessionId);

        if (cart == null) {
            return ResponseEntity.ok(Map.of("message", "Cart is empty"));
        }

        return ResponseEntity.ok(Map.of("cart", cart));

    }

    @PostMapping("/addItem")
    @Operation(summary = "Add item to cart", description = "Adds an item to the cart for the current session")
    @ApiResponse(responseCode = "200", description = "Cart updated")
    @ApiResponse(responseCode = "400", description = "Cart item cannot be null")
    public ResponseEntity<?> addItem(@RequestBody CartItem menuItem, @CookieValue("JSESSIONID") String sessionId) {
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

        return ResponseEntity.ok(Map.of("Cart: ", "Cart updated."));

    }

    @DeleteMapping("/deleteItem")
    @Operation(summary = "Delete item from cart", description = "Deletes an item from the cart for the current session")
    @ApiResponse(responseCode = "200", description = "Item deleted")
    @ApiResponse(responseCode = "400", description = "Cart is empty")
    public ResponseEntity<?> deleteItem(@RequestParam int productId, @CookieValue("JSESSIONID") String sessionId) {
        Cart currentCart = cartService.fetchCart(sessionId);

        if (currentCart == null) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        cartService.deleteItemCart(sessionId, currentCart, productId);
        return ResponseEntity.ok(Map.of("Cart: ", "Item deleted."));
    }

    @PutMapping("/modifyItemQuantity/{productId}")
    @Operation(summary = "Modify item quantity", description = "Modifies the quantity of an item in the cart for the current session")
    @ApiResponse(responseCode = "200", description = "Item quantity modified")
    @ApiResponse(responseCode = "400", description = "Cart is empty")
    public ResponseEntity<?> modifyItemQuantity(@RequestParam int quantity, @RequestParam int productId,
            @CookieValue("JSESSIONID") String sessionId) {
        Cart currentCart = cartService.fetchCart(sessionId);

        if (currentCart == null) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        if (quantity <= 0) {
            return ResponseEntity.badRequest().body("Quantity must be greater than 0");
        }

        cartService.modifyItemQuantity(sessionId, currentCart, productId, quantity);
        return ResponseEntity.ok(Map.of("Cart: ", "Item quantity modified."));
    }

    @DeleteMapping("/clearCart/{sessionId}")
    @Operation(summary = "Clear cart", description = "Clears the cart for the current session")
    @ApiResponse(responseCode = "200", description = "Cart cleared")
    @ApiResponse(responseCode = "400", description = "Cart is empty")
    public ResponseEntity<?> clearCart(@PathVariable String sessionId) {

        cartService.clearCart(sessionId);
        return ResponseEntity.ok(Map.of("Cart: ", "Cart cleared."));
    }

}
