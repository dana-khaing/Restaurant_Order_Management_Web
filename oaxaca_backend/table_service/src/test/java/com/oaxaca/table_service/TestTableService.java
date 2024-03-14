package com.oaxaca.table_service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestTableService {
  
  @Autowired
  private TableService testTS;

  @Test
  void testValidateTableService() { // Test 01
    System.out.println("[TestTableService] => Test 01 testValidateTableService started");
    try {
      assertTrue(testTS instanceof TableService,
          "Test that a new TableService instance was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to validate the TableService instance; " + e.getMessage());
    }
    System.out.println("[TestTableService] => Test 01 testValidateTableService finished");
  }

}
