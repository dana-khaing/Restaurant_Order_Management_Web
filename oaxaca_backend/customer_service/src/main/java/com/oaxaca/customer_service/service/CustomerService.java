package com.oaxaca.customer_service.service;

import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oaxaca.customer_service.exception.CustomerCreationFailedException;

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
            throw new CustomerCreationFailedException("Customer creation failed");
        }

        return customerRepository.save(customer);
    }

    

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer findCustomerByUsername(String username) {
        return customerRepository.findByUsername(username).orElse(null);
    }

    public Customer findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email).orElse(null);
    }
    

  

    
}
