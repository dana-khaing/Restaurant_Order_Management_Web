package com.oaxaca.waiter_service.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.waiter_service.model.Order;
import com.oaxaca.waiter_service.service.WaiterOrderService;
import java.util.Map;

@RequestMapping("/waiter/order")
@RestController
public class WaiterOrderController {

    private final WaiterOrderService waiterOrderService;

    private final RestTemplate restTemplate;

    public WaiterOrderController(WaiterOrderService waiterOrderService, RestTemplate restTemplate) {
        this.waiterOrderService = waiterOrderService;
        this.restTemplate = restTemplate;
    }

    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<Map<String, ?>> confirmOrder(@PathVariable Long orderId) {


        Order order = restTemplate.getForObject("http://localhost:8080/order/" + orderId, Order.class);
        
        if (order == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order not found"));
        }

        waiterOrderService.saveOrder(order);
        waiterOrderService.sendOrderToKitchen(orderId);

        return ResponseEntity.ok(Map.of("message", "Order confirmed"));

    }

    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Map<String, ?>> cancelOrder(@PathVariable Long orderId){
        waiterOrderService.cancelOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order cancelled"));
    }

    @PostMapping("/complete/{orderId}")
    public ResponseEntity<Map<String, ?>> completeOrder(@PathVariable Long orderId){
        waiterOrderService.completeOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order completed"));
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Page<Order>>> getAllOrders(Pageable pageable){
        return ResponseEntity.ok(Map.of("orders", waiterOrderService.getAllOrders(pageable)));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<Map<String, Page<Order>>> getOrdersByStatus(@PathVariable String status, Pageable pageable){
        return ResponseEntity.ok(Map.of("orders", waiterOrderService.getOrdersByStatus(OrderStatus.valueOf(status), pageable)));
    }

    



}
