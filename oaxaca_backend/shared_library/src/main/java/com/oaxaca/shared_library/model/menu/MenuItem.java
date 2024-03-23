package com.oaxaca.shared_library.model.menu;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MenuItem implements IMenuItem{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int category;
    private String name;
    private String description;
    private float price;
    private List<String> allergens;
    private int calories;
    private String imageUrl;


    public MenuItem(Long id, int category, String name, String description, float price,
        List<String> allergens, int calories, String imageUrl) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.description = description;
        this.price = price;
        this.allergens = allergens;
        this.calories = calories;
        this.imageUrl = imageUrl;
    }

    public Long getProductId() {
        return id;
    }

    public void setProductId(Long id) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "MenuItem [id=" + id + ", category=" + category + ", name=" + name + ", description=" + description
                + ", price=" + price + ", allergens=" + allergens + ", calories=" + calories + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof MenuItem)) {
            return false;
        }
        MenuItem menuItem = (MenuItem) o;
        return id == menuItem.id && category == menuItem.category && Objects.equals(name, menuItem.name)
                && Objects.equals(description, menuItem.description) && price == menuItem.price
                && Objects.equals(allergens, menuItem.allergens) && calories == menuItem.calories;
    }
    
}
