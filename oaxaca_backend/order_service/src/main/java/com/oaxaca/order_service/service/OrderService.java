package com.oaxaca.order_service.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oaxaca.order_service.dto.CartItemDto;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.event.OrderStatusUpdateEvent;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.model.OrderItem;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public Order placeOrder(OrderDetailsDto orderDetailsDto) {

        if (orderDetailsDto == null || orderDetailsDto.getCart() == null || orderDetailsDto.getCart().getItems() == null

                || orderDetailsDto.getCart().getItems().isEmpty()) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        Order order = new Order();
        order.setCreationDate(LocalDate.now());
        order.setCustomerName(orderDetailsDto.getCustomerName());
        order.setTableNumber(orderDetailsDto.getTableNumber());
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderType(orderDetailsDto.getOrderType());

        List<OrderItem> orderItems = orderDetailsDto.getCart().getItems().stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        order.setTotal(orderItems.stream().mapToDouble(OrderItem::getPrice).sum());

        return orderRepository.save(order);
    }

    public void cancelOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        orderRepository.deleteById(orderId);
    }

    public void completeOrder(Long orderId) {

        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        Optional<Order> order = orderRepository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order orderToComplete = order.get();
        orderToComplete.setOrderStatus(OrderStatus.COMPLETED);
        orderRepository.save(orderToComplete);
    }

    public Order sendOrderToKitchen(Long orderId) {

        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        Optional<Order> order = orderRepository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order orderToSend = order.get();
        orderToSend.setOrderStatus(OrderStatus.IN_PROGRESS);
        orderRepository.save(orderToSend);
        applicationEventPublisher.publishEvent(new OrderStatusUpdateEvent(this, orderId));

        return orderToSend;
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable cannot be null");
        }

        return orderRepository.findAllByOrderByCreationDateDesc(pageable);

    }

    public Order getOrderById(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public Page<Order> getOrdersByStatus(OrderStatus orderStatus, Pageable pageable) {

        if (orderStatus == null || pageable == null) {
            throw new IllegalArgumentException("Order status and pageable cannot be null");
        }

        return orderRepository.findByOrderStatus(orderStatus, pageable);
    }

    private OrderItem convertToOrderItem(CartItemDto cartDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCalories(cartDto.getCalories());
        orderItem.setCategory(cartDto.getCategory());
        orderItem.setDescription(cartDto.getDescription());
        orderItem.setPrice(cartDto.getPrice());
        orderItem.setName(cartDto.getProductName());

        return orderItem;
    }

}
