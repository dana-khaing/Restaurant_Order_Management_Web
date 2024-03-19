package com.oaxaca.order_service.event;

import org.springframework.context.ApplicationEvent;

public class OrderDeliveredEvent extends ApplicationEvent{

    private final Long orderId;

    public OrderDeliveredEvent(Object source, Long orderId) {
        super(source);
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    
}
