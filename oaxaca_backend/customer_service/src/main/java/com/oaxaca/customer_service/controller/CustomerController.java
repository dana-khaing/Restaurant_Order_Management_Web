package com.oaxaca.customer_service.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    RememberMeServices rememberMeServices;

    @Autowired
    PasswordEncoder passwordEncoder;



    private final AuthenticationManager authenticationManager;

    public CustomerController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public CustomerController(AuthenticationManager authenticationManager, CustomerService customerService) {
        this.authenticationManager = authenticationManager;
        this.customerService = customerService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer, HttpServletRequest request,
            HttpServletResponse response) {
        if (customer == null || customer.getUsername() == null || customer.getUsername().isEmpty()
                || customer.getPassword() == null || customer.getPassword().isEmpty() || customer.getEmail() == null
                || customer.getEmail().isEmpty()) {
            return new ResponseEntity<>("Customer creation failed", HttpStatus.BAD_REQUEST);
        }

        customerService.createCustomer(customer);
    
        Map<String, String> res = new HashMap<>();
        res.put("message", "Customer created successfully");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody Customer customer, HttpServletRequest request,
            HttpServletResponse res) {

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(customer.getUsername(), customer.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        if (authenticationResponse == null) {
            return new ResponseEntity<>("Customer Login Failed", HttpStatus.UNAUTHORIZED);
        }

        rememberMeServices.loginSuccess(request, res, authenticationResponse);

        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer Logged in Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("validate-remember-me")
    public ResponseEntity<?> validateRememberMe(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = rememberMeServices.autoLogin(request, response);

        if (authentication != null && authentication instanceof RememberMeAuthenticationToken) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Map<String, String> result = new HashMap<>();
            result.put("message", "User authenticated with remember-me token");
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body("User not authenticated with remember-me token");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutCustomer(HttpServletRequest request, HttpServletResponse response) {
        rememberMeServices.loginFail(request, response);
        SecurityContextHolder.clearContext();
        Map<String, String> result = new HashMap<>();
        result.put("message", "Customer logged out successfully");
        return ResponseEntity.ok(result);
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

}
