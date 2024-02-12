package com.oaxaca.menu_service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class MenuControllerTest {

  // This is the menu taken from the menu service. It is here to simplify comparisons during
  // assertions.
  private List<MenuItem> sampleMenu = Arrays.asList(
      /*
       * Parameter 1: ID Parameter 2: Category 1 : Appetizers 2 : Main Courses 3 : Sides 4 :
       * Desserts 5 : Beverages Parameter 3: Name Parameter 4: Description Parameter 5: Price
       * 
       * Credit to Irene Vasquez for the menu
       */
      new MenuItem(1, 1, "Tlayudas (Vegetarian option)",
          "Large, thin, crispy tortillas with vegetable toppings.", 6.50f),
      new MenuItem(2, 1, "Tostadas de Chapulines",
          "Tostadas with guacamole and seasoned chapulines.", 7.99f),
      new MenuItem(3, 1, "Guacamole",
          "Fresh avocados, tomatoes, onions, cilantro, lime juice, salt, and pepper.", 6.50f),
      new MenuItem(4, 2, "Mole Negro with Chicken", "Slow-cooked chicken in dark mole sauce.",
          11.99f),
      new MenuItem(5, 2, "Shrimp ceviche",
          "Shrimp marinated in lime juice, mixed with tomatoes, onions, cilantro, and avocado.",
          12.99f),
      new MenuItem(6, 2, "Tacos al pastor",
          "Marinated and grilled pork served in corn tortillas with pineapple and cilantro.",
          9.99f),
      new MenuItem(7, 2, "Tlayudas con Tasajo",
          "Grilled and seasoned thinly sliced beef on tlayudas.", 12.99f),
      new MenuItem(8, 2, "Enfrijoladas",
          "Rolled tortillas filled with chicken and black bean sauce.", 11.99f),
      new MenuItem(9, 3, "Chiles Rellenos de Quesillo",
          "Poblano peppers stuffed with Oaxacan cheese.", 10.99f),
      new MenuItem(10, 3, "Sikil P''ak (Pumpkin Seed Dip)",
          "Dip made with pumpkin seeds, tomatoes, and spices.", 8.99f),
      new MenuItem(11, 4, "Chocolate Oaxaqueño", "Oaxacan-style hot chocolate.", 6.99f),
      new MenuItem(12, 4, "Nicuatole", "Traditional Oaxacan dessert with masa and chocolate.",
          7.99f),
      new MenuItem(13, 4, "Tres Leches Cake",
          "Sponge cake soaked in three kinds of milk, topped with whipped cream and fresh berries.",
          8.99f),
      new MenuItem(14, 4, "Mango Sorbet", "Refreshing mango sorbet.", 5.99f),
      new MenuItem(15, 5, "Mezcal Cocktail", "Various mezcal-based cocktails.", 9.99f),
      new MenuItem(16, 5, "Horchata", "Rice and based horchata.", 4.99f),
      new MenuItem(17, 5, "Coca-Cola", "Carbonated soft drink with ice", 2.80f));

  @Test
  void testCreateMenuController() { // Test 01
    try {
      MenuController testMC = new MenuController();
      assertTrue(testMC instanceof MenuController,
          "Test that a new MenuController instance was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to create a new MenuController instance; "
          + e.getMessage());
    }
  }

  @Test
  void testGetFullMenu() {
    try {
      MenuController testMC = new MenuController();
      assertEquals(sampleMenu, testMC.getFullMenu());
    } catch (Exception e) {
      fail("Exception thrown while testing getting the full menu from a MenuController instance; "
          + e.getMessage());
    }
  }

  @Test
  void testGetMenuItem_FirstItem() {
    try {
      MenuController testMC = new MenuController();
      assertEquals(sampleMenu.stream().filter(t -> t.getId() == 1).findFirst().get(),
          testMC.getMenuItem(1),
          "Test that a MenuController instance returns the first item of the menu successfully");
    } catch (Exception e) {
      fail(
          "Exception thrown while testing getting the first item of the menu from a MenuController instance; "
              + e.getMessage());
    }
  }

  @Test
  void testGetMenuItem_AllItems() {
    try {
      MenuController testMC = new MenuController();
      for (int i = 1; i <= 17; i++) {
        assertEquals(sampleMenu.stream().filter(t -> t.getId() == i).findFirst().get(),
            testMC.getMenuItem(i),
            "Test that a MenuController instance returns the first item of the menu successfully");
      }
    } catch (Exception e) {
      fail(
          "Exception thrown while testing getting the first item of the menu from a MenuController instance; "
              + e.getMessage());
    }
  }
}
