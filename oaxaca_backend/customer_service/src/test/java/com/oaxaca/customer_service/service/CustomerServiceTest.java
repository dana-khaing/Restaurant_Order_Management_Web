
package com.oaxaca.customer_service.service;

import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    @InjectMocks
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindCustomerById_found() {
        Customer customer = new Customer();
        customer.setId(1L);
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer found = customerService.findCustomerById(1L);

        assertEquals(customer, found);
    }

    @Test
    public void testFindCustomerById_notFound() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Customer found = customerService.findCustomerById(1L);

        assertNull(found);
    }
}