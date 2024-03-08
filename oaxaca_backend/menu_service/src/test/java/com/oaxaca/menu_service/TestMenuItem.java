package com.oaxaca.menu_service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestMenuItem {

  @Test
  void testCreateMenuItem_NoImage() { // Test 01
    System.out.println("[TestMenuItem] => Test 01 testCreateMenuItem_NoImage started");
    try {
      MenuItem testMI_NoImg = new MenuItem(1, 1, "Test Dish",
          "Testing creating a dish with JUnit Jupyter.", 10.00f, null, 123, true);
      assertTrue(testMI_NoImg instanceof MenuItem,
          "Test that a new MenuItem instance without an image was created successfully");
    } catch (Exception e) {
      fail("Exception " + e
          + " thrown while testing creating an instance of MenuItem without an image; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 01 testCreateMenuItem_NoImage finished");
  }

  @Test
  void testCreateMenuItem_WithImage() { // Test 02
    System.out.println("[TestMenuItem] => Test 02 testCreateMenuItem_WithImage started");
    try {
      MenuItem testMI_WithImg =
          new MenuItem(1, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertTrue(testMI_WithImg instanceof MenuItem,
          "Test that a new MenuItem instance with an image was created successfully");
    } catch (Exception e) {
      fail("Exception " + e
          + " thrown while testing creating an instance of MenuItem with an image; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 02 testCreateMenuItem_WithImage finished");
  }

  @Test
  void testGetMenuItemId() { // Test 03
    System.out.println("[TestMenuItem] => Test 03 testGetMenuItemId started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals(5, testMI.getId(), "Test that a MenuItem instance returns the correct Id value");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing getId() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 03 testGetMenuItemId finished");
  }

  @Test
  void testSetMenuItemId() { // Test 04
    System.out.println("[TestMenuItem] => Test 04 testSetMenuItemId started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals(5, testMI.getId(), "Test that a MenuItem instance returns the correct Id value");
      testMI.setId(4);
      assertEquals(4, testMI.getId(),
          "Test that a MenuItem instance returns the correct Id value after changing it");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing setId() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 04 testSetMenuItemId finished");
  }

  @Test
  void testGetMenuItemCategory() { // Test 05
    System.out.println("[TestMenuItem] => Test 05 testGetMenuItemCategory started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals(1, testMI.getCategory(),
          "Test that a MenuItem instance returns the correct Category value");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing getCategory() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 05 testGetMenuItemCategory finished");
  }

  @Test
  void testSetMenuItemCategory() { // Test 06
    System.out.println("[TestMenuItem] => Test 06 testSetMenuItemCategory started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals(1, testMI.getCategory(), "Test that a MenuItem instance returns the correct Category value");
      testMI.setCategory(2);
      assertEquals(2, testMI.getCategory(),
          "Test that a MenuItem instance returns the correct Category value after changing it");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing setCategory() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 06 testSetMenuItemCategory finished");
  }

  @Test
  void testGetMenuItemName() { // Test 07
    System.out.println("[TestMenuItem] => Test 07 testGetMenuItemName started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals("Test Dish", testMI.getName(),
          "Test that a MenuItem instance returns the correct name");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing getName() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 07 testGetMenuItemName finished");
  }

  @Test
  void testSetMenuItemName() { // Test 08
    System.out.println("[TestMenuItem] => Test 08 testSetMenuItemName started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals("Test Dish", testMI.getName(),
          "Test that a MenuItem instance returns the correct name");
      testMI.setName("Updated Test Dish");
      assertEquals("Updated Test Dish", testMI.getName(),
          "Test that a MenuItem instance returns the correct name after changing it");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing setName() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 08 testSetMenuItemName finished");
  }
  
  @Test
  void testGetMenuItemDescription() { // Test 09
    System.out.println("[TestMenuItem] => Test 09 testGetMenuItemDescription started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals("Testing creating a dish with JUnit Jupyter.", testMI.getDescription(),
          "Test that a MenuItem instance returns the correct description");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing getDescription() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 09 testGetMenuItemDescription finished");
  }

  @Test
  void testSetMenuItemDescription() { // Test 10
    System.out.println("[TestMenuItem] => Test 10 testSetMenuItemDescription started");
    try {
      MenuItem testMI =
          new MenuItem(5, 1, "Test Dish", "Testing creating a dish with JUnit Jupyter.", 10.00f,
              null, 123, true, "https://i.imgur.com/yrThUBQ.jpg");
      assertEquals("Testing creating a dish with JUnit Jupyter.", testMI.getDescription(),
          "Test that a MenuItem instance returns the correct description");
      testMI.setDescription("Updated testing description");
      assertEquals("Updated testing description", testMI.getDescription(),
          "Test that a MenuItem instance returns the correct description after changing it");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing setDescription() on a MenuItem instance; "
          + e.getMessage());
    }
    System.out.println("[TestMenuItem] => Test 10 testSetMenuItemDescription finished");
  }
  
}
