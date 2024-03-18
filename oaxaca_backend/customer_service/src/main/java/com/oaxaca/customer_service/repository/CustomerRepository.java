package com.oaxaca.customer_service.repository;

import com.oaxaca.customer_service.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Optional<Customer> findByUsername(String username);

    Optional<Customer> findByEmail(String email);
    @NonNull 
    Optional<Customer> findById(@NonNull Long id);




}