package com.oaxaca.order_service.controller;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.service.OrderPaymentService;
import com.oaxaca.order_service.service.OrderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderPaymentService orderPaymentService;
    private final RestTemplate restTemplate;

    public OrderController(OrderService orderService, OrderPaymentService orderPaymentService,
            RestTemplate restTemplate) {
        this.orderService = orderService;
        this.orderPaymentService = orderPaymentService;
        this.restTemplate = restTemplate;
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
    public ResponseEntity<Map<String, ?>> placeOrder(@RequestBody OrderDetailsDto orderDetailsDto,
            @CookieValue("JSESSIONID") String sessionId) {
        Order order = orderService.placeOrder(orderDetailsDto);
        restTemplate.delete("http://localhost:8084/cart/clearCart/" + sessionId);

        return ResponseEntity.ok(Map.of("order", order));
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

    @PutMapping("/preparedOrder/{orderId}")
    public ResponseEntity<Map<String, String>> preparedOrder(@PathVariable Long orderId) {

        orderService.notifyPreparedOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order prepared successfully"));

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
    @GetMapping("/fetchAllPagedOrders")
    public ResponseEntity<Map<String, ?>> fetchAllOrders(Pageable pageable) {
        return ResponseEntity.ok(Map.of("orders", orderService.getAllOrdersPaged(pageable)));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<Map<String, ?>> fetchAllOrders() {
        return ResponseEntity.ok(Map.of("orders", orderService.getAllOrders()));
    }
    @GetMapping("/fetchOrdersByStatus/{status}")
    public ResponseEntity<Map<String, ?>> fetchOrdersByStatus(@PathVariable String status, @RequestParam Pageable pageable) {
        return ResponseEntity.ok(Map.of("orders", orderService.getOrdersByStatus(status, pageable)));
    }

}
