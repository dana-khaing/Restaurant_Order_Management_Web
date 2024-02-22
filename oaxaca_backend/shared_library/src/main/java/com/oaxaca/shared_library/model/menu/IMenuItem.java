package com.oaxaca.shared_library.model.menu;

import java.util.List;

public interface IMenuItem {
    
    // Getters
    int getId();
    int getCategory();
    String getName();
    String getDescription();
    float getPrice();
    List<String> getAllergens();
    int getCalories();

    // Setters
    void setId(int id);
    void setCategory(int category);
    void setName(String name);
    void setDescription(String description);
    void setPrice(float price);
    void setAllergens(List<String> allergens);
    void setCalories(int calories);

    // Utility Methods
    @Override
    String toString();
    @Override
    boolean equals(Object o);
}
