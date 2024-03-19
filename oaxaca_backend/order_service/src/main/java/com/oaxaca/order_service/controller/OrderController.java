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

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Map<String, String>> cancelOrder(@PathVariable(required = false) Long orderId) {

        orderService.cancelOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order cancelled successfully"));

    }

    @PostMapping("/findOrder/{orderId}")
    public ResponseEntity<Map<String, ?>> findOrder(@PathVariable(required = false) Long orderId) {

        return ResponseEntity.ok(Map.of("order", orderService.getOrderById(orderId)));
    }

    @PostMapping("/completeOrder/{orderId}")
    public ResponseEntity<Map<String, String>> completeOrder(@PathVariable(required = false) Long orderId) {

        orderService.completeOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order completed successfully"));

    }

}
