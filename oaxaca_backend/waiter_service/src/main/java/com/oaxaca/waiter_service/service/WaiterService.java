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

    

}
