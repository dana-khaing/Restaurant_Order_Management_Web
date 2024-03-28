
package com.oaxaca.order_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.*;
import com.oaxaca.order_service.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CustomerWebSocketServiceTest {

    @Mock
    private OrderService orderService;

    @Mock
    private WebSocketSession webSocketSession;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private CustomerWebSocketService customerWebSocketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addSession_AddsSessionToMap() throws IOException {
        when(webSocketSession.isOpen()).thenReturn(true);
        customerWebSocketService.addSession(1L, webSocketSession);
        customerWebSocketService.notifyCustomer(1L);
        assertTrue(customerWebSocketService.getSessions().containsKey(1L)); 
    }

    @Test
    void removeSession_RemovesSessionFromMap() throws IOException {
        customerWebSocketService.addSession(1L, webSocketSession);
        customerWebSocketService.removeSession(1L);
        customerWebSocketService.notifyCustomer(1L);
        assertFalse(customerWebSocketService.getSessions().containsKey(1L));
    }

  

    @Test
    void handleOrderCompletedEvent_NotifiesCustomer() {
        customerWebSocketService.handleOrderCompletedEvent(new OrderCompletedEvent(this, 1L));
        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    void handleOrderCancelledEvent_NotifiesCustomer() {
        customerWebSocketService.handleOrderCancelledEvent(new OrderCancelledEvent(this, 1L));
        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    void handleOrderDeliveredEvent_NotifiesCustomer() {
        customerWebSocketService.handleOrderDeliveredEvent(new OrderDeliveredEvent(this, 1L));
        verify(orderService, times(1)).getOrderById(1L);
    }


    @SuppressWarnings("null")
    @Test
    void notifyCustomer_SendsMessageToCustomer() throws IOException {
        Order order = new Order();
        when(orderService.getOrderById(any(Long.class))).thenReturn(order);
        when(objectMapper.writeValueAsString(any())).thenReturn("message");
        when(webSocketSession.isOpen()).thenReturn(true);
        customerWebSocketService.addSession(1L, webSocketSession);
        customerWebSocketService.notifyCustomer(1L);
        verify(webSocketSession, times(1)).sendMessage(any(TextMessage.class));
    }
}