package com.oaxaca.waiter_service.service;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.oaxaca.waiter_service.repository.WaiterSummonRequestRepository;


class WaiterSummonServiceTest {

  @MockBean
  private WaiterSummonService testWSS;

  @Mock
  private WaiterSummonRequestRepository testWSRR;

  @BeforeEach
  void setUp() throws Exception {
    MockitoAnnotations.openMocks(this);
    testWSS = new WaiterSummonService(testWSRR);
  }
  
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
