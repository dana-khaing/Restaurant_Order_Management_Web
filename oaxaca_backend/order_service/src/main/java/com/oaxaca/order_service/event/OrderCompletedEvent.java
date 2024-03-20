package com.oaxaca.order_service.event;

import org.springframework.context.ApplicationEvent;

public class OrderCompletedEvent extends ApplicationEvent {
        
        private final Long orderId;
        
        public OrderCompletedEvent(Object source, Long orderId) {
            super(source);
            this.orderId = orderId;
        }
        
        public Long getOrderId() {
            return orderId;
        }
    
}
