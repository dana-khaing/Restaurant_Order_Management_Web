package com.oaxaca.menu_service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class TestMenuService {

  // This is the menu taken from the menu service. It is here to simplify comparisons during
  // assertions.
  private List<MenuItem> sampleMenu = Arrays.asList(
      /*
       * Parameter 1: ID Parameter 2: Category 1 : Appetizers 2 : Main Courses 3 : Sides 4 :
       * Desserts 5 : Beverages Parameter 3: Name Parameter 4: Description Parameter 5: Price
       * Parameter 6: Allergies Parameter 7: Calories
       * 
       * Credit to Irene Vasquez for the menu & Michael Goodwin for allergies and calories of the
       * menu
       */
      new MenuItem(1, 1, "Tlayudas (Vegetarian option)",
          "Large, thin, crispy tortillas with vegetable toppings.", 6.50f, Arrays.asList("Gluten"),
          528, true),
      new MenuItem(2, 1, "Tostadas de Chapulines",
          "Tostadas with guacamole and seasoned chapulines.", 7.99f, Arrays.asList("Shellfish"),
          315, true),
      new MenuItem(3, 1, "Guacamole",
          "Fresh avocados, tomatoes, onions, cilantro, lime juice, salt, and pepper.", 6.50f,
          Arrays.asList("None"), 256, true),
      new MenuItem(4, 2, "Mole Negro with Chicken", "Slow-cooked chicken in dark mole sauce.",
          11.99f, Arrays.asList("Nuts", "Gluten"), 681, true),
      new MenuItem(5, 2, "Shrimp ceviche",
          "Shrimp marinated in lime juice, mixed with tomatoes, onions, cilantro, and avocado.",
          12.99f, Arrays.asList("Shellfish"), 421, true),
      new MenuItem(6, 2, "Tacos al pastor",
          "Marinated and grilled pork served in corn tortillas with pineapple and cilantro.", 9.99f,
          Arrays.asList("Gluten"), 553, true),
      new MenuItem(7, 2, "Tlayudas con Tasajo",
          "Grilled and seasoned thinly sliced beef on tlayudas.", 12.99f, Arrays.asList("Gluten"),
          602, true),
      new MenuItem(8, 2, "Enfrijoladas",
          "Rolled tortillas filled with chicken and black bean sauce.", 11.99f,
          Arrays.asList("Gluten"), 499, true),
      new MenuItem(9, 3, "Chiles Rellenos de Quesillo",
          "Poblano peppers stuffed with Oaxacan cheese.", 10.99f, Arrays.asList("Dairy"), 457, true),
      new MenuItem(10, 3, "Sikil P''ak (Pumpkin Seed Dip)",
          "Dip made with pumpkin seeds, tomatoes, and spices.", 8.99f, Arrays.asList(), 351, true),
      new MenuItem(11, 4, "Chocolate Oaxaqueño", "Oaxacan-style hot chocolate.", 6.99f,
          Arrays.asList("Dairy"), 292, true),
      new MenuItem(12, 4, "Nicuatole", "Traditional Oaxacan dessert with masa and chocolate.",
          7.99f, Arrays.asList("Dairy"), 330, true),
      new MenuItem(13, 4, "Tres Leches Cake",
          "Sponge cake soaked in three kinds of milk, topped with whipped cream and fresh berries.",
          8.99f, Arrays.asList("Dairy", "Gluten"), 585, true),
      new MenuItem(14, 4, "Mango Sorbet", "Refreshing mango sorbet.", 5.99f, Arrays.asList("None"),
          201, true),
      new MenuItem(15, 5, "Mezcal Cocktail", "Various mezcal-based cocktails.", 9.99f,
          Arrays.asList("None"), 253, true),
      new MenuItem(16, 5, "Horchata", "Rice and based horchata.", 4.99f, Arrays.asList("None"),
          139, true),
      new MenuItem(17, 5, "Coca-Cola", "Carbonated soft drink with ice", 2.80f,
          Arrays.asList("None"), 210, true));

  @Test
  void testCreateMenuService() { // Test 01
    try {
      MenuService testMS = new MenuService();
      assertTrue(testMS instanceof MenuService,
          "Test that a new MenuService instance was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to create a new MenuService instance; " + e.getMessage());
    }
  }

  @Test
  void testGetFullMenu() {
    try {
      MenuService testMS = new MenuService();
      assertEquals(sampleMenu, testMS.getFullMenu());
    } catch (Exception e) {
      fail("Exception thrown while testing getting the full menu from a MenuService instance; "
          + e.getMessage());
    }
  }

  @Test
  void testGetMenuItem_FirstItem() {
    try {
      MenuService testMS = new MenuService();
      assertEquals(sampleMenu.stream().filter(t -> t.getId() == 1).findFirst().get().toString(),
          testMS.getMenuItem(1).toString(),
          "Test that a MenuService instance returns the first item of the menu successfully");
    } catch (Exception e) {
      fail(
          "Exception thrown while testing getting the first item of the menu from a MenuService instance; "
              + e.getMessage());
    }
  }

  @Test
  void testGetMenuItem_AllItems() {
    try {
      MenuService testMS = new MenuService();
      for (int i = 1; i <= 17; i++) {
        final Integer final_i = new Integer(i);
        assertEquals(
            sampleMenu.stream().filter(t -> t.getId() == final_i).findFirst().get().toString(),
            testMS.getMenuItem(i).toString(),
            "Test that a MenuService instance returns the first item of the menu successfully");
      }
    } catch (Exception e) {
      fail(
          "Exception thrown while testing getting the first item of the menu from a MenuService instance; "
              + e.getMessage());
    }
  }
}
