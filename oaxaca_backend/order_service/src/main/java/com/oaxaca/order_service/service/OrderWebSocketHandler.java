package com.oaxaca.order_service.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderCreationEvent;
import com.oaxaca.order_service.event.OrderStatusUpdateEvent;
import com.oaxaca.order_service.model.Order;

@Service
public class OrderWebSocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();

    private final OrderService orderService;

    public OrderWebSocketHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        sessions.remove(session);
    }

    @EventListener
    public void handleOrderStatusUpdateEvent(OrderStatusUpdateEvent event) {
        try {
            Long orderId = event.getOrderId();
            Order order = orderService.getOrderById(orderId);
            sendUpdatedOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventListener
    public void handleOrderCreationEvent(OrderCreationEvent event) {
        try {
            Long orderId = event.getOrderId();
            Order order = orderService.getOrderById(orderId);
            sendUpdatedOrder(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void sendUpdatedOrder(Order order) throws IOException {
        String message = new ObjectMapper().writeValueAsString(order);
        TextMessage textMessage = new TextMessage(message);

        for (WebSocketSession session : sessions) {
            session.sendMessage(textMessage);
        }
    }
}