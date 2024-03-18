
package com.oaxaca.order_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.dto.OrderDetailsDto;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderWebSocketHandlerTest {

    @InjectMocks
    private OrderWebSocketHandler orderWebSocketHandler;

    @Mock
    private OrderService orderService;

    @Mock
    private WebSocketSession session;

    @Test
    public void testHandleTextMessage() throws Exception {
        // Arrange
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        Order order = new Order();
        when(orderService.placeOrder(any(OrderDetailsDto.class))).thenReturn(order); // Use any() matcher
        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(orderDetailsDto);
        TextMessage message = new TextMessage(payload);

        // Act
        orderWebSocketHandler.handleTextMessage(session, message);

        // Assert
        verify(orderService, times(1)).placeOrder(any(OrderDetailsDto.class)); // Use any() matcher
    }

    @Test
    public void testAfterConnectionEstablished() throws Exception {
        // Act
        orderWebSocketHandler.afterConnectionEstablished(session);

        // Assert
        // No exception means success
    }

    @Test
    public void testAfterConnectionClosed() throws Exception {
        // Act
        orderWebSocketHandler.afterConnectionClosed(session, CloseStatus.NORMAL);

        // Assert
        // No exception means success
    }

    @Test
    public void testSendOrderUpdate() throws Exception {
        // Arrange
        Order order = new Order();
        doNothing().when(session).sendMessage(any(TextMessage.class));

        // Simulate a connection establishment to add the session to the set
        orderWebSocketHandler.afterConnectionEstablished(session);

        // Act
        orderWebSocketHandler.sendOrderUpdate(order);

        // Assert
        verify(session, times(1)).sendMessage(any(TextMessage.class));
    }
}
