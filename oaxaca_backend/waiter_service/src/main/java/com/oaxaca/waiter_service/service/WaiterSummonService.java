package com.oaxaca.waiter_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.oaxaca.waiter_service.model.WaiterSummonRequest;
import com.oaxaca.waiter_service.repository.WaiterSummonRequestRepository;

@Service
public class WaiterSummonService {
  
  private WaiterSummonRequestRepository waiterSummonRequestRepository;
  /**
   * Constructs a new WaiterSummonService with the provided repository.
   * 
   * @param waiterSummonRequestRepository The repository for waiter summon requests.
   */
  public WaiterSummonService(WaiterSummonRequestRepository waiterSummonRequestRepository) {
    this.waiterSummonRequestRepository = waiterSummonRequestRepository;
  }
  /**
   * Retrieves all waiter summon requests.
   * 
   * @return A list of all waiter summon requests.
   */
  public List<WaiterSummonRequest> getAllWaiterSummonRequests() {
    List<WaiterSummonRequest> allRequests = new ArrayList<>();
    waiterSummonRequestRepository.findAll().forEach(allRequests::add);
    return allRequests;
  }
  /**
   * Retrieves a waiter summon request by its ID.
   * 
   * @param id The ID of the waiter summon request to retrieve.
   * @return The waiter summon request with the specified ID, or null if not found.
   */
  public WaiterSummonRequest getWaiterSummonRequest(int id) {
    return waiterSummonRequestRepository.findById(id).orElse(null);
  }
  /**
   * Adds a new waiter summon request.
   * 
   * @param wsr The waiter summon request to add.
   */
  public void addWaiterSummonRequest(WaiterSummonRequest wsr) {

    if(wsr == null) {
      return;
    }

    waiterSummonRequestRepository.save(wsr);
  }
  /**
   * Updates an existing waiter summon request.
   * 
   * @param wsr The updated waiter summon request.
   */
  public void updateWaiterSummonRequest(WaiterSummonRequest wsr) {
    if(wsr == null) {
      return;
    }
    waiterSummonRequestRepository.save(wsr);
  }
  /**
   * Deletes a waiter summon request by its ID.
   * 
   * @param id The ID of the waiter summon request to delete.
   */
  public void deleteWaiterSummonRequest(int id) {
    waiterSummonRequestRepository.deleteById(id);
  }
  /**
   * Marks a waiter summon request as served.
   * 
   * @param id The ID of the waiter summon request to mark as served.
   */
  public void markAsServed(int id) {
    WaiterSummonRequest wsr = waiterSummonRequestRepository.findById(id).orElse(null);
    if (wsr == null) {
      return;
    }
    wsr.setIsCustomerServed(true);
  }
}
