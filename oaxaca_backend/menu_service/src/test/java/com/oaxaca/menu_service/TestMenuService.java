package com.oaxaca.menu_service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class TestMenuService {

  @Autowired
  private MenuService testMS;

  private List<MenuItem> currentMenu;
  private boolean capturedCurrentMenu = false;

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
    // Workaround to avoid using a static @BeforeAll method
    if (!capturedCurrentMenu) {
      currentMenu = testMS.getFullMenu();
      capturedCurrentMenu = true;
    }

    System.out.println("[TestMenuService] => Refreshing test menu");
    Arrays
        .asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11,
            item12, item13, item14, item15, item16, item17)
        .forEach(item -> testMS.addMenuItem(item));
    
    // Delete new item from Test 05
    testMS.deleteMenuItem(10018);
  }

  @Test
  void testValidateMenuService() { // Test 01
    System.out.println("[TestMenuService] => Test 01 testValidateMenuService started");
    try {
      assertTrue(testMS instanceof MenuService,
          "Test that a new MenuService instance was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to validate the MenuService instance; " + e.getMessage());
    }
    System.out.println("[TestMenuService] => Test 01 testValidateMenuService finished");
  }

  @Test
  @Transactional
  void testGetMenuItem_FirstItem() { // Test 02
    System.out.println("[TestMenuService] => Test 02 testGetMenuItem_FirstItem started");
    try {
      assertEquals(item1.toString(), testMS.getMenuItem(10001).toString(),
          "Test that the MenuService returns the first item of the menu successfully");
    } catch (Exception e) {
      fail(
          "Exception thrown while testing getting the first item of the menu from the MenuService; "
              + e.getMessage());
    }
    System.out.println("[TestMenuService] => Test 02 testGetMenuItem_FirstItem finished");
  }

  @Test
  @Transactional
  void testGetMenuItem_AllItems() { // Test 03
    System.out.println("[TestMenuService] => Test 03 testGetMenuItem_AllItems started");
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
    System.out.println("[TestMenuService] => Test 03 testGetMenuItem_AllItems finished");
  }

  @Test
  @Transactional
  void testGetFullMenu() { // Test 04
    System.out.println("[TestMenuService] => Test 04 testGetFullMenu started");
    try {
      assertEquals(testMS.getFullMenu().toString(),
          Stream
              .concat(currentMenu.stream(),
                  Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9,
                      item10, item11, item12, item13, item14, item15, item16, item17).stream())
              .collect(Collectors.toList()).toString(),
          "Test that getFullMenu() returns the full menu");
    } catch (Exception e) {
      fail("Exception thrown while testing getting the full menu; " + e.getMessage());
    }
    System.out.println("[TestMenuService] => Test 04 testGetFullMenu finished");
  }

  @Test
  @Transactional
  void testAddNewMenuItem() { // Test 05
    System.out.println("[TestMenuService] => Test 05 testAddNewMenuItem started");
    try {
      MenuItem newItem = new MenuItem(10018, 1, "NewDish", "Brand new dinner item", 10.00f,
          Arrays.asList(""), 100, true);
      assertEquals(testMS.getMenuItem(10018), null,
          "Test that the new menu item is not already in the database");
      testMS.addMenuItem(newItem);
      assertTrue(testMS.getMenuItem(10018).equals(newItem),
          "Test that the new menu item is present in the database after adding it");
      assertEquals(testMS.getFullMenu().toString(), Stream
          .concat(currentMenu.stream(),
              Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10,
                  item11, item12, item13, item14, item15, item16, item17, newItem).stream())
          .collect(Collectors.toList()).toString(),
          "Test that the full menu is returned correctly after adding the new item to the database");
    } catch (Exception e) {
      fail("Exception thrown while testing adding a new item to the menu; " + e.getMessage());
    }
    System.out.println("[TestMenuService] => Test 05 testAddNewMenuItem finished");
  }

  @Test
  @Transactional
  void testRemoveMenuItem() { // Test 06
    System.out.println("[TestMenuService] => Test 06 testRemoveMenuItem started");
    try {
      assertTrue(testMS.getMenuItem(10001).equals(item1),
          "Test that the first item is already in the database");
      testMS.deleteMenuItem(10001);
      assertEquals(testMS.getMenuItem(10001), null,
          "Test that the first item has been deleted from the database");
      assertEquals(testMS.getFullMenu().toString(),
          Stream
              .concat(currentMenu.stream(),
                  Arrays.asList(item2, item3, item4, item5, item6, item7, item8, item9, item10,
                      item11, item12, item13, item14, item15, item16, item17).stream())
              .collect(Collectors.toList()).toString(),
          "Test that the full menu is returned correctly without the first item, after it was deleted from the database");
    } catch (Exception e) {
      fail("Exception thrown while testing removing the first item from the menu; "
          + e.getMessage());
    }
    System.out.println("[TestMenuService] => Test 06 testRemoveMenuItem finished");
  }

  @Test
  @Transactional
  void stressTest_AddRemoveMenuItems() { // Test 07
    System.out.println("[TestMenuService] => Test 07 stressTest_AddRemoveMenuItems started");
    try {
      // Setup a list mimicking the database for tracking what has been added and removed
      ArrayList<MenuItem> stressTestMenuList = new ArrayList<>();
      currentMenu.forEach(item -> stressTestMenuList.add(item));
      Arrays
          .asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11,
              item12, item13, item14, item15, item16, item17)
          .forEach(item -> stressTestMenuList.add(item));

      System.out.println("[TestMenuService] => Stress testing adding 2000 items to the database...");
      // Adding 2000 items to the database
      for (int i = 0; i < 2000; i++) {
        MenuItem newItem = new MenuItem(i + 11000, 1, "Stress Test Dish " + String.valueOf(i),
            "The " + String.valueOf(i) + "th item in the stress test", 100.00f, Arrays.asList(""),
            100 + i, true);
        // Check newItem is not already in the database
        assertEquals(testMS.getMenuItem(i + 11000), null,
            "Test that the " + String.valueOf(i) + "th item is not yet in the database");
        // Add newItem to the database
        testMS.addMenuItem(newItem);
        stressTestMenuList.add(newItem);
        assertTrue(testMS.getMenuItem(i + 11000).equals(newItem),
            "Test that the " + String.valueOf(i) + "th item is now added to the database");
        assertEquals(testMS.getFullMenu().toString(), stressTestMenuList.toString(),
            "Test that the " + String.valueOf(i) + "th item shows up in the full menu");
      }

      System.out.println("[TestMenuService] => Stress testing deleting 2000 items from the database...");
      // Deleting 2000 items from the database
      for (int i = 0; i < 2000; i++) {
        stressTestMenuList
            .remove(new MenuItem(i + 11000, 1, "Stress Test Dish " + String.valueOf(i),
                "The " + String.valueOf(i) + "th item in the stress test", 100.00f,
                Arrays.asList(""), 100 + i, true));
        testMS.deleteMenuItem(i + 11000);
        assertEquals(testMS.getMenuItem(i + 11000), null,
            "Test that the " + String.valueOf(i) + "th item is deleted from the database");
        assertEquals(testMS.getFullMenu().toString(), stressTestMenuList.toString(),
            "Test that the " + String.valueOf(i) + "th item no longer shows up in the full menu");
      }

      assertEquals(testMS.getFullMenu().toString(),
          Stream
              .concat(currentMenu.stream(),
                  Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9,
                      item10, item11, item12, item13, item14, item15, item16, item17).stream())
              .collect(Collectors.toList()).toString(),
          "Test that the menu is now back to its original state before the stress test");
    } catch (Exception e) {
      fail("Exception thrown while stress testing adding and removing items from the menu; "
          + e.getMessage());
    }
    System.out.println("[TestMenuService] => Test 07 stressTest_AddRemoveMenuItems finished");
  }

  @Test
  @Transactional
  void testUpdateMenuItem() { // Test 08
    System.out.println("[TestMenuService] => Test 08 testUpdateMenuItem started");
    try {
      // Prerequisite checks
      assertTrue(testMS.getMenuItem(10001).equals(item1),
          "Test that the first item is in the menu unmodified");
      assertEquals(testMS.getFullMenu().toString(),
          Stream
              .concat(currentMenu.stream(),
                  Arrays.asList(item1, item2, item3, item4, item5, item6, item7, item8, item9,
                      item10, item11, item12, item13, item14, item15, item16, item17).stream())
              .collect(Collectors.toList()).toString(),
          "Test that the full menu is in the original state before the updating the first item");

      // Updating the menu
      MenuItem updatedItem1 = new MenuItem(10001, 1, "Tlayudas 2.0 (Vegetarian option)",
          "Larger, thinner, crispier tortillas with more vegetable toppings.", 7.50f,
          Arrays.asList("Gluten"), 778, true);
      testMS.updateMenuItem(10001, updatedItem1);

      // Checking the menu has been updated correctly
      assertEquals(testMS.getMenuItem(10001).toString(), updatedItem1.toString(),
          "Test that the first item has now been updated");
      assertEquals(testMS.getFullMenu().toString(), Stream
          .concat(currentMenu.stream(),
              Arrays.asList(updatedItem1, item2, item3, item4, item5, item6, item7, item8, item9,
                  item10, item11, item12, item13, item14, item15, item16, item17).stream())
          .collect(Collectors.toList()).toString(),
          "Test that the menu now shows the updated first item");
    } catch (Exception e) {
      fail("Exception thrown while testing updating an existing item in the menu; "
          + e.getMessage());
    }
    System.out.println("[TestMenuService] => Test 08 testUpdateMenuItem finished");
  }
}
