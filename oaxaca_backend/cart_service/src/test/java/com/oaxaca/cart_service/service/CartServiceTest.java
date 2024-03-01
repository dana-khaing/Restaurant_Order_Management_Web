package com.oaxaca.cart_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import com.oaxaca.cart_service.model.Cart;
import com.oaxaca.cart_service.model.CartItem;

public class CartServiceTest {

    @InjectMocks
    private CartService cartService;

    @Mock
    private RedisTemplate<String, Cart> redisTemplate;

    @Mock
    ValueOperations<String, Cart> valueOperations;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(redisTemplate.opsForValue()).thenReturn(valueOperations);

    }

    @Test
    public void testAddCartItem() {
        // Arrange
        Cart mockCart = mock(Cart.class);
        CartItem mockCartItem = mock(CartItem.class);
        when(mockCartItem.getQuantity()).thenReturn(1);
        when(mockCartItem.getProductId()).thenReturn(1);
        when(mockCartItem.getPrice()).thenReturn(1.0);

        @SuppressWarnings("unchecked")
        List<CartItem> mockCartItems = mock(List.class);
        when(mockCart.getItems()).thenReturn(mockCartItems);

        // Act
        Cart result = cartService.addCartItem("test",mockCart, mockCartItem);

        // Assert
        verify(mockCartItems, times(1)).add(mockCartItem);
        verify(redisTemplate.opsForValue(), times(1)).set("test", mockCart);
        assertEquals(mockCart, result);
    }

    @Test
    public void testAddCartItemWithNullCart() {
        // Arrange
        CartItem mockCartItem = mock(CartItem.class);
        when(mockCartItem.getQuantity()).thenReturn(1);
        when(mockCartItem.getProductId()).thenReturn(1);
        when(mockCartItem.getPrice()).thenReturn(1.0);

        // Act

        Cart result = cartService.addCartItem("test", null, mockCartItem);

        // Assert
        verify(redisTemplate.opsForValue(), times(1)).set("test", result);
        assertEquals(1, result.getItems().size());
    }

    @Test
    public void testAddCartItemWithNullCartItem() {
        // Arrange
        Cart mockCart = mock(Cart.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.addCartItem("test", mockCart, null);
        });
    }

    @Test
    public void testAddCartItemWithZeroQuantity() {
        // Arrange
        Cart mockCart = mock(Cart.class);
        CartItem mockCartItem = mock(CartItem.class);
        when(mockCartItem.getQuantity()).thenReturn(0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.addCartItem("test", mockCart, mockCartItem);
        });
    }

    @Test
    public void testAddCartItemWithNegativeProductId() {
        // Arrange
        Cart mockCart = mock(Cart.class);
        CartItem mockCartItem = mock(CartItem.class);
        when(mockCartItem.getQuantity()).thenReturn(1);
        when(mockCartItem.getProductId()).thenReturn(-1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.addCartItem("test", mockCart, mockCartItem);
        });
    }

    @Test
    public void testAddCartItemWithZeroPrice() {
        // Arrange
        Cart mockCart = mock(Cart.class);
        CartItem mockCartItem = mock(CartItem.class);
        when(mockCartItem.getQuantity()).thenReturn(1);
        when(mockCartItem.getProductId()).thenReturn(1);
        when(mockCartItem.getPrice()).thenReturn(0.0);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.addCartItem("test", mockCart, mockCartItem);
        });
    }

    @Test
    public void testAddCartItemWithExistingItem() {
        // Arrange
        Cart cart = new Cart();
        CartItem existingItem = new CartItem( 1, 1, 1.0, "Test Product", "vegan");

        cart.getItems().add(existingItem);

        CartItem newItem = new CartItem( 1, 2, 1.0, "Test Product", "vegan");
        
        // Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.addCartItem("test", cart, newItem);
        });
    }

    public void testDeleteItemCart(){
        // Arrange
        Cart cart = new Cart();
        CartItem existingItem = new CartItem(1, 1, 1, "Test Product", "vegan");

        cart.getItems().add(existingItem);

        // Act
        cartService.deleteItemCart("test", cart, 1);

        // Assert
        assertEquals(0, cart.getItems().size());
    }

    @Test
    public void testDeleteItemCartWithNullCart() {
        // Arrange
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.deleteItemCart("test", null, 1);
        });
    }

    @Test
    public void testDeleteItemCartWithNegativeProductId() {
        // Arrange
        Cart mockCart = mock(Cart.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.deleteItemCart("test", mockCart, -1);
        });
    }

    @Test
    public void testFetchCart(){
        // Arrange
        Cart mockCart = mock(Cart.class);
        when(redisTemplate.opsForValue().get("test")).thenReturn(mockCart);

        // Act
        Cart result = cartService.fetchCart("test");

        // Assert
        assertEquals(mockCart, result);
    }

    @Test
    public void testFetchCartWithNullCart(){
        // Arrange
        when(redisTemplate.opsForValue().get("test")).thenReturn(null);

        // Act
        Cart result = cartService.fetchCart("test");

        // Assert
        assertEquals(null, result);
    }

    @Test
    public void testFetchCartWithNullSessionId(){
        // Arrange
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.fetchCart(null);
        });
    }

    @Test
    public void testModifyExistingItemQuantity(){
        // Arrange
        Cart cart = new Cart();
        CartItem existingItem = new CartItem(1, 1, 1, "Test Product", "vegan");

        cart.getItems().add(existingItem);

        // Act
        cartService.modifyItemQuantity("test", cart, 1, 2);

        // Assert
        assertEquals(2, cart.getItems().get(0).getQuantity());
    }

    @Test
    public void testModifyItemQuantityWithNullCart(){
        // Arrange
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.modifyItemQuantity("test",null, 1, 2);
        });
    }

    @Test
    public void testModifyItemQuantityWithNegativeProductId(){
        // Arrange
        Cart mockCart = mock(Cart.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.modifyItemQuantity("test",mockCart, -1, 2);
        });
    }

    @Test
    public void testModifyItemQuantityWithZeroQuantity(){
        // Arrange
        Cart mockCart = mock(Cart.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.modifyItemQuantity("test",mockCart, 1, 0);
        });
    }

   



}
