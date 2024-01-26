package com.oaxaca_backend.customer_service.repository;

import com.yourcompany.customerservice.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
    Customer findByUsername(String username);

    Customer findByEmail(String email);

    Customer findById(Long id);



}