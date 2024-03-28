package com.oaxaca.waiter_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oaxaca.waiter_service.exception.WaiterCreationFailedException;
import com.oaxaca.waiter_service.model.Waiter;
import com.oaxaca.waiter_service.repository.WaiterRepository;

@Service
public class WaiterService {

    @Autowired
    private WaiterRepository waiterRepository;

    public WaiterService() {
    }
    /**
     * Constructs a new WaiterService with the provided repository.
     * 
     * @param waiterRepository The repository for waiters.
     */
    public WaiterService(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }
    /**
     * Creates a new waiter.
     * 
     * @param waiter The waiter to create.
     * @return The created waiter.
     * @throws WaiterCreationFailedException If waiter creation fails.
     */
    public Waiter createWaiter(Waiter waiter)  {
        if (waiter == null) {
            throw new WaiterCreationFailedException("Waiter creation failed");
        }

        if (waiter.getFirstName() == null || waiter.getFirstName().isEmpty() ||
                waiter.getLastName() == null || waiter.getLastName().isEmpty() ||
                waiter.getUsername() == null || waiter.getUsername().isEmpty() ||
                waiter.getPassword() == null || waiter.getPassword().isEmpty() ||
                waiter.getEmail() == null || waiter.getEmail().isEmpty() ||
                waiter.getRestaurantName() == null || waiter.getRestaurantName().isEmpty() ||
                waiter.getManagerName() == null || waiter.getManagerName().isEmpty()) {
            throw new WaiterCreationFailedException("Waiter creation failed: missing required fields");
        }

        return waiterRepository.save(waiter);
    }
    /**
     * Finds a waiter by their ID.
     * 
     * @param id The ID of the waiter to find.
     * @return The waiter if found, else null.
     */
    public Waiter findWaiterById(Long id) {
        return waiterRepository.findById(id).orElse(null);
    }
    /**
     * Finds a waiter by their username.
     * 
     * @param username The username of the waiter to find.
     * @return The waiter if found, else null.
     */
    public Waiter findWaiterByUsername(String username) {
        return waiterRepository.findByUsername(username).orElse(null);
    }

}
