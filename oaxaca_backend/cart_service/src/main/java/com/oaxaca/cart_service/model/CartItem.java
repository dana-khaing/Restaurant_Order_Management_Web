package com.oaxaca.cart_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("CartItem")
public class CartItem {

  

    @Id
    private int id;
    private int productId;
    private int quantity;
    private double price;
    private String productName;
    private String dietaryRequirement;

    public CartItem(int id, int productId, int quantity, double price, String productName, String dietaryRequirement) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.productName = productName;
        this.dietaryRequirement = dietaryRequirement;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getProductName() {
        return productName;
    }

    public String getDietaryRequirement() {
        return dietaryRequirement;
    }

    public void setPrice(double d) {
        this.price = d;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setDietaryRequirement(String dietaryRequirement) {
        this.dietaryRequirement = dietaryRequirement;
    }



}
