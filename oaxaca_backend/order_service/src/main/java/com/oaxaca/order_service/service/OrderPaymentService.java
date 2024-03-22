package com.oaxaca.order_service.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.oaxaca.order_service.event.OrderPaidEvent;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class OrderPaymentService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Value("${stripe.api.key}")
    private String stripeApiKey;


    static {
        Stripe.apiKey = "sk_test_51Ow5wJRvay58z2uef8kWPF5aB2EUhpU5E2F4vcl9qOD9n9CjAy1q68qzb2dCWZhLqlDV5iZC7fnyEwxKZQSHt42300jgs2Po1t";
    }

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

        Order orderToPay = orderRepository.findById(orderId).orElse(null);

        if (orderToPay == null) {
            return false;
        }

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", orderToPay.getTotal());
        chargeParams.put("currency", "gbp");
        chargeParams.put("description", "Order payment");
        chargeParams.put("source", "tok_visa");

        try {
            Charge charge = Charge.create(chargeParams);

            if (charge.getPaid()) {
                order.setOrderStatus(OrderStatus.PAID);
                orderRepository.save(order);
                applicationEventPublisher.publishEvent(new OrderPaidEvent(this, order.getId()));

                return true;
            }
        } catch (StripeException e) {
            return false;
        }

        return false;

    }

}
