package com.oaxaca.order_service.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.model.Order;

@Service
public class OrderWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();

    private final OrderService orderService;

    public OrderWebSocketHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void handleTextMessage(@NonNull WebSocketSession session, @NonNull TextMessage message)
            throws JsonMappingException, JsonProcessingException {

        String payload = message.getPayload();
        ObjectMapper mapper = new ObjectMapper();
        OrderDetailsDto orderDetails = mapper.readValue(payload, OrderDetailsDto.class);
        Order order = orderService.placeOrder(orderDetails);

        try {
            sendOrderUpdate(order);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {

        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        sessions.remove(session);
    }

    public void sendOrderUpdate(Order order) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String orderJson = mapper.writeValueAsString(order);

        if (orderJson == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(orderJson));
        }
    }

}