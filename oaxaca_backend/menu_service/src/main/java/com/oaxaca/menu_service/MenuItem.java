package com.oaxaca.menu_service;

public class MenuItem {

  public void setPrice(float price) {
    this.price = price;
  }

  private int id;
  private int category;
  private String name;
  private String description;
  private float price;

  public MenuItem(int id, int category, String name, String description, float price) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.price = price;
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

}
