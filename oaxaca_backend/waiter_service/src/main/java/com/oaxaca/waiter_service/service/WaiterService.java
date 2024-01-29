package com.oaxaca.waiter_service.service;

import java.lang.reflect.Field;

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

    public WaiterService(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    public Waiter createWaiter(Waiter waiter) throws IllegalArgumentException, IllegalAccessException {
        if (waiter == null) {
            throw new WaiterCreationFailedException("Waiter creation failed");
        }

        if (waiter.getName() == null || waiter.getName().isEmpty() ||
                waiter.getLastname() == null || waiter.getLastname().isEmpty() ||
                waiter.getUsername() == null || waiter.getUsername().isEmpty() ||
                waiter.getPassword() == null || waiter.getPassword().isEmpty() ||
                waiter.getEmail() == null || waiter.getEmail().isEmpty() ||
                waiter.getRestaurantName() == null || waiter.getRestaurantName().isEmpty() ||
                waiter.getManagerName() == null || waiter.getManagerName().isEmpty()) {
            throw new WaiterCreationFailedException("Waiter creation failed: missing required fields");
        }

        return waiterRepository.save(waiter);
    }

    public Waiter findWaiterById(Long id) {
        return waiterRepository.findById(id).orElse(null);
    }

    public Waiter findWaiterByUsername(String username) {
        return waiterRepository.findByUsername(username).orElse(null);
    }

}
