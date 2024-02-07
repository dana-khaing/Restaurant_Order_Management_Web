package com.oaxaca.customer_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/register")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (customer == null || customer.getUsername() == null || customer.getUsername().isEmpty()
                || customer.getPassword() == null || customer.getPassword().isEmpty() || customer.getEmail() == null
                || customer.getEmail().isEmpty()) {
            return new ResponseEntity<>("Customer creation failed", HttpStatus.BAD_REQUEST);
        }

        Customer createdCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/find/id/{id}")
    public Customer findCustomerById(@PathVariable Long id) {
        return customerService.findCustomerById(id);
    }

    @GetMapping("/find/username/{username}")
    public Customer findCustomerByUsername(@PathVariable String username) {
        return customerService.findCustomerByUsername(username);
    }

    @GetMapping("/find/email/{email}")
    public Customer findCustomerByEmail(@PathVariable String email) {
        return customerService.findCustomerByEmail(email);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer) {
        if (customer == null || customer.getUsername() == null || customer.getUsername().isEmpty()
                || customer.getPassword() == null || customer.getPassword().isEmpty()) {
            return new ResponseEntity<>("Customer login failed. Wrong username or password", HttpStatus.BAD_REQUEST);
        }

        Customer foundCustomer = customerService.findCustomerByUsername(customer.getUsername());
        if (foundCustomer == null) {
            return new ResponseEntity<>("Customer login failed", HttpStatus.BAD_REQUEST);
        }

        if (!foundCustomer.getPassword().equals(customer.getPassword())) {
            return new ResponseEntity<>("Customer login failed. Wrong username or password", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

}
