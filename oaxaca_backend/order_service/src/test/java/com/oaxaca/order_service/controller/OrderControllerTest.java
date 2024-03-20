
package com.oaxaca.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.service.OrderPaymentService;
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

import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Mock
    private OrderPaymentService orderPaymentService;

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

    @Test
    public void testPlaceOrder() throws Exception {
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();

        mockMvc.perform(post("/orders/placeOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDetailsDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testSendOrderToKitchen() throws Exception {
        doNothing().when(orderService).sendOrderToKitchen(anyLong());

        mockMvc.perform(put("/orders/sendOrderToKitchen/{orderId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeliverOrder() throws Exception {
        doNothing().when(orderService).deliverOrder(anyLong());

        mockMvc.perform(put("/orders/deliverOrder/{orderId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testSendOrderToKitchenWithNullId() throws Exception {
        mockMvc.perform(post("/orders/sendOrderToKitchen/{orderId}", (Object) null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testDeliverOrderWithNullId() throws Exception {
        mockMvc.perform(post("/orders/deliverOrder/{orderId}", (Object) null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testPayForOrder() throws Exception {
        when(orderPaymentService.payOrder(anyLong())).thenReturn(true);

        mockMvc.perform(put("/orders/payForOrder/{orderId}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testPayForOrderWithNullId() throws Exception {
        mockMvc.perform(post("/orders/payForOrder/{orderId}", (Object) null)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testOrderControllerConstructor() {
        OrderController controller = new OrderController(orderService, orderPaymentService);
        assertNotNull(controller);
    }

}