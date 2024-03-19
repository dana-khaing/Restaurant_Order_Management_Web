package com.oaxaca.order_service.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.service.OrderPaymentService;
import com.oaxaca.order_service.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderPaymentService orderPaymentService;

    public OrderController(OrderService orderService, OrderPaymentService orderPaymentService) {
        this.orderService = orderService;
        this.orderPaymentService = orderPaymentService;
    }

    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<Map<String, String>> cancelOrder(@PathVariable Long orderId) {

        orderService.cancelOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order cancelled successfully"));

    }

    @GetMapping("/findOrder/{orderId}")
    public ResponseEntity<Map<String, ?>> findOrder(@PathVariable Long orderId) {

        return ResponseEntity.ok(Map.of("order", orderService.getOrderById(orderId)));
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<Map<String, ?>> placeOrder(@RequestBody OrderDetailsDto orderDetailsDto) {

        return ResponseEntity.ok(Map.of("order", "Order placed successfully"));
    }

    @PutMapping("/sendOrderToKitchen/{orderId}")
    public ResponseEntity<Map<String, String>> sendOrderToKitchen(@PathVariable Long orderId) {

        orderService.sendOrderToKitchen(orderId);
        return ResponseEntity.ok(Map.of("message", "Order sent to kitchen successfully"));

    }

    @PutMapping("/deliverOrder/{orderId}")
    public ResponseEntity<Map<String, String>> deliverOrder(@PathVariable Long orderId) {

        orderService.deliverOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order delivered successfully"));

    }

    @PutMapping("/completeOrder/{orderId}")
    public ResponseEntity<Map<String, String>> completeOrder(@PathVariable Long orderId) {

        orderService.completeOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order completed successfully"));

    }

    @PutMapping("/payForOrder/{orderId}")
    public ResponseEntity<Map<String, String>> payForOrder(@PathVariable Long orderId) {

        boolean paymentStatus = orderPaymentService.payOrder(orderId);

        if (paymentStatus) {
            return ResponseEntity.ok(Map.of("message", "Order paid successfully"));
        } else {
            return ResponseEntity.ok(Map.of("message", "Order payment failed"));
        }

    }

}
