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
    /**
     * Constructs an {@code OrderController} with the specified services and RestTemplate.
     *
     * @param orderService        The service for handling order operations.
     * @param orderPaymentService The service for handling order payments.
     * @param restTemplate       The RestTemplate for making HTTP requests.
     */
    public OrderController(OrderService orderService, OrderPaymentService orderPaymentService,
            RestTemplate restTemplate) {
        this.orderService = orderService;
        this.orderPaymentService = orderPaymentService;
        this.restTemplate = restTemplate;
    }
    /**
     * Endpoint for cancelling an order.
     *
     * @param orderId The ID of the order to be cancelled.
     * @return A ResponseEntity containing the cancellation status.
     */
    @PutMapping("/cancel/{orderId}")
    public ResponseEntity<Map<String, String>> cancelOrder(@PathVariable Long orderId) {

        orderService.cancelOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order cancelled successfully"));

    }
    /**
     * Endpoint for finding an order by ID.
     *
     * @param orderId The ID of the order to find.
     * @return A ResponseEntity containing the order information.
     */
    @GetMapping("/findOrder/{orderId}")
    public ResponseEntity<Map<String, ?>> findOrder(@PathVariable Long orderId) {

        return ResponseEntity.ok(Map.of("order", orderService.getOrderById(orderId)));
    }
    /**
     * Endpoint for placing an order.
     *
     * @param orderDetailsDto The details of the order to be placed.
     * @param sessionId       The session ID for identifying the customer's cart.
     * @return A ResponseEntity containing the placed order.
     */
    @PostMapping("/placeOrder")
    public ResponseEntity<Map<String, ?>> placeOrder(@RequestBody OrderDetailsDto orderDetailsDto,
            @CookieValue("JSESSIONID") String sessionId) {
        Order order = orderService.placeOrder(orderDetailsDto);
        restTemplate.delete("http://localhost:8084/cart/clearCart/" + sessionId);

        return ResponseEntity.ok(Map.of("order", order));
    }
    /**
     * Endpoint for sending an order to the kitchen.
     *
     * @param orderId The ID of the order to be sent to the kitchen.
     * @return A ResponseEntity containing the status of sending the order to the kitchen.
     */
    @PutMapping("/sendOrderToKitchen/{orderId}")
    public ResponseEntity<Map<String, String>> sendOrderToKitchen(@PathVariable Long orderId) {

        orderService.sendOrderToKitchen(orderId);
        return ResponseEntity.ok(Map.of("message", "Order sent to kitchen successfully"));

    }
    /**
     * Endpoint for delivering an order.
     *
     * @param orderId The ID of the order to be delivered.
     * @return A ResponseEntity containing the status of delivering the order.
     */
    @PutMapping("/deliverOrder/{orderId}")
    public ResponseEntity<Map<String, String>> deliverOrder(@PathVariable Long orderId) {

        orderService.deliverOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order delivered successfully"));

    }
    /**
     * Endpoint for notifying that an order is prepared.
     *
     * @param orderId The ID of the order that is prepared.
     * @return A ResponseEntity containing the status of notifying that the order is prepared.
     */
    @PutMapping("/preparedOrder/{orderId}")
    public ResponseEntity<Map<String, String>> preparedOrder(@PathVariable Long orderId) {

        orderService.notifyPreparedOrder(orderId);
        return ResponseEntity.ok(Map.of("message", "Order prepared successfully"));

    }

    /**
     * Endpoint for paying for an order.
     *
     * @param orderId The ID of the order to be paid for.
     * @return A ResponseEntity containing the payment status.
     */

    @PutMapping("/payForOrder/{orderId}")
    public ResponseEntity<Map<String, String>> payForOrder(@PathVariable Long orderId) {

        boolean paymentStatus = orderPaymentService.payOrder(orderId);

        if (paymentStatus) {
            return ResponseEntity.ok(Map.of("message", "Order paid successfully"));
        } else {
            return ResponseEntity.ok(Map.of("message", "Order payment failed"));
        }

    }

    /**
     * Endpoint for fetching all orders with pagination.
     *
     * @param pageable The pageable object for pagination.
     * @return A ResponseEntity containing the paginated orders.
     */
    @GetMapping("/fetchAllPagedOrders")
    public ResponseEntity<Map<String, ?>> fetchAllOrders(Pageable pageable) {
        return ResponseEntity.ok(Map.of("orders", orderService.getAllOrdersPaged(pageable)));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<Map<String, ?>> fetchAllOrders() {
        return ResponseEntity.ok(Map.of("orders", orderService.getAllOrders()));
    }
    /**
     * Endpoint for fetching orders by status with pagination.
     *
     * @param status   The status of the orders to fetch.
     * @param pageable The pageable object for pagination.
     * @return A ResponseEntity containing the paginated orders with the specified status.
     */
    @GetMapping("/fetchOrdersByStatus/{status}")
    public ResponseEntity<Map<String, ?>> fetchOrdersByStatus(@PathVariable String status, @RequestParam Pageable pageable) {
        return ResponseEntity.ok(Map.of("orders", orderService.getOrdersByStatus(status, pageable)));
    }

}
