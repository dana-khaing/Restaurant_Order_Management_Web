
package com.oaxaca.order_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.event.OrderCreationEvent;
import com.oaxaca.order_service.event.OrderStatusUpdateEvent;
import com.oaxaca.order_service.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderWebSocketHandlerTest {

    @InjectMocks
    private OrderWebSocketHandler orderWebSocketHandler;

    @Mock
    private OrderService orderService;

    @Mock
    private WebSocketSession session;

    @Mock
    private OrderStatusUpdateEvent orderStatusUpdateEvent;

    @Mock
    private OrderCreationEvent orderCreationEvent;

    @Mock
    private Order order;

    @Test
    public void testHandleOrderStatusUpdateEvent() throws Exception {
        // Arrange
        when(orderStatusUpdateEvent.getOrderId()).thenReturn(1L);
        when(orderService.getOrderById(anyLong())).thenReturn(order);
        doNothing().when(session).sendMessage(any(TextMessage.class));
        orderWebSocketHandler.afterConnectionEstablished(session);

        // Act
        orderWebSocketHandler.handleOrderStatusUpdateEvent(orderStatusUpdateEvent);

        // Assert
        verify(session, times(1)).sendMessage(any(TextMessage.class));
    }

    @Test
    public void testHandleOrderCreationEvent() throws Exception {
        // Arrange
        when(orderCreationEvent.getOrderId()).thenReturn(1L);
        when(orderService.getOrderById(anyLong())).thenReturn(order);
        doNothing().when(session).sendMessage(any(TextMessage.class));
        orderWebSocketHandler.afterConnectionEstablished(session);

        // Act
        orderWebSocketHandler.handleOrderCreationEvent(orderCreationEvent);

        // Assert
        verify(session, times(1)).sendMessage(any(TextMessage.class));
    }

    @Test
    public void testSendUpdatedOrder() throws Exception {
        // Arrange
        doNothing().when(session).sendMessage(any(TextMessage.class));
        orderWebSocketHandler.afterConnectionEstablished(session);

        // Act
        orderWebSocketHandler.sendUpdatedOrder(order);

        // Assert
        verify(session, times(1)).sendMessage(any(TextMessage.class));
    }

    @SuppressWarnings("null")
    @Test
    public void testAfterConnectionEstablished() throws Exception {
        // Act
        orderWebSocketHandler.afterConnectionEstablished(session);

        // Assert
        // No exception means success
    }

    @SuppressWarnings("null")
    @Test
    public void testAfterConnectionClosed() throws Exception {
        // Act
        orderWebSocketHandler.afterConnectionClosed(session, CloseStatus.NORMAL);

        // Assert
        // No exception means success
    }

    @SuppressWarnings({ "unused", "null" })
    @Test
    public void testSendOrderUpdate() throws Exception {
        // Arrange
        Order order = new Order();
        doNothing().when(session).sendMessage(any(TextMessage.class));

        // Simulate a connection establishment to add the session to the set
        orderWebSocketHandler.afterConnectionEstablished(session);

        // Act
        orderWebSocketHandler.sendUpdatedOrder(order);

        // Assert
        verify(session, times(1)).sendMessage(any(TextMessage.class));
    }
}
