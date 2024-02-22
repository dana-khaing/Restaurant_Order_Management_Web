package com.oaxaca.shared_library.model.order;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderItem implements IOrderItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int category;
    private String name;
    private String description;
    private float price;
    private List<String> allergens;
    private int calories;


    public OrderItem(int id, int category, String name, String description, float price,
        List<String> allergens, int calories) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergens = allergens;
        this.calories = calories;
      
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        this.allergens = allergens;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
            "id=" + id +
            ", category=" + category +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", allergens=" + allergens +
            ", calories=" + calories +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return id == orderItem.id &&
            category == orderItem.category &&
            Float.compare(orderItem.price, price) == 0 &&
            calories == orderItem.calories &&
            Objects.equals(name, orderItem.name) &&
            Objects.equals(description, orderItem.description) &&
            Objects.equals(allergens, orderItem.allergens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, name, description, price, allergens, calories);
    }
    
}
