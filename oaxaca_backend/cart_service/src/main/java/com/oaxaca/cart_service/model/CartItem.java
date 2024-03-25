package com.oaxaca.cart_service.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.oaxaca.shared_library.model.menu.ICartItem;

@RedisHash("CartItem")
public class CartItem implements Serializable, ICartItem {

    @Id
    private String id;
    private Long productId;
    private int quantity;
    private float price;
    private String productName;
    private List<String> allergens;
    private int calories;
    private int category;
    private String description;
    private String imageUrl;

    public CartItem(String productName, String description, List<String> allergens, int calories, int category,
            Long productId, int quantity, float price, String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.productName = productName;
        this.allergens = allergens;
        this.calories = calories;
        this.category = category;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;

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

    @Override
    public Long getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public int getQuantity() {
        return quantity;
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}