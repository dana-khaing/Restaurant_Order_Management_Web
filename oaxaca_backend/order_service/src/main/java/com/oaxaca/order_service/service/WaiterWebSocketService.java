package com.oaxaca.order_service.service;

import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderCancelledEvent;
import com.oaxaca.order_service.event.OrderCompletedEvent;
import com.oaxaca.order_service.event.OrderCreationEvent;
import com.oaxaca.order_service.event.OrderDeliveredEvent;
import com.oaxaca.order_service.event.OrderPreparedEvent;
import com.oaxaca.order_service.event.OrderSentToKitchenEvent;
import com.oaxaca.order_service.model.Order;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WaiterWebSocketService extends TextWebSocketHandler {

    private Set<WebSocketSession> waiterSessions;
    private final OrderService orderService;

    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(WaiterWebSocketService.class);

    public WaiterWebSocketService(OrderService orderService, ObjectMapper objectMapper) {
        this.orderService = orderService;
        this.waiterSessions = new CopyOnWriteArraySet<>();
        this.objectMapper = objectMapper;
    }

    public void addSession(WebSocketSession session) {
        waiterSessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        waiterSessions.remove(session);
    }

    public Set<WebSocketSession> getSessions() {
        return waiterSessions;
    }

    @Override
    public void afterConnectionEstablished(@NonNull WebSocketSession session) {
        addSession(session);
    }

    @Override
    public void afterConnectionClosed(@NonNull WebSocketSession session, @NonNull CloseStatus status) {
        removeSession(session);
    }

    @EventListener
    public void handleOrderCreationEvent(OrderCreationEvent event) {
        // This will now update only the waiters
        Long orderId = event.getOrderId();
        Order order = orderService
                .getOrderById(orderId);
        logger.debug("Order created: " + order.toString());

        // Update waiters when a new order is created
        try {
            if (order != null) {
                logger.debug("Calling notifyWaiters");
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
    public void handleOrderSentToKitchenEvent(OrderSentToKitchenEvent event) {
        Long orderId = event.getOrderId();
        Order order = orderService
                .getOrderById(orderId);

        // Update waiters when a new order is sent to kitchen
        try {
            if (order != null) {
                notifyWaiters(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @EventListener
    public void handleOrderPreparedEvent(OrderPreparedEvent event) {
        Long orderId = event.getOrderId();
        Order order = orderService
                .getOrderById(orderId);

        // Update waiters when a new order is prepared
        try {
            if (order != null) {
                notifyWaiters(order);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void notifyWaiters(Order order) throws IOException {
        logger.debug("Sessions" + getSessions() + " Order: " + order.toString());
        if (order == null || waiterSessions.isEmpty()) {
            return;
        }

        logger.debug("Notifying waiters about order: " + order.toString());

        String message = objectMapper.writeValueAsString(order);
        if (message == null || message.isEmpty())
            return;
        // Send the message to all the waiters (WebSocketSessions)

        logger.debug("Message created: " + message);

        TextMessage textMessage = new TextMessage(message);
        for (WebSocketSession session : waiterSessions) {
            if (session.isOpen()) {
                logger.debug("Sending message: " + message + " " + "to waiter with id " + session.getId());
                session.sendMessage(textMessage);
            }
        }
    }
}
