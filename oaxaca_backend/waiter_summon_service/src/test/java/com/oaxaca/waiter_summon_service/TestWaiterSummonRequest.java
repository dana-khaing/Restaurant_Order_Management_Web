package com.oaxaca.waiter_summon_service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TestWaiterSummonRequest {

  @Test
  void testCreateWaiterSummonRequest() { // Test 01
    try {
      WaiterSummonRequest testWSR = new WaiterSummonRequest();
      assertTrue(testWSR instanceof WaiterSummonRequest,
          "Test that a new WaiterSummonRequest was created successfully");
    } catch (Exception e) {
      fail("Exception thrown while trying to create a new WaiterSummonRequest instance; "
          + e.getMessage());
    }
  }

}
