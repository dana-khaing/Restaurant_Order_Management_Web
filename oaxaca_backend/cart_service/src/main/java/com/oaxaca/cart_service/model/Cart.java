package com.oaxaca.cart_service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;




@RedisHash("Cart")
public class Cart implements Serializable{ 


    @Id
    private String id;

    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public Cart(String id, List<CartItem> items) {
        this.items = items;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    } 

    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

}