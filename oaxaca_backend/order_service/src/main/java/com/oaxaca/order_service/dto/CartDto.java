package com.oaxaca.order_service.dto;

import java.io.Serializable;
import java.util.List;

public class CartDto implements Serializable {

    private String id;
    private List<CartItemDto> items;

    public CartDto() {
    }

    public CartDto(String id, List<CartItemDto> items) {
        this.id = id;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }
}