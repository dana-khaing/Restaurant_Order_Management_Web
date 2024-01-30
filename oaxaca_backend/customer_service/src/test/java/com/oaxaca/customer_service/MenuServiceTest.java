package com.oaxaca.customer_service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.oaxaca.customer_service.service.*;

class MenuServiceTest {

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

}
