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
    /**
     * Constructs a {@code CartItemDto} with the specified parameters.
     *
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param category    The category of the product.
     * @param price       The price of the product.
     * @param allergens   The list of allergens present in the product.
     * @param calories    The number of calories in the product.
     * @param quantity    The quantity of the product in the cart.
     * @param imageUrl    The URL of the product's image.
     * @param productId   The unique identifier of the product.
     */
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