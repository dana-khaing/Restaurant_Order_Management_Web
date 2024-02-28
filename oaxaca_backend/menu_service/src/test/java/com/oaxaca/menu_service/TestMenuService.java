package com.oaxaca.menu_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TestMenuService {

  @Autowired
  private MenuService testMS;

  // Credit to Irene Vasquez for the menu & Michael Goodwin for allergies and calories of the menu
  MenuItem item1 = new MenuItem(10001, 1, "Tlayudas (Vegetarian option)",
      "Large, thin, crispy tortillas with vegetable toppings.", 6.50f, Arrays.asList("Gluten"), 528,
      true);
  MenuItem item2 = new MenuItem(10002, 1, "Tostadas de Chapulines",
      "Tostadas with guacamole and seasoned chapulines.", 7.99f, Arrays.asList("Shellfish"), 315,
      true);
  MenuItem item3 = new MenuItem(10003, 1, "Guacamole",
      "Fresh avocados, tomatoes, onions, cilantro, lime juice, salt, and pepper.", 6.50f,
      Arrays.asList("None"), 256, true);
  MenuItem item4 =
      new MenuItem(10004, 2, "Mole Negro with Chicken", "Slow-cooked chicken in dark mole sauce.",
          11.99f, Arrays.asList("Nuts", "Gluten"), 681, true);
  MenuItem item5 = new MenuItem(10005, 2, "Shrimp ceviche",
      "Shrimp marinated in lime juice, mixed with tomatoes, onions, cilantro, and avocado.", 12.99f,
      Arrays.asList("Shellfish"), 421, true);
  MenuItem item6 = new MenuItem(10006, 2, "Tacos al pastor",
      "Marinated and grilled pork served in corn tortillas with pineapple and cilantro.", 9.99f,
      Arrays.asList("Gluten"), 553, true);
  MenuItem item7 = new MenuItem(10007, 2, "Tlayudas con Tasajo",
      "Grilled and seasoned thinly sliced beef on tlayudas.", 12.99f, Arrays.asList("Gluten"), 602,
      true);
  MenuItem item8 = new MenuItem(10008, 2, "Enfrijoladas",
      "Rolled tortillas filled with chicken and black bean sauce.", 11.99f, Arrays.asList("Gluten"),
      499, true);
  MenuItem item9 = new MenuItem(10009, 3, "Chiles Rellenos de Quesillo",
      "Poblano peppers stuffed with Oaxacan cheese.", 10.99f, Arrays.asList("Dairy"), 457, true);
  MenuItem item10 = new MenuItem(10010, 3, "Sikil P''ak (Pumpkin Seed Dip)",
      "Dip made with pumpkin seeds, tomatoes, and spices.", 8.99f, Arrays.asList(), 351, true);
  MenuItem item11 = new MenuItem(10011, 4, "Chocolate Oaxaqueño", "Oaxacan-style hot chocolate.",
      6.99f, Arrays.asList("Dairy"), 292, true);
  MenuItem item12 =
      new MenuItem(10012, 4, "Nicuatole", "Traditional Oaxacan dessert with masa and chocolate.",
          7.99f, Arrays.asList("Dairy"), 330, true);
  MenuItem item13 = new MenuItem(10013, 4, "Tres Leches Cake",
      "Sponge cake soaked in three kinds of milk, topped with whipped cream and fresh berries.",
      8.99f, Arrays.asList("Dairy", "Gluten"), 585, true);
  MenuItem item14 = new MenuItem(10014, 4, "Mango Sorbet", "Refreshing mango sorbet.", 5.99f,
      Arrays.asList("None"), 201, true);
  MenuItem item15 = new MenuItem(10015, 5, "Mezcal Cocktail", "Various mezcal-based cocktails.",
      9.99f, Arrays.asList("None"), 253, true);
  MenuItem item16 = new MenuItem(10016, 5, "Horchata", "Rice and based horchata.", 4.99f,
      Arrays.asList("None"), 139, true);
  MenuItem item17 = new MenuItem(10017, 5, "Coca-Cola", "Carbonated soft drink with ice", 2.80f,
      Arrays.asList("None"), 210, true);

  @BeforeEach
  void populateDatabase() {
    Arrays
        .asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11,
            item12, item13, item14, item15, item16, item17)
        .forEach(item -> testMS.addMenuItem(item));
  }

  @Test
  void testValidateMenuService() { // Test 01
    try {
      assertTrue(testMS instanceof MenuService,
          "Test that a new MenuService instance was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to validate the MenuService instance; " + e.getMessage());
    }
  }

  @Test
  @Transactional
  void testGetMenuItem_FirstItem() { // Test 02
    try {
      assertEquals(item1.toString(), testMS.getMenuItem(10001).toString(),
          "Test that the MenuService returns the first item of the menu successfully");
    } catch (Exception e) {
      fail(
          "Exception thrown while testing getting the first item of the menu from the MenuService; "
              + e.getMessage());
    }
  }

  @Test
  @Transactional
  void testGetMenuItem_AllItems() { // Test 03
    try {
      assertEquals(item1.toString(), testMS.getMenuItem(10001).toString(),
          "Test that the MenuService returns item 1 of the menu successfully");
      assertEquals(item2.toString(), testMS.getMenuItem(10002).toString(),
          "Test that the MenuService returns item 2 of the menu successfully");
      assertEquals(item3.toString(), testMS.getMenuItem(10003).toString(),
          "Test that the MenuService returns item 3 of the menu successfully");
      assertEquals(item4.toString(), testMS.getMenuItem(10004).toString(),
          "Test that the MenuService returns item 4 of the menu successfully");
      assertEquals(item5.toString(), testMS.getMenuItem(10005).toString(),
          "Test that the MenuService returns item 5 of the menu successfully");
      assertEquals(item6.toString(), testMS.getMenuItem(10006).toString(),
          "Test that the MenuService returns item 6 of the menu successfully");
      assertEquals(item7.toString(), testMS.getMenuItem(10007).toString(),
          "Test that the MenuService returns item 7 of the menu successfully");
      assertEquals(item8.toString(), testMS.getMenuItem(10008).toString(),
          "Test that the MenuService returns item 8 of the menu successfully");
      assertEquals(item9.toString(), testMS.getMenuItem(10009).toString(),
          "Test that the MenuService returns item 9 of the menu successfully");
      assertEquals(item10.toString(), testMS.getMenuItem(10010).toString(),
          "Test that the MenuService returns item 10 of the menu successfully");
      assertEquals(item11.toString(), testMS.getMenuItem(10011).toString(),
          "Test that the MenuService returns item 11 of the menu successfully");
      assertEquals(item12.toString(), testMS.getMenuItem(10012).toString(),
          "Test that the MenuService returns item 12 of the menu successfully");
      assertEquals(item13.toString(), testMS.getMenuItem(10013).toString(),
          "Test that the MenuService returns item 13 of the menu successfully");
      assertEquals(item14.toString(), testMS.getMenuItem(10014).toString(),
          "Test that the MenuService returns item 14 of the menu successfully");
      assertEquals(item15.toString(), testMS.getMenuItem(10015).toString(),
          "Test that the MenuService returns item 15 of the menu successfully");
      assertEquals(item16.toString(), testMS.getMenuItem(10016).toString(),
          "Test that the MenuService returns item 16 of the menu successfully");
      assertEquals(item17.toString(), testMS.getMenuItem(10017).toString(),
          "Test that the MenuService returns item 17 of the menu successfully");
    } catch (Exception e) {
      fail(
          "Exception thrown while testing getting the first item of the menu from a MenuService instance; "
              + e.getMessage());
    }
  }

  @Test
  @Transactional
  void testGetFullMenu() { // Test 04
    try {
      assertEquals(testMS.getFullMenu().toString(),
          Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10,
              item11, item12, item13, item14, item15, item16, item17).toString(),
          "Test that getFullMenu() returns the full menu");
    } catch (Exception e) {
      fail("Exception thrown while testing getting the full menu; " + e.getMessage());
    }
  }
}
