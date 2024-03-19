package com.oaxaca.order_service.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.oaxaca.order_service.event.OrderPaidEvent;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;

@Service
public class OrderPaymentService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public OrderPaymentService(OrderRepository orderRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.orderRepository = orderRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public boolean payOrder(Long orderId) {
        // 1. Validate the payment information
        // 2. Charge the payment method
        // 3. Update the order status
        // 4. Return the payment status

        if (orderId == null) {
            return false;
        }

        Order order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            return false;
        }

        order.setOrderStatus(OrderStatus.PAID);
        orderRepository.save(order);
        applicationEventPublisher.publishEvent(new OrderPaidEvent(this, order.getId()));
        
        return true;

    }

}
