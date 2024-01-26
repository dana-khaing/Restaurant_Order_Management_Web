package com.oaxaca_backend.customer_service.repository;

import com.oaxaca_backend.customer_service.model.Customer;


import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
   



}