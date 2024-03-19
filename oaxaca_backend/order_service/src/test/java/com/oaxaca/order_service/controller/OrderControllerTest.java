
package com.oaxaca.order_service.controller;

import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @Mock

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void testCancelOrder() throws Exception {
        doNothing().when(orderService).cancelOrder(anyLong());

        mockMvc.perform(put("/orders/cancel/{orderId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindOrder() throws Exception {
        when(orderService.getOrderById(anyLong())).thenReturn(new Order());

        mockMvc.perform(get("/orders/findOrder/{orderId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCompleteOrder() throws Exception {
        doNothing().when(orderService).completeOrder(anyLong());

        mockMvc.perform(put("/orders/completeOrder/{orderId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCancelOrderWithNullId() throws Exception {
        mockMvc.perform(post("/orders/cancel/{orderId}", (Object) null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testFindOrderWithNullId() throws Exception {
        mockMvc.perform(post("/orders/findOrder/{orderId}", (Object) null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCompleteOrderWithNullId() throws Exception {
        mockMvc.perform(post("/orders/completeOrder/{orderId}", (Object) null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}