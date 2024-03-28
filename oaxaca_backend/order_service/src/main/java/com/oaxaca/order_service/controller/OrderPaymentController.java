package com.oaxaca.order_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.order_service.service.OrderPaymentService;

@RestController
@RequestMapping("/orderPayment")
public class OrderPaymentController {

    private final OrderPaymentService orderPaymentService;

    public OrderPaymentController(OrderPaymentService orderPaymentService) {
        this.orderPaymentService = orderPaymentService;
    }

    @PutMapping("/payOrder/{orderId}")
    public ResponseEntity<Map<String, Object>> payOrder(@PathVariable Long orderId) {
        Map<String, Object> response = new HashMap<>();
        if (orderId == null) {
            response.put("error", "Order ID cannot be null");
            return ResponseEntity.badRequest().body(response);
        }

        boolean paymentStatus = orderPaymentService.payOrder(orderId);

        if (paymentStatus) {
            response.put("message", "Payment successful");
            return ResponseEntity.ok(response);
        } else {
            response.put("error", "Payment failed");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
