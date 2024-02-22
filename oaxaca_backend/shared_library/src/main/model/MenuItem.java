package com.oaxaca.shared_library.model;
import java.util.List;

public class MenuItem implements IMenuItem{

    private int id;
    private int category;
    private String name;
    private String description;
    private float price;
    private List<String> allergens;
    private int calories;

    public MenuItem(int id, int category, String name, String description, float price,
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
