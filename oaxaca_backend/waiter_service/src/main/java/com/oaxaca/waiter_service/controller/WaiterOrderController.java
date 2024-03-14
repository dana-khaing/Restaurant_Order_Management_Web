package com.oaxaca.waiter_service.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

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

    @Operation(summary = "Confirm order", description = "Confirms an order and sends it to the kitchen")
    @ApiResponse(responseCode = "200", description = "Order confirmed")
    @ApiResponse(responseCode = "404", description = "Order not found")
    @ApiResponse(responseCode = "400", description = "Order id cannot be null")
    @PostMapping("/confirm/{orderId}")
    public ResponseEntity<Map<String, ?>> confirmOrder(@PathVariable Long orderId) {

        if (orderId == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order id cannot be null"));
        }

        Order order = restTemplate.getForObject("http://localhost:8080/order/" + orderId, Order.class);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Order cannot be found"));
        }

        waiterOrderService.saveOrder(order);
        waiterOrderService.sendOrderToKitchen(orderId);

        return ResponseEntity.ok(Map.of("message", "Order confirmed"));

    }

    @Operation(summary = "Cancel order", description = "Cancels an order")
    @ApiResponse(responseCode = "200", description = "Order cancelled")
    @ApiResponse(responseCode = "400", description = "Order id cannot be null")
    @PostMapping("/cancel/{orderId}")
    public ResponseEntity<Map<String, ?>> cancelOrder(@PathVariable Long orderId) {

        if (orderId == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order id cannot be null"));
        }

        waiterOrderService.cancelOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order cancelled"));
    }

    @Operation(summary = "Complete order", description = "Completes an order")
    @ApiResponse(responseCode = "200", description = "Order completed")
    @ApiResponse(responseCode = "400", description = "Order id cannot be null")
    @PostMapping("/complete/{orderId}")
    public ResponseEntity<Map<String, ?>> completeOrder(@PathVariable Long orderId) {

        if (orderId == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order id cannot be null"));
        }

        waiterOrderService.completeOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order completed"));
    }

    @Operation(summary = "Get all orders", description = "Gets all orders")
    @ApiResponse(responseCode = "200", description = "Orders fetched")
    @ApiResponse(responseCode = "400", description = "Pageable cannot be null")
    @GetMapping("/all")
    public ResponseEntity<Map<String, ?>> getAllOrders(Pageable pageable) {
        if (pageable == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Pageable cannot be null"));
        }
        return ResponseEntity.ok(Map.of("orders", waiterOrderService.getAllOrders(pageable)));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get orders by status", description = "Gets all orders by status")
    @ApiResponse(responseCode = "200", description = "Orders fetched")
    @ApiResponse(responseCode = "400", description = "Status and pageable cannot be null")
    public ResponseEntity<Map<String, ?>> getOrdersByStatus(@PathVariable String status, Pageable pageable) {

        if (status == null || pageable == null) {
            return ResponseEntity.badRequest().body(Map.of("message", "Status and pageable cannot be null"));
        }

        return ResponseEntity
                .ok(Map.of("orders", waiterOrderService.getOrdersByStatus(OrderStatus.valueOf(status), pageable)));
    }

}
