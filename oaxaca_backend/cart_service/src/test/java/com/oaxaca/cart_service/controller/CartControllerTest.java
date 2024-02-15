package com.oaxaca.cart_service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Map;
import com.oaxaca.cart_service.model.Cart;
import com.oaxaca.cart_service.service.CartService;

public class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private CartService cartService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFetchCart() {
        // Arrange
        String sessionId = "test";
        Cart mockCart = new Cart();
        when(cartService.fetchCart(sessionId)).thenReturn(mockCart);
    
        // Act
        ResponseEntity<?> result = cartController.fetchCart(sessionId);
    
        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(mockCart, ((Map<String, Cart>) result.getBody()).get("Cart: "));
    }

    @Test
    public void testFetchCartWithNullSessionIdReturnsBadRequest() {
        // Arrange
        String sessionId = null;

        // Act
        ResponseEntity<?> result = cartController.fetchCart(sessionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Session ID cannot be null", result.getBody());
    }

    @Test
    public void testFetchCartWithEmptyCartReturnsOkMessage() {
        // Arrange
        String sessionId = "test";
        when(cartService.fetchCart(sessionId)).thenReturn(null);

        // Act
        ResponseEntity<?> result = cartController.fetchCart(sessionId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(((Map) result.getBody()).containsKey("message"));
        assertEquals("Cart is empty", ((Map) result.getBody()).get("message"));
    }

    @Test
    public void testFetchCartWithExistingCartReturnsOkCart() {
        // Arrange
        String sessionId = "test";
        Cart mockCart = new Cart();
        when(cartService.fetchCart(sessionId)).thenReturn(mockCart);

        // Act
        ResponseEntity<?> result = cartController.fetchCart(sessionId);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertTrue(((Map) result.getBody()).containsKey("Cart: "));
        assertEquals(mockCart, ((Map) result.getBody()).get("Cart: "));
    }

}
