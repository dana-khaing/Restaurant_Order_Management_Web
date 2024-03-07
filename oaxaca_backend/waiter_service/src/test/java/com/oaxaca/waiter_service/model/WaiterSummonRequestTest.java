package com.oaxaca.waiter_service.model;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.Test;

class WaiterSummonRequestTest {

  @Test
  void testCreateWaiterSummonRequest() { // Test 01
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 5);
      assertTrue(testWSR instanceof WaiterSummonRequest,
          "Test that a new WaiterSummonRequest was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to create a new WaiterSummonRequest instance; "
          + e.getMessage());
    }
  }

  @Test
  void testGetWSR_Id() { // Test 02
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 4);
      assertEquals(0, testWSR.getRequestId(),
          "Test that the getter for the RequestId works correctly");
    } catch (Exception e) {
      fail("Exception thrown while trying to get the ID of a WaiterSummonRequest; "
          + e.getMessage());
    }
  }

  @Test
  void testGetWSR_Customer() { // Test 03
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(
          new Customer("tester@gmail.com", "TestingAccount", "InsecurePassword"), 5);
      assertTrue(testWSR.getCustomer() instanceof Customer,
          "Test that the customer getter from WaiterSummonRequest returns an instance of the Customer class");

      // Test that the Customer retrieved from getter method is the same Customer initialized at the
      // beginning of the test
      assertEquals("tester@gmail.com", testWSR.getCustomer().getEmail(),
          "Test that the email address was initialized correctly on the WaiterSummonRequest's Customer");
      assertEquals("TestingAccount", testWSR.getCustomer().getUsername(),
          "Test that the username was initialized correctly on the WaiterSummonRequest's Customer");
      assertEquals("InsecurePassword", testWSR.getCustomer().getPassword(),
          "Test that the password was initialized correctly on the WaiterSummonRequest's Customer");
    } catch (Exception e) {
      fail(
          "Exception thrown while trying to get the Customer instance of a WaiterSummonRequest instance; "
              + e.getMessage());
    }
  }

  @Test
  void testSetWSR_Customer() {
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(
          new Customer("tester@gmail.com", "TestingAccount", "InsecurePassword"), 5);

      // Test that the Customer retrieved from getter method is the same Customer initialized at the
      // beginning of the test
      assertEquals("tester@gmail.com", testWSR.getCustomer().getEmail(),
          "Test that the email address was initialized correctly on the WaiterSummonRequest's Customer");
      assertEquals("TestingAccount", testWSR.getCustomer().getUsername(),
          "Test that the username was initialized correctly on the WaiterSummonRequest's Customer");
      assertEquals("InsecurePassword", testWSR.getCustomer().getPassword(),
          "Test that the password was initialized correctly on the WaiterSummonRequest's Customer");

      // Set a new customer
      testWSR.setCustomer(new Customer("tester2@gmail.com", "TestingAccount2", "BadPassword"));

      // Verify that the new Customer has been set successfully
      assertEquals("tester2@gmail.com", testWSR.getCustomer().getEmail(),
          "Test that the email address was updated correctly with setCustomer() on the WaiterSummonRequest instance");
      assertEquals("TestingAccount2", testWSR.getCustomer().getUsername(),
          "Test that the username was updated correctly with setCustomer() on the WaiterSummonRequest instance");
      assertEquals("BadPassword", testWSR.getCustomer().getPassword(),
          "Test that the password was updated correctly with setCustomer() on the WaiterSummonRequest instance");
    } catch (Exception e) {
      fail(
          "Exception thrown while trying to set the Customer instance of a WaiterSummonRequest instance; "
              + e.getMessage());
    }
  }

  @Test
  void testGetWSR_TableNumber() {
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 10);
      assertEquals(10, testWSR.getTableNumber(),
          "Test that the getter for tableNumber works correctly");
    } catch (Exception e) {
      fail("Exception thrown while trying to get the tableNumber of a WaiterSummonRequest; "
          + e.getMessage());
    }
  }

  @Test
  void testSetWSR_TableNumber() {
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 10);
      assertEquals(10, testWSR.getTableNumber(),
          "Test that the getter for tableNumber is originally 10 on a WaiterSummonRequest");

      // Now set a new table number and check it updated correctly
      testWSR.setTableNumber(9);
      assertEquals(9, testWSR.getTableNumber(),
          "Test that setTableNumber() updated tableNumber to 9 on a WaiterSummonRequest");
    } catch (Exception e) {
      fail("Exception thrown while trying to set the tableNumber of a WaiterSummonRequest; "
          + e.getMessage());
    }
  }

  @Test
  void testGetWSR_SummonRequestTime() {
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 11);
      // It is not possible to test this using an assertEquals() as the time will change before it
      // is tested. Instead, just check that it returns an instance of OffsetDateTime.
      assertTrue(testWSR.getSummonRequestTime() instanceof OffsetDateTime,
          "Test that the getter for summonRequestTime works correctly");
    } catch (Exception e) {
      fail("Exception thrown while trying to get the summonRequestTime of a WaiterSummonRequest; "
          + e.getMessage());
    }
  }

  @Test
  void testSetWSR_SummonRequestTime() {
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 12);
      OffsetDateTime newTime = OffsetDateTime.now(ZoneId.of("Europe/London"));

      // Now set a new summonRequestTime and check it updated correctly
      testWSR.setSummonRequestTime(newTime);
      assertEquals(newTime, testWSR.getSummonRequestTime(),
          "Test that setSummonRequestTime() updated summonRequestTime correctly to a new time on a WaiterSummonRequest");
    } catch (Exception e) {
      fail("Exception thrown while trying to set the summonRequestTime of a WaiterSummonRequest; "
          + e.getMessage());
    }
  }
  
  @Test
  void testGetWSR_IsCustomerServed() {
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 10);
      assertEquals(false, testWSR.getIsCustomerServed(),
          "Test that the getter for whether the customer is served yet works correctly");
    } catch (Exception e) {
      fail("Exception thrown while trying to get isCustomerServed of a WaiterSummonRequest; "
          + e.getMessage());
    }
  }
  
  @Test
  void testSetWSR_IsCustomerServed() {
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest(new Customer(), 1);
      assertEquals(false, testWSR.getIsCustomerServed(),
          "Test that the getter for isCustomerServed is originally false on a WaiterSummonRequest");

      // Now set a new status and check it updated correctly
      testWSR.setIsCustomerServed(true);
      assertEquals(true, testWSR.getIsCustomerServed(),
          "Test that setIsCustomerServed() updated isCustomerServed to true on a WaiterSummonRequest");
      
      // Since isCustomerServed can only be true or false, we can test the setter for both cases
      testWSR.setIsCustomerServed(false);
      assertEquals(false, testWSR.getIsCustomerServed(),
          "Test that setIsCustomerServed() updated isCustomerServed to false on a WaiterSummonRequest");
    } catch (Exception e) {
      fail("Exception thrown while trying to set isCustomerServed of a WaiterSummonRequest; "
          + e.getMessage());
    }
  }
}
