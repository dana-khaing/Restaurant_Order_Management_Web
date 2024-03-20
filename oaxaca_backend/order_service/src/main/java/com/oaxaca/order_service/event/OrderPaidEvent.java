package com.oaxaca.order_service.event;

import org.springframework.context.ApplicationEvent;

public  class OrderPaidEvent extends ApplicationEvent{
        
        private final Long orderId;
        
        public OrderPaidEvent(Object source, Long orderId) {
            super(source);
            this.orderId = orderId;
        }
        
        public Long getOrderId() {
            return orderId;
        }
    
}
