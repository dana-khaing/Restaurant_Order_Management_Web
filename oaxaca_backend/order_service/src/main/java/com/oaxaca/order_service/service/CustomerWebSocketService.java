package com.oaxaca.order_service.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.*;
import com.oaxaca.order_service.model.Order;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * Constructs a CustomerWebSocketService with the given OrderService.
 *
 * @param orderService The OrderService to be used for retrieving order information.
 */
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
    /**
     * Adds a WebSocket session associated with the given order ID.
     *
     * @param orderId The ID of the order.
     * @param session The WebSocketSession to be added.
     */
    public void addSession(Long orderId, WebSocketSession session) {
        customerSessions.put(orderId, session);
    }
    /**
     * Removes the WebSocket session associated with the given order ID.
     *
     * @param orderId The ID of the order.
     */
    public void removeSession(Long orderId) {
        customerSessions.remove(orderId);
    }
    /**
     * Retrieves all WebSocket sessions associated with orders.
     *
     * @return A map containing order IDs as keys and WebSocket sessions as values.
     */
    public Map<Long, WebSocketSession> getSessions() {
        return customerSessions;
    }
    /**
     * Handles the OrderCreationEvent by notifying the customer about the new order.
     *
     * @param event The OrderCreationEvent to be handled.
     */
    @EventListener
    public void handleOrderCreationEvent(OrderCreationEvent event) {
        notifyCustomer(event.getOrderId());
    }
    /**
     * Handles the OrderCompletedEvent by notifying the customer about the completed order.
     *
     * @param event The OrderCompletedEvent to be handled.
     */
    @EventListener
    public void handleOrderCompletedEvent(OrderCompletedEvent event) {
        notifyCustomer(event.getOrderId());
    }
    /**
     * Handles the OrderCancelledEvent by notifying the customer about the cancelled order.
     *
     * @param event The OrderCancelledEvent to be handled.
     */
    @EventListener
    public void handleOrderCancelledEvent(OrderCancelledEvent event) {
        notifyCustomer(event.getOrderId());
    }
    /**
     * Handles the OrderDeliveredEvent by notifying the customer about the delivered order.
     *
     * @param event The OrderDeliveredEvent to be handled.
     */
    @EventListener
    public void handleOrderDeliveredEvent(OrderDeliveredEvent event) {
        notifyCustomer(event.getOrderId());
    }

    /**
     * Notifies the customer associated with the given order ID about the order.
     *
     * @param orderId The ID of the order.
     */
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