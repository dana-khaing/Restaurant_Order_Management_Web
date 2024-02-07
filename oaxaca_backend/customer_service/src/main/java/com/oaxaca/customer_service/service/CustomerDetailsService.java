package com.oaxaca.customer_service.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.model.CustomerDetails;
import com.oaxaca.customer_service.repository.CustomerRepository;

public class CustomerDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> optionalUser = customerRepository.findByUsername(username);
        
        if(optionalUser.isPresent()) {
            return new CustomerDetails(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User not found");
        }


    }

}
