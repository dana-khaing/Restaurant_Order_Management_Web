package com.oaxaca.waiter_service.model;

import java.util.ArrayList;
import java.util.List;

import com.oaxaca.shared_library.model.order.IOrderItem;

import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItem implements IOrderItem {

    private int category;
    private String name;
    private String description;
    private float price;
    private Long productId;
    private List<String> allergens = new ArrayList<>();
    private int quantity;
    private String imageUrl;

    private int calories;

    public OrderItem() {
    }
    /**
     * Constructs an OrderItem instance with specified attributes.
     * 
     * @param category     The category of the item.
     * @param name         The name of the item.
     * @param description  The description of the item.
     * @param price        The price of the item.
     * @param allergens    The list of allergens present in the item.
     * @param calories     The calorie count of the item.
     * @param productId    The ID of the product associated with the item.
     * @param quantity     The quantity of the item.
     * @param imageUrl     The URL of the item's image.
     */
    public OrderItem(int category, String name, String description, float price,
            List<String> allergens, int calories, Long productId, int quantity, String imageUrl) {

        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergens = allergens;
        this.calories = calories;
        this.productId = productId;
        this.quantity = quantity;
        this.imageUrl = imageUrl;

    }

    @Override
    public int getCategory() {
        return category;
    }

    @Override
    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
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
    public float getPrice() {
        return price;
    }

    @Override
    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public List<String> getAllergens() {
        return this.allergens;
    }

    @Override
    public void setAllergens(List<String> allergens) {

        if (this.allergens == null) {
            this.allergens = new ArrayList<>();
        }

        this.allergens.clear();
        this.allergens.addAll(allergens);
    }

    @Override
    public int getCalories() {
        return calories;
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
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
