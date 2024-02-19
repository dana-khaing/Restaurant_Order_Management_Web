package com.oaxaca.waiter_service.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.oaxaca.waiter_service.model.Waiter;
import com.oaxaca.waiter_service.model.WaiterDetails;
import com.oaxaca.waiter_service.repository.WaiterRepository;

public class WaiterDetailsService implements UserDetailsService {

    @Autowired
    private WaiterRepository waiterRepository;

    public WaiterDetailsService(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Waiter> optionalUser = waiterRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            return new WaiterDetails(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
    
}
