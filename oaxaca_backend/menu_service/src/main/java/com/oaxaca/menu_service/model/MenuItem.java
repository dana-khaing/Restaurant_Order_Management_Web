package com.oaxaca.menu_service.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

/**
 * MenuItem is a class that represents one single item in the menu. It also represents the schema
 * for the "menu" relation in the database.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */

@Entity
@Table(name = "menu")
public class MenuItem {

  // User Story: Customer - View Menu
  /** ID of the MenuItem entry */
  @Id
  private int id;

  /**
   * Category of the product in the MenuItem entry <br>
   * <br>
   * Values of category correspond as follows:
   * <ul>
   * <li>1 - Appetiser</li>
   * <li>2 - Main Dish</li>
   * <li>3 - Side Dish</li>
   * <li>4 - Dessert</li>
   * <li>5 - Drink</li>
   * </ul>
   */
  @Column(name = "category", nullable = false)
  private int category;

  /** Name of the product of the MenuItem entry */
  @Column(name = "name", nullable = false)
  private String name;

  /** Description of the product of the MenuItem entry */
  @Lob
  @Column(name = "description", nullable = false)
  private String description;

  /** Price of the product of the MenuItem entry */
  @Column(name = "price", nullable = false)
  private float price;

  // User Story: Customer - Allergies and Calories
  /** Allergens contained in the product of the MenuItem entry */
  @ElementCollection
  private List<String> allergens;

  /** Calories of the product of the MenuItem entry */
  @Column(name = "calories", nullable = false)
  private int calories;

  // User Story: Waiter - Change the Menu
  /** Current availability of the product of the MenuItem entry */
  @Column(name = "availability", nullable = false)
  private boolean availability;


  // User Story: Customer - Food pictures
  /**
   * URL to image of the product of the MenuItem entry, if there is no image then this will be null
   */
  @Column(name = "imageURL", nullable = true)
  private String imageURL; // If the product has no associated picture, imageURL will be null

  /**
   * Default no-argument constructor. This is required by JPA for database functionality to work and
   * should be avoided in programming under normal circumstances.
   */
  public MenuItem() {

  }

  /**
   * Creates a new MenuItem object with no associated image.
   * 
   * @param id The Id of the MenuItem
   * @param category The category of the product in the MenuItem entry. The values of category
   *        correspond as follows:
   *        <ul>
   *        <li>1 - Appetiser</li>
   *        <li>2 - Main Dish</li>
   *        <li>3 - Side Dish</li>
   *        <li>4 - Dessert</li>
   *        <li>5 - Drink</li>
   *        </ul>
   * @param name The name of the product
   * @param description A brief description of the product
   * @param price The price charged to customer for the product
   * @param allergens The allergens present in the product
   * @param calories The number of calories in the product
   * @param availability The availability field indicates whether the restaurant is currently
   *        serving the product to customers
   */
  public MenuItem(int id, int category, String name, String description, float price,
      List<String> allergens, int calories, boolean availability) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.price = price;
    this.allergens = allergens;
    this.calories = calories;
    this.availability = availability;

    // No image provided
    this.imageURL = null;
  }

  /**
   * Creates a new MenuItem object with an image.
   * 
   * @param id The Id of the MenuItem
   * @param category The category of the product in the MenuItem entry. The values of category
   *        correspond as follows:
   *        <ul>
   *        <li>1 - Appetiser</li>
   *        <li>2 - Main Dish</li>
   *        <li>3 - Side Dish</li>
   *        <li>4 - Dessert</li>
   *        <li>5 - Drink</li>
   *        </ul>
   * @param name The name of the product
   * @param description A brief description of the product
   * @param price The price charged to customer for the product
   * @param allergens The allergens present in the product
   * @param calories The number of calories in the product
   * @param availability The availability field indicates whether the restaurant is currently
   *        serving the product to customers
   * @param imageURL The address to an image of the product
   */
  public MenuItem(int id, int category, String name, String description, float price,
      List<String> allergens, int calories, boolean availability, String imageURL) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.description = description;
    this.price = price;
    this.allergens = allergens;
    this.calories = calories;
    this.availability = availability;
    this.imageURL = imageURL;
  }

  /**
   * Returns the Id of this MenuItem instance.
   * 
   * @return Id of this MenuItem instance
   */
  public int getId() {
    return id;
  }

  /**
   * Sets the Id of this MenuItem instance.
   * 
   * @param id New Id of this MenuItem instance
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Returns the category of this MenuItem instance. The values of category correspond as follows:
   * <ul>
   * <li>1 - Appetiser</li>
   * <li>2 - Main Dish</li>
   * <li>3 - Side Dish</li>
   * <li>4 - Dessert</li>
   * <li>5 - Drink</li>
   * </ul>
   * 
   * @return Category of this MenuItem instance
   */
  public int getCategory() {
    return category;
  }

  /**
   * Sets the category of this MenuItem instance.
   * 
   * @param category Updated category of this MenuItem instance
   */
  public void setCategory(int category) {
    this.category = category;
  }

  /**
   * Returns the name of this MenuItem instance.
   * 
   * @return Name of the MenuItem
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of this MenuItem instance.
   * 
   * @param name Updated name of this MenuItem instance
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Returns the description of this MenuItem instance.
   * 
   * @return Description of this MenuItem instance
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of this MenuItem instance. Description should be kept brief and concise if
   * possible.
   * 
   * @param description Updated description of this MenuItem instance
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Returns the price of this MenuItem instance.
   * 
   * @return Price of this MenuItem instance
   */
  public float getPrice() {
    return price;
  }

  /**
   * Sets the price of this MenuItem instance.
   * 
   * @param price Updated price of this MenuItem instance
   */
  public void setPrice(float price) {
    this.price = price;
  }

  /**
   * Returns the allergens of this MenuItem instance. It is returned in the format of a List
   * containing a separate string for each allergen.
   * 
   * @return List of allergens of this MenuItem instance
   */
  public List<String> getAllergens() {
    return this.allergens;
  }

  /**
   * Set the allergens of this MenuItem instance. Accepts a List containing a separate string for
   * each allergen.
   * 
   * @param allergens Updated list of allergens of this MenuItem instance
   */
  public void setAllergens(List<String> allergens) {
    this.allergens = allergens;
  }

  /**
   * Returns the number of calories in the product of this MenuItem instance.
   * 
   * @return Calories of this MenuItem instance
   */
  public int getCalories() {
    return this.calories;
  }

  /**
   * Set the number of calories in the product of this MenuItem instance.
   * 
   * @param calories Updated calories of this MenuItem instance
   */
  public void setCalories(int calories) {
    this.calories = calories;
  }

  /**
   * Returns whether the product of this MenuItem instance is currently in stock and being actively
   * served to customers.
   * 
   * @return true if product is currently available, false if product is currently unavailable
   */
  public boolean getAvailability() {
    return this.availability;
  }

  /**
   * Sets whether the product of this MenuItem instance is currently in stock and being actively
   * served to customers.
   * 
   * @param availability true if product is now available, false if product is now unavailable
   */
  public void setAvailability(boolean availability) {
    this.availability = availability;
  }

  /**
   * Returns the URL of the image associated with this MenuItem instance.
   * 
   * @return URL of the image for this MenuItem, if there is no image then null is returned
   */
  public String getImageURL() {
    return this.imageURL;
  }

  /**
   * Sets the URL of the image associated with this MenuItem instance.
   * 
   * @param imageURL Updated URL of the image for this MenuItem, null to remove image
   */
  public void setImageUrl(String imageURL) {
    this.imageURL = imageURL;
  }

  /**
   * Returns the MenuItem in JSON format.
   * 
   * @return MenuItem formatted as JSON
   */
  @Override
  public String toString() {
    return "{\"id\":" + String.valueOf(id) + ",\"name\":\"" + name + "\",\"description\":\""
        + description + "\",\"price\":" + String.valueOf(price) + ",\"allergens\":"
        + allergens.toString() + ",\"calories\":" + String.valueOf(calories) + ",\"availability\":"
        + String.valueOf(availability) + ",\"imageURL\":" + imageURL + "}";
  }

  /**
   * Compares this MenuItem with another Object. Starts by checking if object is a MenuItem object.
   * If it is, it calls equals(MenuItem) and parses o to a MenuItem object. If it is not, it stops
   * the comparison and returns false.
   * 
   * @param o Object to compare to this MenuItem instance
   * @return true if o is equal to this MenuItem instance, otherwise false
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || o.getClass() != this.getClass()) { // Is the other object a MenuItem object?
      return false; // If not then they can't be equal
    } // Now that we know o must be an Entry object, call equals(MenuItem) and compare both products
    return equals((MenuItem) o);
  }

  /**
   * Compares this MenuItem with another MenuItem and determines if all their values are equal.
   * 
   * @param otherMenuItem Other MenuItem object to compare to
   * @return true if otherMenuItem is equal to this MenuItem instance, otherwise false
   */
  public boolean equals(MenuItem otherMenuItem) {
    try {
      return this.id == otherMenuItem.getId() && this.category == otherMenuItem.getCategory()
          && this.name.equals(otherMenuItem.getName())
          && this.description.equals(otherMenuItem.getDescription())
          && this.price == otherMenuItem.getPrice()
          && this.allergens.equals(otherMenuItem.getAllergens())
          && this.calories == otherMenuItem.getCalories()
          && this.availability == otherMenuItem.getAvailability()
          && this.imageURL == otherMenuItem.getImageURL();
      // Using Exception to tell if otherMenuItem is a different type
    } catch (Exception e) {
      return false;
    }
  }
}
