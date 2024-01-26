package com.oaxaca_backend.customer_service.service;

import org.springframework.stereotype.Service;
import com.oaxaca_backend.customer_service.model.Customer;
import com.oaxaca_backend.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService() {
    }

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {

        if (customer == null || customer.getUsername() == null || customer.getUsername().isEmpty() || customer.getPassword() == null || customer.getPassword().isEmpty() || customer.getEmail() == null || customer.getEmail().isEmpty()){
            throw new IllegalArgumentException("Customer cannot be null");
        }

        return customerRepository.save(customer);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }
    

  

    
}
