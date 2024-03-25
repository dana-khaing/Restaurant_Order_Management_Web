package com.oaxaca.order_service.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderCancelledEvent;
import com.oaxaca.order_service.event.OrderCompletedEvent;
import com.oaxaca.order_service.event.OrderCreationEvent;
import com.oaxaca.order_service.event.OrderDeliveredEvent;
import com.oaxaca.order_service.event.OrderPreparedEvent;
import com.oaxaca.order_service.event.OrderSentToKitchenEvent;
import com.oaxaca.order_service.model.Order;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class WaiterWebSocketServiceTest {

    @Mock
    private OrderService orderService;
    @Mock
    private WebSocketSession session1;
    @Mock
    private WebSocketSession session2;

    @Mock
    private ObjectMapper objectMapper;

    private WaiterWebSocketService waiterWebSocketService;
    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        waiterWebSocketService = new WaiterWebSocketService(orderService);

        // Assume orderService.getOrderById always returns a valid Order object for any
        // ID
        order = new Order();
        when(orderService.getOrderById(anyLong())).thenReturn(order);

        // Set up WebSocket sessions
        waiterWebSocketService.addSession(session1);
        waiterWebSocketService.addSession(session2);

        // Assume the sessions are always open
        when(session1.isOpen()).thenReturn(true);
        when(session2.isOpen()).thenReturn(true);
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderCreationEventSendsMessages() throws IOException {
        // Given
        OrderCreationEvent event = new OrderCreationEvent(this, 1L);

        // When
        waiterWebSocketService.handleOrderCreationEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderCreationEventIgnoresClosedSessions() throws IOException {
        // Given
        OrderCreationEvent event = new OrderCreationEvent(this, 1L);
        when(session2.isOpen()).thenReturn(false);

        // When
        waiterWebSocketService.handleOrderCreationEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, never()).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderCreationEventIgnoresExceptions() throws IOException {
        // Given
        OrderCreationEvent event = new OrderCreationEvent(this, 1L);
        doThrow(new IOException()).when(session2).sendMessage(any(TextMessage.class));

        // When
        waiterWebSocketService.handleOrderCreationEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderCreationEventIgnoresNullOrder() throws IOException {
        // Given
        OrderCreationEvent event = new OrderCreationEvent(this, 1L);
        when(orderService.getOrderById(anyLong())).thenReturn(null);

        // When
        waiterWebSocketService.handleOrderCreationEvent(event);

        // Then
        verify(session1, never()).sendMessage(any(TextMessage.class));
        verify(session2, never()).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderCompletedEventSendsMessages() throws IOException {
        // When
        OrderCompletedEvent event = new OrderCompletedEvent(this, 1L);
        waiterWebSocketService.handleOrderCompletedEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderCancelledEventSendsMessages() throws IOException {
        // When
        OrderCancelledEvent event = new OrderCancelledEvent(this, 1L);
        waiterWebSocketService.handleOrderCancelledEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderDeliveredEventSendsMessages() throws IOException {
        // When
        OrderDeliveredEvent event = new OrderDeliveredEvent(this, 1L);
        waiterWebSocketService.handleOrderDeliveredEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void notifyWaitersSendsMessages() throws IOException {
        // When
        waiterWebSocketService.notifyWaiters(order);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderSentToKitchenEventSendsMessages() throws IOException {
        // Given
        OrderSentToKitchenEvent event = new OrderSentToKitchenEvent(this, 1L);

        // When
        waiterWebSocketService.handleOrderSentToKitchenEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    void handleOrderPreparedEventSendsMessages() throws IOException {
        // Given
        OrderPreparedEvent event = new OrderPreparedEvent(this, 1L);

        // When
        waiterWebSocketService.handleOrderPreparedEvent(event);

        // Then
        verify(session1, times(1)).sendMessage(any(TextMessage.class));
        verify(session2, times(1)).sendMessage(any(TextMessage.class));
    }

    @Test
    void removeSessionRemovesSession() {
        // When
        waiterWebSocketService.removeSession(session1);

        // Then
        assertFalse(waiterWebSocketService.getSessions().contains(session1));
        assertTrue(waiterWebSocketService.getSessions().contains(session2));
    }

    @SuppressWarnings("null")
    @Test
    void notifyWaitersIgnoresNullOrder() throws IOException {
        // Given
        Order nullOrder = null;

        // When
        waiterWebSocketService.notifyWaiters(nullOrder);

        // Then
        verify(session1, never()).sendMessage(any(TextMessage.class));
        verify(session2, never()).sendMessage(any(TextMessage.class));
    }

   

}
