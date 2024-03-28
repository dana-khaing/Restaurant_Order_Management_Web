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
import com.oaxaca.order_service.event.OrderCancelledEvent;
import com.oaxaca.order_service.event.OrderCreationEvent;
import com.oaxaca.order_service.event.OrderDeliveredEvent;
import com.oaxaca.order_service.event.OrderPreparedEvent;
import com.oaxaca.order_service.event.OrderSentToKitchenEvent;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.model.OrderItem;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    /**
     * Constructs an OrderService with the given OrderRepository and ApplicationEventPublisher.
     *
     * @param orderRepository           The OrderRepository to be used.
     * @param applicationEventPublisher The ApplicationEventPublisher to be used.
     */
    public OrderService(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }
    /**
     * Places an order with the given order details.
     *
     * @param orderDetailsDto The details of the order.
     * @return The placed order.
     */
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

        orderRepository.save(order);

        applicationEventPublisher.publishEvent(new OrderCreationEvent(this, order.getId()));

        return order;

    }

    public void cancelOrder(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);

        applicationEventPublisher.publishEvent(new OrderCancelledEvent(this, orderId));

        orderRepository.deleteById(orderId);
    } 

    public void deliverOrder(Long orderId) {

        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        Optional<Order> order = orderRepository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order orderToDeliver = order.get();
        orderToDeliver.setOrderStatus(OrderStatus.DELIVERED);
        orderRepository.save(orderToDeliver);
        applicationEventPublisher.publishEvent(new OrderDeliveredEvent(this, orderId));

    }

    public void notifyPreparedOrder(Long orderId){
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        Optional<Order> order = orderRepository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }

        Order orderToDeliver = order.get();
        orderToDeliver.setOrderStatus(OrderStatus.PREPARED);
        orderRepository.save(orderToDeliver);
        applicationEventPublisher.publishEvent(new OrderPreparedEvent(this, orderId));
    }

    public void sendOrderToKitchen(Long orderId) {

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
        applicationEventPublisher.publishEvent(new OrderSentToKitchenEvent(this, orderId));

    }

    public Page<Order> getAllOrdersPaged(Pageable pageable) {
        if (pageable == null) {
            throw new IllegalArgumentException("Pageable cannot be null");
        }

        return orderRepository.findAllByOrderByCreationDateDesc(pageable);

    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public Page<Order> getOrdersByStatus(String status, Pageable pageable) {

        if (status == null || pageable == null) {
            throw new IllegalArgumentException("Order status and pageable cannot be null");
        }

        OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());

        return orderRepository.findByOrderStatus(orderStatus, pageable);
    }

    /**
     * Converts a CartItemDto to an OrderItem.
     *
     * @param cartDto The CartItemDto to convert.
     * @return The converted OrderItem.
     */
    private OrderItem convertToOrderItem(CartItemDto cartDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setCalories(cartDto.getCalories());
        orderItem.setCategory(cartDto.getCategory());
        orderItem.setDescription(cartDto.getDescription());
        orderItem.setPrice(cartDto.getPrice());
        orderItem.setName(cartDto.getName());
        orderItem.setAllergens(cartDto.getAllergens());
        orderItem.setQuantity(cartDto.getQuantity());
        orderItem.setProductId(cartDto.getProductId());
        orderItem.setImageUrl(cartDto.getImageUrl());

        return orderItem;
    }

}
