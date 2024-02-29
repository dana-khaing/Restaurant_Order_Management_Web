package com.oaxaca.waiter_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oaxaca.waiter_service.repository.WaiterSummonRequestRepository;
import java.util.ArrayList;
import java.util.List;
import com.oaxaca.waiter_service.model.WaiterSummonRequest;

@Service
public class WaiterSummonService {
  
  private WaiterSummonRequestRepository waiterSummonRequestRepository;
  
  @Autowired
  public WaiterSummonService(WaiterSummonRequestRepository waiterSummonRequestRepository) {
    this.waiterSummonRequestRepository = waiterSummonRequestRepository;
  }

  public List<WaiterSummonRequest> getAllWaiterSummonRequests(){
    List<WaiterSummonRequest> waiterSummonRequests = new ArrayList<>();
    return waiterSummonRequests;
  }
}
