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

import java.sql.Date;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

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
        String requestBody = "{"
                + "\"name\":\"test\","
                + "\"lastname\":\"test\","
                + "\"username\":\"test\","
                + "\"password\":\"test\","
                + "\"email\":\"test@test.com\","
                + "\"restaurantName\":\"test\","
                + "\"managerName\":\"test\","
                + "\"restaurantAddress\":\"test\","
                + "\"dateOfBirth\":\"2024-01-01\""
                + "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/waiter/register")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Waiter created successfully"));
    }

    @Test
    public void testLoginWaiter() throws Exception {
        String requestBody = "{"
                + "\"username\":\"test\","
                + "\"password\":\"test\","
                + "\"firstName\":\"test\","
                + "\"lastName\":\"test\","
                + "\"dateOfBirth\":\"1990-01-01\""
                + "}";
        LocalDate localDate = LocalDate.of(2022, Month.JANUARY, 1);
        java.util.Date utilDate = java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        Waiter mockWaiter = new Waiter("test", "test", "test", "test", "test@test.com", "test", "test", "test", date);
        Mockito.when(waiterService.findWaiterByUsername("test")).thenReturn(mockWaiter);

        mockMvc.perform(MockMvcRequestBuilders.post("/waiter/login")
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }
}
