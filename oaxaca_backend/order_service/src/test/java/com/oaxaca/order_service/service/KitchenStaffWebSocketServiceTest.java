
package com.oaxaca.order_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderSentToKitchenEvent;
import com.oaxaca.order_service.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class KitchenStaffWebSocketServiceTest {

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private OrderService orderService;

    @Mock
    private WebSocketSession webSocketSession;

    @Mock
    private OrderSentToKitchenEvent orderSentToKitchenEvent;

    private KitchenStaffWebSocketService kitchenStaffWebSocketService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        kitchenStaffWebSocketService = new KitchenStaffWebSocketService(objectMapper, orderService);
        kitchenStaffWebSocketService.addSession(webSocketSession);
    }

    @Test
    public void testAddSession() {
        kitchenStaffWebSocketService.addSession(webSocketSession);
        assertTrue(kitchenStaffWebSocketService.getSessions().contains(webSocketSession));
    }

    @Test
    public void testRemoveSession() {
        kitchenStaffWebSocketService.removeSession(webSocketSession);
        assertFalse(kitchenStaffWebSocketService.getSessions().contains(webSocketSession));
    }

    @Test
    public void testHandleOrderSentToKitchenEvent() throws IOException {
        Order order = new Order();
        when(orderSentToKitchenEvent.getOrderId()).thenReturn(1L);
        when(orderService.getOrderById(anyLong())).thenReturn(order);
        when(objectMapper.writeValueAsString(order)).thenReturn("Order");
        when(webSocketSession.isOpen()).thenReturn(true);

        kitchenStaffWebSocketService.handleOrderSentToKitchenEvent(orderSentToKitchenEvent);

        verify(webSocketSession, times(1)).sendMessage(new TextMessage("Order"));
    }

    @Test
    public void testNotifyKitchenStaff() throws IOException {
        Order order = new Order();
        when(objectMapper.writeValueAsString(order)).thenReturn("Order");
        when(webSocketSession.isOpen()).thenReturn(true);

        kitchenStaffWebSocketService.notifyKitchenStaff(order);

        verify(webSocketSession, times(1)).sendMessage(new TextMessage("Order"));
    }
}