package com.oaxaca.waiter_service.model;

import java.util.List;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    private String customerName;

    private LocalDate creationDate;

    @ElementCollection
    @Embedded
    private List<OrderItem> orderItems;

    public Order() {

    }
    /**
     * Constructs an Order instance with specified attributes.
     * 
     * @param orderType     The type of the order.
     * @param orderStatus   The status of the order.
     * @param customerName  The name of the customer who placed the order.
     */
    public Order(OrderType orderType, OrderStatus orderStatus, String customerName) {
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.customerName = customerName;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        return "Order [customerName=" + customerName + ", id=" + id + ", orderItems=" + orderItems + ", orderStatus="
                + orderStatus + ", orderType=" + orderType + "]";
    }

}
