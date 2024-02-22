package com.oaxaca.cart_service.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;




@RedisHash("Cart")
public class Cart {

   

    @Id
    private Long id;

    private Long userId;

    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(Long userId, List<CartItem> items) {
        this.userId = userId;
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

}