package com.oaxaca.waiter_service.service;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;
import com.oaxaca.waiter_service.model.Order;
import com.oaxaca.waiter_service.repository.OrderRepository;

import jakarta.persistence.Enumerated;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WaiterOrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private WaiterOrderService waiterOrderService;

    @Test
    public void testCancelOrderSuccess() {
        // Arrange
        Order order = new Order(OrderType.DINE_IN, OrderStatus.IN_PROGRESS, "Jack");
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        waiterOrderService.cancelOrder(1L);

        // Assert
        Mockito.verify(orderRepository).deleteById(1L);
    }

    @Test
    public void testCancelOrderNotFound() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.cancelOrder(1L));
    }

    @Test
    public void testCompleteOrderSuccess() {
        // Arrange
        Order order = new Order(OrderType.DINE_IN, OrderStatus.IN_PROGRESS, "Jack");
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        waiterOrderService.completeOrder(1L);

        // Assert
        assertEquals(OrderStatus.COMPLETED, order.getOrderStatus());
        Mockito.verify(orderRepository).save(order);
    }

    @Test
    public void testCompleteOrderNotFound() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.completeOrder(1L));
    }

    @Test
    public void testSendOrderToKitchenSuccess() {
        // Arrange
        Order order = new Order(OrderType.DINE_IN, OrderStatus.PENDING, "Jack");
        order.setId(1L);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        // Act
        waiterOrderService.sendOrderToKitchen(1L);

        // Assert
        assertEquals(OrderStatus.IN_PROGRESS, order.getOrderStatus());
        Mockito.verify(orderRepository).save(order);
    }

    @Test
    public void testSendOrderToKitchenNotFound() {
        // Arrange
        when(orderRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.sendOrderToKitchen(1L));
    }
}
