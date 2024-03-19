package com.oaxaca.order_service.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderCancelledEvent;
import com.oaxaca.order_service.event.OrderCompletedEvent;
import com.oaxaca.order_service.event.OrderCreationEvent;
import com.oaxaca.order_service.event.OrderDeliveredEvent;
import com.oaxaca.order_service.event.OrderPaidEvent;
import com.oaxaca.order_service.model.Order;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
public class WaiterWebSocketService extends TextWebSocketHandler {

    private Set<WebSocketSession> waiterSessions;
    private final OrderService orderService;
    private final ObjectMapper objectMapper;

    public WaiterWebSocketService(OrderService orderService) {
        this.orderService = orderService;
        this.waiterSessions = new CopyOnWriteArraySet<>();
        this.objectMapper = new ObjectMapper();
    }

    public void addSession(WebSocketSession session) {
        waiterSessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        waiterSessions.remove(session);
    }

    @EventListener
    public void handleOrderCreationEvent(OrderCreationEvent event) {
        // This will now update only the waiters
        Long orderId = event.getOrderId();
        Order order = orderService
                .getOrderById(orderId);

        // Update waiters when a new order is created
        try {
            if (order != null) {
                notifyWaiters(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @EventListener
    public void handleOrderCompletedEvent(OrderCompletedEvent event) {
        // Update waiters when an order is completed by the kitchen
        Long order = event.getOrderId();
        try {
            notifyWaiters(orderService.getOrderById(order));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @EventListener
    public void handleOrderCancelledEvent(OrderCancelledEvent event) {
        // Update waiters when an order is cancelled
        Long order = event.getOrderId();
        try {
            if (orderService.getOrderById(order) != null) {
                notifyWaiters(orderService.getOrderById(order));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventListener
    public void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        // Update waiters when an order is delivered
        Long order = event.getOrderId();
        try {
            notifyWaiters(orderService.getOrderById(order));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @EventListener
    public void handleOrderPaidEvent(OrderPaidEvent event) {
        // Update waiters when an order is paid
        Long order = event.getOrderId();
        try {
            notifyWaiters(orderService.getOrderById(order));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void notifyWaiters(Order order) throws IOException {
        if (order == null) {
            return;
        }

        String message = objectMapper.writeValueAsString(order);
        if (message == null || message.isEmpty())
            return;
        // Send the message to all the waiters (WebSocketSessions)

        TextMessage textMessage = new TextMessage(message);
        for (WebSocketSession session : waiterSessions) {
            if (session.isOpen()) {
                session.sendMessage(textMessage);
            }
        }
    }
}
