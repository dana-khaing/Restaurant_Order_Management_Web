package com.oaxaca.order_service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.stereotype.Service;

import com.oaxaca.order_service.dto.CartDto;
import com.oaxaca.order_service.dto.CartItemDto;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.model.OrderItem;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order placeOrder(OrderDetailsDto orderDetailsDto) {

        if (orderDetailsDto.getCart() == null || orderDetailsDto.getCart().getItems() == null
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

        return orderRepository.save(order);
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
