package com.oaxaca.waiter_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.oaxaca.waiter_service.model.WaiterSummonRequest;
import com.oaxaca.waiter_service.service.WaiterSummonService;

@RestController
public class WaiterSummonController {

  @Autowired
  private WaiterSummonService waiterSummonService;
  
  @RequestMapping("/waiter-summons")
  public List<WaiterSummonRequest> getAllWaiterSummonRequests() {
    return waiterSummonService.getAllWaiterSummonRequests();
  }
  
  @RequestMapping("/waiter-summons/{id}")
  public WaiterSummonRequest getWaiterSummonRequest(@PathVariable int id) {
    return waiterSummonService.getWaiterSummonRequest(id);
  }
  
  @PostMapping("/waiter-summons")
  public void addWaiterSummonRequest(@RequestBody WaiterSummonRequest wsr) {
    waiterSummonService.addWaiterSummonRequest(wsr);
  }
  
  @PutMapping("/waiter-summons/{id}")
  public void updateWaiterSummonRequest(@RequestBody WaiterSummonRequest wsr, @PathVariable int id) {
    waiterSummonService.updateWaiterSummonRequest(wsr);
  }
  
  @DeleteMapping("/waiter-summons/{id}")
  public void deleteWaiterSummonRequest(@PathVariable int id) {
    waiterSummonService.deleteWaiterSummonRequest(id);
  }
}
