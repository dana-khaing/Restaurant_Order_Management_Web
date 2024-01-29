package com.oaxaca.customer_service.controller;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.customer_service.model.Customer;
import com.oaxaca.customer_service.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
public class CustomerControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        mockMvc = standaloneSetup(customerController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setUsername("username");
        customer.setPassword("password");
        customer.setEmail("email");

        given(customerService.createCustomer(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post("/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testCreateCustomerFullDetails() throws Exception {
        Customer customer = new Customer();
        customer.setUsername("username");
        customer.setPassword("password");
        customer.setEmail("email");
        customer.setName("name");
        customer.setAddress("address");
        customer.setPhone("phone");
        customer.setCreditCard("creditCard");

        given(customerService.createCustomer(any(Customer.class))).willReturn(customer);

        mockMvc.perform(post("/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    public void testCreateCustomerInsufficientDetails() throws Exception {
        Customer customer = new Customer();
        // Not setting username, password, and email

        mockMvc.perform(post("/customer/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testFindCustomerById() throws Exception {
        Long id = 1L;
        Customer customer = new Customer();
        customer.setId(id);
        customer.setUsername("username");
        customer.setPassword("password");
        customer.setEmail("email");

        given(customerService.createCustomer(customer)).willReturn(customer);
        given(customerService.findCustomerById(id)).willReturn(customer);

        mockMvc.perform(get("/customer/find/id/" + id)) // Added a forward slash before id
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

    }

    @Test
    public void testFindCustomerByUsername() throws Exception {
        String username = "username";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword("password");
        customer.setEmail("email");

        given(customerService.createCustomer(customer)).willReturn(customer);
        given(customerService.findCustomerByUsername(username)).willReturn(customer);

        mockMvc.perform(get("/customer/find/username/" + username)) // Added a forward slash before username
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());

    }

    @Test
    public void testFindCustomerByEmail() throws Exception {
        String username = "username";
        String email  = "email";
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setPassword("password");
        customer.setEmail(email);

        given(customerService.createCustomer(customer)).willReturn(customer);
        given(customerService.findCustomerByEmail(email)).willReturn(customer);

        mockMvc.perform(get("/customer/find/email/" + email)) // Added a forward slash before username
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").exists());

    }

}