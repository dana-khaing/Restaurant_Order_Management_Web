package com.oaxaca_backend.customer_service.service;

import com.oaxaca_backend.customer_service.model.Customer;
import com.oaxaca_backend.customer_service.repository.CustomerRepository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer("name", "email", "username", "password", "address", "phone", "creditCard");
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerService.createCustomer(customer);
        assertEquals(customer, savedCustomer);
    }

    @Test
    void testSaveCustomerEssentialFields() {
        Customer customer = new Customer("email", "username", "password");
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerService.createCustomer(customer);
        assertEquals(customer, savedCustomer);
    }

    @Test
    void testSaveCustomerNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(null);
        });
    }

    @Test
    void testSaveCustomerNullUserName() {
        Customer customer = new Customer("name", "email", null, "password", "address", "phone", "creditCard");
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(customer);
        });
    }

    @Test
    void testSaveCustomerEmptyUserName(){
        Customer customer = new Customer("name", "email", "", "password", "address", "phone", "creditCard");
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(customer);
        });
    }

    @Test
    void testSaveCustomerNullEmail() {
        Customer customer = new Customer("name", null, "username", "password", "address", "phone", "creditCard");
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(customer);
        });
    }

    @Test
    void testSaveCustomerEmptyEmail(){
        Customer customer = new Customer("name", "", "username", "password", "address", "phone", "creditCard");
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(customer);
        });
    }

    @Test
    void testSaveCustomerNullPassword() {
        Customer customer = new Customer("name", "email", "username", null, "address", "phone", "creditCard");
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(customer);
        });
    }

    @Test
    void testSaveCustomerEmptyPassword(){
        Customer customer = new Customer("name", "email", "username", "", "address", "phone", "creditCard");
        assertThrows(IllegalArgumentException.class, () -> {
            customerService.createCustomer(customer);
        });
    }


    @Test
    void testFindCustomerById() {
        Customer customer = new Customer("name", "email", "username", "password", "address", "phone", "creditCard");
        when(customerRepository.findById(1L)).thenReturn(java.util.Optional.of(customer));
        Customer foundCustomer = customerService.findCustomerById(1L);
        assertEquals(customer, foundCustomer);
    }

    



    




    
}
