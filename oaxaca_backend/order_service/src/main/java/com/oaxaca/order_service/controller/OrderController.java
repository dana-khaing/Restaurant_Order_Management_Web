package com.oaxaca.order_service.controller;

import com.oaxaca.order_service.service.OrderService;

public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
}
