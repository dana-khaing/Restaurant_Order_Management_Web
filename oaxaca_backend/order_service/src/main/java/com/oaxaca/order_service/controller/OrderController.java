package com.oaxaca.order_service.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/cancel")
    public ResponseEntity<Map<String, String>> cancelOrder(@PathVariable Long orderId) {

        if (orderId == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order id cannot be null"));
        }

        orderService.cancelOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order cancelled successfully"));

    }

    @PostMapping("/findOrder/{id}")
    public ResponseEntity<Map<String, ?>> findOrder(@PathVariable Long orderId) {

        if (orderId == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order id cannot be null"));
        }

        return ResponseEntity.ok(Map.of("order", orderService.getOrderById(orderId)));
    }

    @PostMapping("/completeOrder/{id}")
    public ResponseEntity<Map<String, String>> completeOrder(@PathVariable Long orderId) {

        if (orderId == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order id cannot be null"));
        }

        orderService.completeOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order completed successfully"));

    }

}
