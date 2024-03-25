package com.oaxaca.order_service.dto;

import java.io.Serializable;
import java.util.List;


public class CartItemDto implements Serializable {

    private String name;
    private int category;
    private float price;
    private List<String> allergens;
    private int calories;
    private String description;
    private int quantity;
    private String imageUrl;
    private Long productId;

    public CartItemDto() {
    }

    public CartItemDto(String name, String description, int category, float price, List<String> allergens, int calories, int quantity, String imageUrl, Long productId) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.price = price;
        this.allergens = allergens;
        this.calories = calories;
        this.quantity = quantity;
        this.imageUrl = imageUrl;
        this.productId = productId;

    }

    public String getName() {
        return name;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return  description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}