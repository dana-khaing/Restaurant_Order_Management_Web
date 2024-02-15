package com.oaxaca.cart_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        @SuppressWarnings("unchecked")
        List<CartItem> mockCartItems = mock(List.class);
        when(mockCart.getItems()).thenReturn(mockCartItems);

        // Act
        Cart result = cartService.addCartItem(mockCart, mockCartItem);

        // Assert
        verify(mockCartItems, times(1)).add(mockCartItem);
        verify(redisTemplate.opsForValue(), times(1)).set("cart", mockCart);
        assertEquals(mockCart, result);
    }

}
