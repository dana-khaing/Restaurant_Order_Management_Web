package com.oaxaca.waiter_service.model;

import java.util.ArrayList;
import java.util.List;

import com.oaxaca.shared_library.model.order.IOrderItem;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

@Embeddable
public class OrderItem implements IOrderItem {

    private int category;
    private String name;
    private String description;
    private float price;

    private List<String> allergens = new ArrayList<>();
    
    private int calories;

    public OrderItem() {
    }

    public OrderItem(int category, String name, String description, float price,
            List<String> allergens, int calories) {

        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergens = allergens;
        this.calories = calories;

    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getAllergens() {
        return this.allergens;
    }

    public void setAllergens(List<String> allergens) {

        if (this.allergens == null) {
            this.allergens = new ArrayList<>();
        }

        this.allergens.clear();
        this.allergens.addAll(allergens);
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

}
