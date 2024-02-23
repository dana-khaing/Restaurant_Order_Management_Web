package com.oaxaca.shared_library.model.order;

import java.util.List;


public interface IOrder {

    Long getId();
    void setId(Long id);

    List<IOrderItem> getOrderItems();
    void setOrderItems(List<IOrderItem> orderItems);

    OrderType getOrderType();
    void setOrderType(OrderType orderType);

    OrderStatus getOrderStatus();
    void setOrderStatus(OrderStatus orderStatus);

    String getCustomerName();
    void setCustomerName(String customerName);
}