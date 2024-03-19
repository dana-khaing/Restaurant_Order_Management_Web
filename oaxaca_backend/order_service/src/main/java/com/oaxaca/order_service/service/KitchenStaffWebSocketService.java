package com.oaxaca.order_service.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderSentToKitchenEvent;
import com.oaxaca.order_service.model.Order;

public class KitchenStaffWebSocketService extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private Set<WebSocketSession> sessions;
    private final OrderService orderService;

    public KitchenStaffWebSocketService(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.sessions = new HashSet<>();
        this.orderService = orderService;
    }

    void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }

    Set<WebSocketSession> getSessions() {
        return sessions;
    }

    @EventListener
    void handleOrderSentToKitchenEvent(OrderSentToKitchenEvent event) {
        // Update kitchen staff when a new order is created
        Long orderId = event.getOrderId();
        Order order = orderService
                .getOrderById(orderId);

        // Update kitchen staff when a new order is created
        try {
            if (order != null) {
                notifyKitchenStaff(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void notifyKitchenStaff(@NonNull Order order) throws IOException {

        for (WebSocketSession session : sessions) {
            String orderString = objectMapper.writeValueAsString(order);

            if (orderString != null && !orderString.isEmpty() && session.isOpen()) {
                session.sendMessage(new TextMessage(orderString));
            }
        }
    }

}
