package com.oaxaca.waiter_service.service;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;
import com.oaxaca.waiter_service.model.Order;
import com.oaxaca.waiter_service.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
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

    @Test
    public void testSaveOrderSuccess() {
        // Arrange
        Order order = new Order(OrderType.DINE_IN, OrderStatus.PENDING, "Jack");
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        // Act
        waiterOrderService.saveOrder(order);

        // Assert
        Mockito.verify(orderRepository).save(order);
    }

    @Test
    public void testSaveOrderNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.saveOrder(null));
    }

    @Test
    public void testGetAllOrdersSuccess() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 5);
        Page<Order> orderPage = new PageImpl<>(
                Collections.singletonList(new Order(OrderType.DINE_IN, OrderStatus.PENDING, "Jack")));
        when(orderRepository.findAllByOrderByCreationDateDesc(pageable)).thenReturn(orderPage);

        // Act
        Page<Order> result = waiterOrderService.getAllOrders(pageable);

        // Assert
        assertEquals(orderPage, result);
        Mockito.verify(orderRepository).findAllByOrderByCreationDateDesc(pageable);
    }

    @Test
    public void testCancelOrderNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.cancelOrder(null));
    }

    @Test
    public void testCompleteOrderNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.completeOrder(null));
    }

    @Test
    public void testSendOrderToKitchenNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.sendOrderToKitchen(null));
    }

    @Test
    public void testGetOrdersByStatusSuccess() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 5);
        Page<Order> orderPage = new PageImpl<>(
                Collections.singletonList(new Order(OrderType.DINE_IN, OrderStatus.PENDING, "Jack")));
        when(orderRepository.findByOrderStatus(OrderStatus.PENDING, pageable)).thenReturn(orderPage);

        // Act
        Page<Order> result = waiterOrderService.getOrdersByStatus(OrderStatus.PENDING, pageable);

        // Assert
        assertEquals(orderPage, result);
        Mockito.verify(orderRepository).findByOrderStatus(OrderStatus.PENDING, pageable);
    }

    @Test
    public void testGetOrdersByStatusNullStatus() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 5);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> waiterOrderService.getOrdersByStatus(null, pageable));
    }

    @Test
    public void testGetOrdersByStatusNullPageable() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> waiterOrderService.getOrdersByStatus(OrderStatus.PENDING, null));
    }
}
