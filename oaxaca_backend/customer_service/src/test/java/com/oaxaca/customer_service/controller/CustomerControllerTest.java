package com.oaxaca.customer_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.test.util.ReflectionTestUtils;

import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.service.CustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Map;

public class CustomerControllerTest {

    @InjectMocks
    private RememberMeServices rememberMeServices;

    @Mock
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        rememberMeServices = mock(RememberMeServices.class);
        customerService = mock(CustomerService.class);
    }
    

    @Test
    public void testLoginCustomerSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        CustomerController controller = new CustomerController(authenticationManager);
        ReflectionTestUtils.setField(controller, "rememberMeServices", rememberMeServices);

        Authentication authentication = mock(Authentication.class);
        Customer customer = new Customer("username", "password", "email");
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Act
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        ResponseEntity<?> response = controller.loginCustomer(customer, request, res);

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
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        ResponseEntity<?> response = controller.loginCustomer(customer, request, res);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Customer Login Failed", response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
    }

    @Test
    public void testCreateCustomerSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        CustomerService customerService = mock(CustomerService.class);
        CustomerController controller = new CustomerController(authenticationManager, customerService);
        Customer customer = new Customer("username", "password", "email");
        when(customerService.createCustomer(any(Customer.class))).thenReturn(customer);

        // Act
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        ResponseEntity<?> response = controller.createCustomer(customer, request, res);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Map<String, String> expected = Map.of("message", "Customer created successfully");
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testCreateCustomerFail() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        CustomerService customerService = mock(CustomerService.class);
        CustomerController controller = new CustomerController(authenticationManager, customerService);
        Customer customer = new Customer();

        // Act
        HttpServletRequest request = mock(HttpServletRequest.class); 
        HttpServletResponse res = mock(HttpServletResponse.class);
        ResponseEntity<?> response = controller.createCustomer(customer, request, res);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Customer creation failed", response.getBody());
    }
}