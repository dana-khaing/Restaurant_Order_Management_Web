package com.oaxaca.order_service.dto;

import com.oaxaca.shared_library.model.order.OrderType;

public class OrderDetailsDto {

    private String customerName;
    private int tableNumber;
    private OrderType orderType;

    private CartDto cart;
    /**
     * Constructs an {@code OrderDetailsDto} with the specified parameters.
     *
     * @param customerName The name of the customer placing the order.
     * @param tableNumber  The table number where the order is placed.
     * @param cart         The cart containing items in the order.
     * @param orderType    The type of the order.
     */
    public OrderDetailsDto(String customerName, int tableNumber, CartDto cart, String orderType) {
        this.customerName = customerName;
        this.tableNumber = tableNumber;
        this.cart = cart;
        this.orderType = OrderType.valueOf(orderType.toUpperCase());
    }

    public OrderDetailsDto() {
       
    }
    /**
     * Retrieves the name of the customer who placed the order.
     *
     * @return The name of the customer.
     */
    public String getCustomerName() {
        return customerName;
    }
    /**
     * Sets the name of the customer who placed the order.
     *
     * @param customerName The name of the customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

    public OrderType getOrderType() {
        return orderType;
    }
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    
}
