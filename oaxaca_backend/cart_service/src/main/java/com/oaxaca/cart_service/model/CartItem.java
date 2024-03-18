package com.oaxaca.cart_service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.oaxaca.shared_library.model.menu.IMenuItem;

@RedisHash("CartItem")
public class CartItem implements Serializable, IMenuItem {

    @Id
    private String id;
    private int productId;
    private int quantity;
    private float price;
    private String productName;
    private String dietaryRequirement;
    private List<String> allergens;
    private int calories;
    private int category;

    public CartItem(String productName, String dietaryRequirement, List<String> allergens, int calories, int category, int productId, int quantity, float price) {
        this.id = UUID.randomUUID().toString();
        this.productName = productName;
        this.dietaryRequirement = dietaryRequirement;
        this.allergens = new ArrayList<>(allergens);
        this.calories = calories;
        this.category = category;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;

    }

    @Override
    public int getCategory() {
        return category;
    }

    @Override
    public String getName() {
        return productName;
    }

    @Override
    public String getDescription() {
        return dietaryRequirement;
    }

    @Override
    public float getPrice() {
        return price;

    }

    @Override
    public List<String> getAllergens() {
        return allergens;
    }

    @Override
    public int getCalories() {
        return calories;
    }

    @Override
    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public void setName(String name) {
        this.productName = name;

    }

    @Override
    public void setDescription(String description) {
        this.dietaryRequirement = description;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    @Override
    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}