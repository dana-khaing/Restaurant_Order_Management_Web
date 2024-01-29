package com.oaxaca.waiter_service.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.oaxaca.waiter_service.model.Waiter;
import com.oaxaca.waiter_service.service.WaiterService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class WaiterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private WaiterService waiterService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterWaiter() throws Exception {
        String requestBody = "{\"name\":\"test\", \"lastname\":\"test\", \"username\":\"test\", \"password\":\"test\", \"email\":\"test@test.com\", \"restaurantName\":\"test\", \"managerName\":\"test\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/waiter/register")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Waiter created successfully"));
    }

    @Test
    public void testLoginWaiter() throws Exception {
        String requestBody = "{\"username\":\"test\", \"password\":\"test\"}";

        Waiter mockWaiter = new Waiter("test", "test", "test", "test", "test@test.com", "test", "test");
        Mockito.when(waiterService.findWaiterByUsername("test")).thenReturn(mockWaiter);

        mockMvc.perform(MockMvcRequestBuilders.post("/waiter/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }
}
