package com.oaxaca.customer_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/find/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/find/{username}")
    public Customer findCustomerByUsername(@PathVariable String username) {
        return customerService.findCustomerByUsername(username);
    }

    @GetMapping("/find/{email}")
    public Customer findCustomerByEmail(@PathVariable String email) {
        return customerService.findCustomerByEmail(email);
    }
    
}
