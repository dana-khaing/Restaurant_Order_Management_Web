package com.oaxaca.order_service.service;

import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.*;
import com.oaxaca.order_service.model.Order;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CustomerWebSocketService extends TextWebSocketHandler {

    private Map<Long, WebSocketSession> customerSessions;
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public CustomerWebSocketService(OrderService orderService) {
        this.orderService = orderService;
        this.customerSessions = new ConcurrentHashMap<>();
        this.objectMapper = new ObjectMapper();
    }

    public void addSession(Long orderId, WebSocketSession session) {
        customerSessions.put(orderId, session);
    }

    public void removeSession(Long orderId) {
        customerSessions.remove(orderId);
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        // Add customer session
        URI uri = session.getUri();

        if (uri == null) {
            return;
        }

        Long orderId = Long.parseLong(uri.getPath().split("/")[2]);
        addSession(orderId, session);

    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        // Remove customer session

        URI uri = session.getUri();

        if (uri == null) {
            return;
        }

        Long orderId = Long.parseLong(uri.getPath().split("/")[2]);
        removeSession(orderId);
    }

    public Map<Long, WebSocketSession> getSessions() {
        return customerSessions;
    }

    @EventListener
    public void handleOrderCreationEvent(OrderCreationEvent event) {
        notifyCustomer(event.getOrderId());
    }

    @EventListener
    public void handleOrderCompletedEvent(OrderCompletedEvent event) {
        notifyCustomer(event.getOrderId());
    }

    @EventListener
    public void handleOrderCancelledEvent(OrderCancelledEvent event) {
        notifyCustomer(event.getOrderId());
    }

    @EventListener
    public void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        notifyCustomer(event.getOrderId());
    }

    void notifyCustomer(Long orderId) {
        Order order = orderService.getOrderById(orderId);
        if (order == null) {
            return;
        }

        String message;
        try {
            message = objectMapper.writeValueAsString(order);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        WebSocketSession session = customerSessions.get(orderId);

        if (session != null && session.isOpen() && message != null) {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}