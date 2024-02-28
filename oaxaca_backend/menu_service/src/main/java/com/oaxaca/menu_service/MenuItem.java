package com.oaxaca.menu_service;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class MenuItem {

  // Customer - View Menu
  @Id
  private int id;
  private int category;
  private String name;
  private String description;
  private float price;

  // Customer - Allergies and Calories
  private List<String> allergens;
  private int calories;
  
  // Waiter - Change the Menu
  private boolean available;

  // Default no-argument constructor
  // This is required by JPA for database functionality to work
  public MenuItem() {
    
  }
  
  public MenuItem(int id, int category, String name, String description, float price,
      List<String> allergens, int calories, boolean available) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.price = price;
    this.allergens = allergens;
    this.calories = calories;
    this.available = available;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "category", nullable = false)
  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  @Column(name = "name", nullable = false)
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "description", nullable = false)
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "price", nullable = false)
  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

  @Column(name = "allergens", nullable = false)
  public List<String> getAllergens() {
    return this.allergens;
  }

  public void setAllergens(List<String> allergens) {
    this.allergens = allergens;
  }

  @Column(name = "calories", nullable = false)
  public int getCalories() {
    return this.calories;
  }

  public void setCalories(int calories) {
    this.calories = calories;
  }
  
  @Column(name = "availability", nullable = false)
  public boolean getAvailability() {
    return this.available;
  }
  
  public void setAvailability(boolean available) {
    if (this.available != available) {
      this.available = !this.available;
    }
  }

  @Override
  public String toString() {
    return "{\"id\":" + String.valueOf(id) + ",\"name\":\"" + name + "\",\"description\":\""
        + description + "\",\"price\":" + String.valueOf(price) + ",\"allergens\":"
        + allergens.toString() + ",\"calories\":" + String.valueOf(calories) + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || o.getClass() != this.getClass()) { // Is the other object an Entry object?
      return false; // If not then they can't be equal
    } // Now that we know o must be an Entry object, call equals(Entry) and compare both entries
    return equals((MenuItem) o);
  }

  public boolean equals(MenuItem otherEntry) {
    try {
      return this.id == otherEntry.getId() && this.category == otherEntry.getCategory()
          && this.name.equals(otherEntry.getName())
          && this.description.equals(otherEntry.getDescription())
          && this.price == otherEntry.getPrice() && this.allergens.equals(otherEntry.getAllergens())
          && this.calories == otherEntry.getCalories();
      // Using Exception to tell if otherEntry is a different type
    } catch (Exception e) {
      return false;
    }
  }
}
