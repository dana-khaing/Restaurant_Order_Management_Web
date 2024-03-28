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
    /**
     * Constructs a new WaiterDetailsService with the provided repository.
     * 
     * @param waiterRepository The repository for waiters.
     */
    public WaiterDetailsService(WaiterRepository waiterRepository) {
        this.waiterRepository = waiterRepository;
    }
    /**
     * Loads waiter details by username.
     * 
     * @param username The username of the waiter.
     * @return UserDetails representing the waiter.
     * @throws UsernameNotFoundException If the username is not found.
     */
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
