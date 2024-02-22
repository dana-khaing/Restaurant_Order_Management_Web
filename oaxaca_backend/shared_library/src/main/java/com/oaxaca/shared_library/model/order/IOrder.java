package com.oaxaca.shared_library.model.order;

import java.util.List;

public interface IOrder {

    Long getId();
    void setId(Long id);

    List<OrderItem> getOrderItems();
    void setOrderItems(List<OrderItem> orderItems);

    OrderType getOrderType();
    void setOrderType(OrderType orderType);

    OrderStatus getOrderStatus();
    void setOrderStatus(OrderStatus orderStatus);

    String getCustomerName();
    void setCustomerName(String customerName);
}