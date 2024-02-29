package com.oaxaca.waiter_summon_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WaiterSummonService {
  
  private WaiterSummonRequestRepository waiterSummonRequestRepository;
  
  @Autowired
  public WaiterSummonService(WaiterSummonRequestRepository waiterSummonRequestRepository) {
    this.waiterSummonRequestRepository = waiterSummonRequestRepository;
  }

}
