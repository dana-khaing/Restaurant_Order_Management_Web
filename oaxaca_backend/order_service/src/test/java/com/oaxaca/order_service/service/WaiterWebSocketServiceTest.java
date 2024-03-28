package com.oaxaca.order_service.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderCompletedEvent;
import com.oaxaca.order_service.event.OrderCreationEvent;
import com.oaxaca.order_service.event.OrderDeliveredEvent;
import com.oaxaca.order_service.event.OrderPreparedEvent;
import com.oaxaca.order_service.event.OrderSentToKitchenEvent;
import com.oaxaca.order_service.model.Order;

@ExtendWith(MockitoExtension.class)
public class WaiterWebSocketServiceTest {

    @Mock
    private OrderService orderService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private WebSocketSession webSocketSession;

    @InjectMocks
    private WaiterWebSocketService waiterWebSocketService;

    private Order order;
    private String orderJson = "{\"id\": 1, \"details\": \"Test Order\"}";

    @BeforeEach
    public void setUp() throws JsonProcessingException {

        order = new Order();
        order.setId(1L);
 
        waiterWebSocketService.addSession(webSocketSession);
    }

    @Test
    public void testNotifyWaitersWithValidOrder() throws IOException {
        when(webSocketSession.isOpen()).thenReturn(true);
        when(objectMapper.writeValueAsString(order)).thenReturn(orderJson);
        waiterWebSocketService.notifyWaiters(order);

        verify(webSocketSession, times(1)).sendMessage(new TextMessage(orderJson));
    }

    @Test
    public void testNotifyWaitersWithNoSessions() throws Exception {
        waiterWebSocketService.removeSession(webSocketSession); // No sessions to notify

        waiterWebSocketService.notifyWaiters(order);

        verify(webSocketSession, never()).sendMessage(any(TextMessage.class));
    }

    @Test
    public void testAfterConnectionEstablishedAddsSession() {
        // Assume this session is not already added
        WebSocketSession newSession = mock(WebSocketSession.class);
        waiterWebSocketService.afterConnectionEstablished(newSession);

        assertTrue(waiterWebSocketService.getSessions().contains(newSession));
    }

    @Test
    public void testAfterConnectionClosedRemovesSession() {
        // First, ensure the session is added
        waiterWebSocketService.afterConnectionEstablished(webSocketSession);
        waiterWebSocketService.afterConnectionClosed(webSocketSession, mock(CloseStatus.class));

        assertFalse(waiterWebSocketService.getSessions().contains(webSocketSession));
    }

    @Test
    public void testHandleOrderCreationEventNotifiesWaiters() throws IOException {
        OrderCreationEvent event = new OrderCreationEvent(this, 1L);
        when(orderService.getOrderById(1L)).thenReturn(order);
        when(webSocketSession.isOpen()).thenReturn(true);   
        when(objectMapper.writeValueAsString(order)).thenReturn(orderJson);

        waiterWebSocketService.handleOrderCreationEvent(event);

        verify(orderService, times(1)).getOrderById(1L);
        verify(webSocketSession, times(1)).sendMessage(new TextMessage(orderJson));
    }

    @Test
    public void testHandleOrderInPreparationEventNotifiesWaiters() throws IOException {
        OrderSentToKitchenEvent event = new OrderSentToKitchenEvent(this, 1L);
        when(orderService.getOrderById(1L)).thenReturn(order);
        when(webSocketSession.isOpen()).thenReturn(true);   
        when(objectMapper.writeValueAsString(order)).thenReturn(orderJson);

        waiterWebSocketService.handleOrderSentToKitchenEvent(event);

        verify(orderService, times(1)).getOrderById(1L);
        verify(webSocketSession, times(1)).sendMessage(new TextMessage(orderJson));
    } 

    @Test
    public void testHandleOrderPreparedEventNotifiesWaiters() throws IOException {
        OrderPreparedEvent event = new OrderPreparedEvent(this, 1L);
        when(orderService.getOrderById(1L)).thenReturn(order);
        when(webSocketSession.isOpen()).thenReturn(true);   
        when(objectMapper.writeValueAsString(order)).thenReturn(orderJson);

        waiterWebSocketService.handleOrderPreparedEvent(event);

        verify(orderService, times(1)).getOrderById(1L);
        verify(webSocketSession, times(1)).sendMessage(new TextMessage(orderJson));
    }

    @Test
    public void testHandleOrderDeliveredEventNotifiesWaiters() throws IOException {
        OrderDeliveredEvent event = new OrderDeliveredEvent(this, 1L);
        when(orderService.getOrderById(1L)).thenReturn(order);
        when(webSocketSession.isOpen()).thenReturn(true);   
        when(objectMapper.writeValueAsString(order)).thenReturn(orderJson);

        waiterWebSocketService.handleOrderDeliveredEvent(event);

        verify(orderService, times(1)).getOrderById(1L);
        verify(webSocketSession, times(1)).sendMessage(new TextMessage(orderJson));
    }

    @Test
    public void testHandleOrderCompletedEventNotifiesWaiters() throws IOException {
        OrderCompletedEvent event = new OrderCompletedEvent(this, 1L);
        when(orderService.getOrderById(1L)).thenReturn(order);
        when(webSocketSession.isOpen()).thenReturn(true);   
        when(objectMapper.writeValueAsString(order)).thenReturn(orderJson);

        waiterWebSocketService.handleOrderCompletedEvent(event);

        verify(orderService, times(1)).getOrderById(1L);
        verify(webSocketSession, times(1)).sendMessage(new TextMessage(orderJson));
    }

    @Test
    public void testNotifyWaitersWithClosedSession() throws IOException {

        waiterWebSocketService.notifyWaiters(order);

        verify(webSocketSession, never()).sendMessage(any(TextMessage.class));
    } 

    @Test
    public void testHandleOrderCreationEventWithNullOrder() throws IOException {
        OrderCreationEvent event = new OrderCreationEvent(this, 1L);
        when(orderService.getOrderById(1L)).thenReturn(null);

        waiterWebSocketService.handleOrderCreationEvent(event);

        verify(webSocketSession, never()).sendMessage(any(TextMessage.class));
    }

}
