package com.oaxaca.table_service;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class TestRestaurantTable {

  @Test
  void testCreateTable_NoWaiter() { // Test 01
    System.out.println("[TestTable] => Test 01 testCreateTable_NoWaiter started");
    try {
      RestaurantTable testTable_Waiterless = new RestaurantTable(1);
      assertTrue(testTable_Waiterless instanceof RestaurantTable,
          "Test that a new Table instance without a waiter was created successfully");
    } catch (Exception e) {
      fail("Exception " + e
          + " thrown while testing creating an instance of Table without a waiter; "
          + e.getMessage());
    }
    System.out.println("[TestTable] => Test 01 testCreateTable_NoWaiter finished");
  }

  @Test
  void testCreateTable_WithWaiter() {
    System.out.println("[TestTable] => Test 02 testCreateTable_WithWaiter started");
    try {
      RestaurantTable testTable_WithWaiter = new RestaurantTable(1, 2);
      assertTrue(testTable_WithWaiter instanceof RestaurantTable,
          "Test that a new Table instance with a waiter was created successfully");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing creating an instance of Table with a waiter; "
          + e.getMessage());
    }
    System.out.println("[TestTable] => Test 02 testCreateTable_WithWaiter finished");
  }

  @Test
  void testGetTableNumber() {
    System.out.println("[TestTable] => Test 03 testGetTableNumber started");
    try {
      RestaurantTable testTable = new RestaurantTable(5);
      assertEquals(5, testTable.getTableNumber(),
          "Test that getTableNumber() returns the correct table number");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing getTableNumber() a Table instance; "
          + e.getMessage());
    }
    System.out.println("[TestTable] => Test 03 testGetTableNumber finished");
  }

  @Test
  void testSetTableNumber() {
    System.out.println("[TestTable] => Test 04 testSetTableNumber started");
    try {
      // First verify that the table number is some value
      RestaurantTable testTable = new RestaurantTable(25);
      assertEquals(25, testTable.getTableNumber(),
          "Test that getTableNumber() returns the correct table number");
      // Now test that setTableNumber() actually changes the value
      testTable.setTableNumber(14);
      assertEquals(14, testTable.getTableNumber(),
          "Test that getTableNumber() returns the correct table number after changing it");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing setTableNumber() on a Table instance; "
          + e.getMessage());
    }
    System.out.println("[TestTable] => Test 04 testSetTableNumber finished");
  }

  @Test
  void testGetAssignedWaiter() {
    System.out.println("[TestTable] => Test 05 testGetAssignedWaiter started");
    try {
      Integer assignedWaiter = 5;
      RestaurantTable testTable = new RestaurantTable(13, assignedWaiter);
      assertTrue(testTable.getAssignedWaiter().equals(assignedWaiter),
          "Test that getAssignedWaiter() returns the correct waiter");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing getAssignedWaiter() on a Table instance; "
          + e.getMessage());
    }
    System.out.println("[TestTable] => Test 05 testGetAssignedWaiter finished");
  }

  @Test
  void testSetAssignedWaiter() {
    System.out.println("[TestTable] => Test 06 testSetAssignedWaiter started");
    try {
      // First verify that the table has some assigned waiter
      Integer assignedWaiter = 18;
      RestaurantTable testTable = new RestaurantTable(55, assignedWaiter);
      assertTrue(testTable.getAssignedWaiter().equals(assignedWaiter),
          "Test that getAssignedWaiter() returns the correct waiter");
      // Now test that setTableNumber() actually changes the value
      Integer newAssignedWaiter = 26;
      testTable.setAssignedWaiter(newAssignedWaiter);
      assertTrue(testTable.getAssignedWaiter().equals(newAssignedWaiter),
          "Test that getAssignedWaiter() returns the correct waiter after changing it");
    } catch (Exception e) {
      fail("Exception " + e + " thrown while testing setAssignedWaiter() on a Table instance; "
          + e.getMessage());
    }
    System.out.println("[TestTable] => Test 06 testSetAssignedWaiter finished");
  }

}
