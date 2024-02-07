package com.oaxaca.customer_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.oaxaca.customer_service.model.Customer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Test
    public void testLoginCustomerSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        CustomerController controller = new CustomerController(authenticationManager);

        Authentication authentication = mock(Authentication.class);
        Customer customer = new Customer("username", "password", "email");
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Act
        ResponseEntity<?> response = controller.loginCustomer(customer);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer Logged in Successfully", response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testLoginCustomerFail() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        CustomerController controller = new CustomerController(authenticationManager);
        Customer customer = new Customer("username", "password", "email");
        when(authenticationManager.authenticate(any())).thenReturn(null);

        // Act
        ResponseEntity<?> response = controller.loginCustomer(customer);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Customer Login Failed", response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
    }
}