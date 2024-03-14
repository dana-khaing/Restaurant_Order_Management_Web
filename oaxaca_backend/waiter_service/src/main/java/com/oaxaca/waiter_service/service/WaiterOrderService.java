package com.oaxaca.waiter_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.waiter_service.model.Order;
import com.oaxaca.waiter_service.repository.OrderRepository;

@Service
public class WaiterOrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void cancelOrder(Long orderId) {

        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }

        Optional<Order> order = orderRepository.findById(orderId);

        if (!order.isPresent()) {
            throw new IllegalArgumentException("Order not found");
        }

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
        return orderRepository.save(orderToSend);
    }

    public void saveOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        orderRepository.save(order);
    }

    public Page<Order> getAllOrders(Pageable pageable) {
        return orderRepository.findAllByOrderByCreationDateDesc(pageable);

    }

    public Page<Order> getOrdersByStatus(OrderStatus orderStatus, Pageable pageable) {

        if (orderStatus == null || pageable == null) {
            throw new IllegalArgumentException("Order status and pageable cannot be null");
        }

        return orderRepository.findByOrderStatus(orderStatus, pageable);
    }

}
