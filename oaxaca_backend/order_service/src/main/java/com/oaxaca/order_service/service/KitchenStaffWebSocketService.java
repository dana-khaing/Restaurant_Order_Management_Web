package com.oaxaca.order_service.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.event.OrderSentToKitchenEvent;
import com.oaxaca.order_service.model.Order;

@Service
public class KitchenStaffWebSocketService extends TextWebSocketHandler {

    private final ObjectMapper objectMapper;
    private Set<WebSocketSession> sessions;
    private final OrderService orderService;
    /**
     * Constructs a KitchenStaffWebSocketService with the given ObjectMapper and OrderService.
     *
     * @param objectMapper The ObjectMapper to be used for JSON serialization.
     * @param orderService The OrderService to be used for retrieving order information.
     */
    public KitchenStaffWebSocketService(ObjectMapper objectMapper, OrderService orderService) {
        this.objectMapper = objectMapper;
        this.sessions = new HashSet<>();
        this.orderService = orderService;
    }
    /**
     * Adds a WebSocket session.
     *
     * @param session The WebSocketSession to be added.
     */
    void addSession(WebSocketSession session) {
        sessions.add(session);
    }
    /**
     * Removes a WebSocket session.
     *
     * @param session The WebSocketSession to be removed.
     */
    void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
    /**
     * Retrieves all WebSocket sessions.
     *
     * @return A set containing all WebSocket sessions.
     */
    Set<WebSocketSession> getSessions() {
        return sessions;
    }
    /**
     * Handles the OrderSentToKitchenEvent by notifying kitchen staff about the new order.
     *
     * @param event The OrderSentToKitchenEvent to be handled.
     */
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
    /**
     * Notifies kitchen staff about the given order.
     *
     * @param order The order to be notified about.
     * @throws IOException if there is an error during JSON serialization.
     */
    void notifyKitchenStaff(@NonNull Order order) throws IOException {

        for (WebSocketSession session : sessions) {
            String orderString = objectMapper.writeValueAsString(order);

            if (orderString != null && !orderString.isEmpty() && session.isOpen()) {
                session.sendMessage(new TextMessage(orderString));
            }
        }
    }

}
