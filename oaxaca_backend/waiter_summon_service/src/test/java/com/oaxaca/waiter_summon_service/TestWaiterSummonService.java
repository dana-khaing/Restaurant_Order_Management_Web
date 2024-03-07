package com.oaxaca.waiter_summon_service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestWaiterSummonService {

  @Autowired
  private WaiterSummonService testWSS;
  
  @Test
  void testValidateWaiterSummonService() { // Test 01
    try {
      assertTrue(testWSS instanceof WaiterSummonService,
          "Test that a new WaiterSummonService instance was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to validate the WaiterSummonService instance; " + e.getMessage());
    }

  }

}
