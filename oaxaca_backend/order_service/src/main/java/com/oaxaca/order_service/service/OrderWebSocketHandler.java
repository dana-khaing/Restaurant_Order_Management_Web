package com.oaxaca.order_service.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.oaxaca.order_service.event.OrderStatusUpdateEvent;
import com.oaxaca.order_service.model.Order;

@Service
public class OrderWebSocketHandler extends TextWebSocketHandler implements ApplicationListener<OrderStatusUpdateEvent> {

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
        orderService.placeOrder(orderDetails);

        try {
            sendUpdatedOrders();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onApplicationEvent(@NonNull OrderStatusUpdateEvent event) {
        try {
            sendUpdatedOrders();
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

    public void sendUpdatedOrders() throws IOException {
        Pageable pageable = PageRequest.of(0, 10); // Get the first 10 orders

        Page<Order> orders = orderService.getAllOrders(pageable);
        ObjectMapper mapper = new ObjectMapper();
        String ordersJson = mapper.writeValueAsString(orders);
        if (ordersJson == null) {
            return;
        }

        TextMessage message = new TextMessage(ordersJson);

        for (WebSocketSession session : sessions) {
            session.sendMessage(message);
        }
    }

}