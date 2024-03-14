package com.oaxaca.waiter_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.oaxaca.waiter_service.model.WaiterSummonRequest;
import com.oaxaca.waiter_service.repository.WaiterSummonRequestRepository;

@Service
public class WaiterSummonService {
  
  private WaiterSummonRequestRepository waiterSummonRequestRepository;
  
  public WaiterSummonService(WaiterSummonRequestRepository waiterSummonRequestRepository) {
    this.waiterSummonRequestRepository = waiterSummonRequestRepository;
  }
  
  public List<WaiterSummonRequest> getAllWaiterSummonRequests() {
    List<WaiterSummonRequest> allRequests = new ArrayList<>();
    waiterSummonRequestRepository.findAll().forEach(allRequests::add);
    return allRequests;
  }
  
  public WaiterSummonRequest getWaiterSummonRequest(int id) {
    return waiterSummonRequestRepository.findById(id).orElse(null);
  }
  
  public void addWaiterSummonRequest(WaiterSummonRequest wsr) {

    if(wsr == null) {
      return;
    }

    waiterSummonRequestRepository.save(wsr);
  }

  public void updateWaiterSummonRequest(WaiterSummonRequest wsr) {
    if(wsr == null) {
      return;
    }
    waiterSummonRequestRepository.save(wsr);
  }
  
  public void deleteWaiterSummonRequest(int id) {
    waiterSummonRequestRepository.deleteById(id);
  }
  
  public void markAsServed(int id) {
    WaiterSummonRequest wsr = waiterSummonRequestRepository.findById(id).orElse(null);
    if (wsr == null) {
      return;
    }
    wsr.setIsCustomerServed(true);
  }
}
