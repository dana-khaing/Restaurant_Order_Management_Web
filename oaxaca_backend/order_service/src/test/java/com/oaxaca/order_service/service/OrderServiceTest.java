package com.oaxaca.order_service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.oaxaca.order_service.dto.CartDto;
import com.oaxaca.order_service.dto.CartItemDto;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    ApplicationEventPublisher applicationEventPublisher;

    @SuppressWarnings("null")
    @Test
    void testPlaceOrder() {
        // Arrange
        CartItemDto cartItemDto = new CartItemDto("Pizza", "Delicious pizza", 200, 15.5f, new ArrayList<>(), 1, 3, "image");
        CartItemDto cartItemDto2 = new CartItemDto("Soda", "Refreshing soda", 100, 2.5f, new ArrayList<>(), 2, 2, "image");
        ArrayList<CartItemDto> items = new ArrayList<>();
        items.add(cartItemDto);
        items.add(cartItemDto2);
        CartDto cartDto = new CartDto("Customer", items);
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto("Customer", 1, cartDto, OrderType.DINE_IN.name());

        Order mockedOrder = new Order();
        mockedOrder.setId(1L);
        mockedOrder.setCustomerName("Customer");
        mockedOrder.setCreationDate(LocalDate.now());
        mockedOrder.setOrderStatus(OrderStatus.PENDING);
        mockedOrder.setOrderType(OrderType.DINE_IN);

        when(orderRepository.save(any(Order.class))).thenReturn(mockedOrder);

        // Act
        orderService.placeOrder(orderDetailsDto);

        // Assert
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testPlaceOrderWithEmptyCart() {
        // Arrange
        CartDto cartDto = new CartDto("Customer", new ArrayList<>());
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto("Customer", 1, cartDto, OrderType.DINE_IN.name());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeOrder(orderDetailsDto);
        });

        String expectedMessage = "Cart cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCancelOrderNotFound() {
        // Arrange
        Long orderId = 999L;
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.cancelOrder(orderId);
        });

        String expectedMessage = "Order not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @SuppressWarnings("null")
    @Test
    void testCompleteOrderSuccess() {
        // Arrange
        Long orderId = 1L;
        Order mockedOrder = new Order();
        mockedOrder.setId(orderId);
        mockedOrder.setOrderStatus(OrderStatus.PENDING);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockedOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(mockedOrder);

        // Act
        orderService.completeOrder(orderId);

        // Assert
        assertEquals(OrderStatus.COMPLETED, mockedOrder.getOrderStatus());
    }

    @SuppressWarnings("null")
    @Test
    void testSendOrderToKitchen() {
        // Arrange
        Long orderId = 1L;
        Order orderToSend = new Order();
        orderToSend.setId(orderId);
        orderToSend.setOrderStatus(OrderStatus.PENDING);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderToSend));
        when(orderRepository.save(any(Order.class))).thenReturn(orderToSend);

        // Act
        orderService.sendOrderToKitchen(orderId);

        // Assert
        verify(orderRepository, times(1)).save(any(Order.class));
        assertEquals(OrderStatus.IN_PROGRESS, orderToSend.getOrderStatus());
    }

    @Test
    void testGetAllOrders() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
        Page<Order> mockedPage = mock(Page.class);
        when(orderRepository.findAllByOrderByCreationDateDesc(pageable)).thenReturn(mockedPage);

        // Act
        Page<Order> result = orderService.getAllOrders(pageable);

        // Assert
        assertNotNull(result);
        verify(orderRepository, times(1)).findAllByOrderByCreationDateDesc(pageable);
    }

    @Test
    void testGetOrdersByStatus() {
        // Arrange
        OrderStatus status = OrderStatus.PENDING;
        Pageable pageable = mock(Pageable.class);
        @SuppressWarnings("unchecked")
        Page<Order> mockedPage = mock(Page.class);
        when(orderRepository.findByOrderStatus(status, pageable)).thenReturn(mockedPage);

        // Act
        Page<Order> result = orderService.getOrdersByStatus(status, pageable);

        // Assert
        assertNotNull(result);
        verify(orderRepository, times(1)).findByOrderStatus(status, pageable);

    }

    @Test
    void testPlaceOrderWithNullOrderId() {
        // Arrange
        OrderDetailsDto orderDetailsDto = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.placeOrder(orderDetailsDto);
        });

        String expectedMessage = "Cart cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCancelOrderWithNullOrderId() {
        // Arrange
        Long orderId = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.cancelOrder(orderId);
        });

        String expectedMessage = "Order id cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testCompleteOrderWithNullOrderId() {
        // Arrange
        Long orderId = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.completeOrder(orderId);
        });

        String expectedMessage = "Order id cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testSendOrderToKitchenWithNullOrderId() {
        // Arrange
        Long orderId = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.sendOrderToKitchen(orderId);
        });

        String expectedMessage = "Order id cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetAllOrdersWithNullPageable() {
        // Arrange
        Pageable pageable = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.getAllOrders(pageable);
        });

        String expectedMessage = "Pageable cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testGetOrdersByStatusWithNullArguments() {
        // Arrange
        OrderStatus status = null;
        Pageable pageable = null;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrdersByStatus(status, pageable);
        });

        String expectedMessage = "Order status and pageable cannot be null";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
