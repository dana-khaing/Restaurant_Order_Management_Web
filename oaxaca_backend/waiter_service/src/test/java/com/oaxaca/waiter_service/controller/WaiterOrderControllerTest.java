package com.oaxaca.waiter_service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.waiter_service.model.Order;
import com.oaxaca.waiter_service.service.WaiterOrderService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class WaiterOrderControllerTest {

    private MockMvc mockMvc;

    @Mock
    private WaiterOrderService waiterOrderService;
    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        WaiterOrderController controller = new WaiterOrderController(waiterOrderService, restTemplate);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testConfirmOrder() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(Order.class))).thenReturn(new Order());

        mockMvc.perform(post("/waiter/order/confirm/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Order confirmed\"}"));

        verify(waiterOrderService, times(1)).saveOrder(any(Order.class));
        verify(waiterOrderService, times(1)).sendOrderToKitchen(anyLong());
    }

    @Test
    public void testCancelOrder() throws Exception {
        mockMvc.perform(post("/waiter/order/cancel/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Order cancelled\"}"));

        verify(waiterOrderService, times(1)).cancelOrder(anyLong());
    }

    @Test
    public void testCompleteOrder() throws Exception {
        mockMvc.perform(post("/waiter/order/complete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Order completed\"}"));

        verify(waiterOrderService, times(1)).completeOrder(anyLong());
    }

    @Test
    public void testConfirmOrderNotFound() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(Order.class))).thenReturn(null);

        mockMvc.perform(post("/waiter/order/confirm/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"message\":\"Order cannot be found\"}"));

        verify(waiterOrderService, times(0)).saveOrder(any(Order.class));
        verify(waiterOrderService, times(0)).sendOrderToKitchen(anyLong());
    }

    @Test
    public void testConfirmOrderSuccess() throws Exception {
        when(restTemplate.getForObject(anyString(), eq(Order.class))).thenReturn(new Order());

        mockMvc.perform(post("/waiter/order/confirm/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Order confirmed\"}"));

        verify(waiterOrderService, times(1)).saveOrder(any(Order.class));
        verify(waiterOrderService, times(1)).sendOrderToKitchen(anyLong());
    }

    @Test
    public void testCancelOrderSuccess() throws Exception {
        mockMvc.perform(post("/waiter/order/cancel/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Order cancelled\"}"));

        verify(waiterOrderService, times(1)).cancelOrder(anyLong());
    }

    @Test
    public void testCompleteOrderSuccess() throws Exception {
        mockMvc.perform(post("/waiter/order/complete/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Order completed\"}"));

        verify(waiterOrderService, times(1)).completeOrder(anyLong());
    }
 

}
