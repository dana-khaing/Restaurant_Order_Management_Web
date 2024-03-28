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
  /**
   * Retrieves all waiter summon requests.
   * 
   * @return A list of all waiter summon requests.
   */
  @RequestMapping("/waiter-summons")
  public List<WaiterSummonRequest> getAllWaiterSummonRequests() {
    return waiterSummonService.getAllWaiterSummonRequests();
  }
  /**
   * Retrieves a specific waiter summon request by its ID.
   * 
   * @param id The ID of the waiter summon request to retrieve.
   * @return The waiter summon request with the specified ID.
   */
  @RequestMapping("/waiter-summons/{id}")
  public WaiterSummonRequest getWaiterSummonRequest(@PathVariable int id) {
    return waiterSummonService.getWaiterSummonRequest(id);
  }
  /**
   * Adds a new waiter summon request.
   * 
   * @param wsr The waiter summon request to add.
   */
  @PostMapping("/waiter-summons")
  public void addWaiterSummonRequest(@RequestBody WaiterSummonRequest wsr) {
    waiterSummonService.addWaiterSummonRequest(wsr);
  }
  /**
   * Updates an existing waiter summon request.
   * 
   * @param wsr The updated waiter summon request.
   * @param id  The ID of the waiter summon request to update.
   */
  @PutMapping("/waiter-summons/{id}")
  public void updateWaiterSummonRequest(@RequestBody WaiterSummonRequest wsr, @PathVariable int id) {
    waiterSummonService.updateWaiterSummonRequest(wsr);
  }
  /**
   * Deletes a waiter summon request by its ID.
   * 
   * @param id The ID of the waiter summon request to delete.
   */
  @DeleteMapping("/waiter-summons/{id}")
  public void deleteWaiterSummonRequest(@PathVariable int id) {
    waiterSummonService.deleteWaiterSummonRequest(id);
  }
}
