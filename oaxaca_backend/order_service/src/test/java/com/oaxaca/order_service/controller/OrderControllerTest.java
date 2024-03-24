
package com.oaxaca.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oaxaca.order_service.dto.CartDto;
import com.oaxaca.order_service.dto.CartItemDto;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.service.OrderPaymentService;
import com.oaxaca.order_service.service.OrderService;
import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;

import jakarta.servlet.http.Cookie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private OrderPaymentService orderPaymentService;

    @Mock
    private RestTemplate restTemplate;

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

        // Arrange
        CartItemDto cartItemDto = new CartItemDto("Pizza", "Delicious pizza", 200, 15.5f, new ArrayList<>(), 1, 3, "image", 1L);
        CartItemDto cartItemDto2 = new CartItemDto("Soda", "Refreshing soda", 100, 2.5f, new ArrayList<>(), 2, 2, "image", 1L);
        ArrayList<CartItemDto> items = new ArrayList<>();
        items.add(cartItemDto);
        items.add(cartItemDto2);
        CartDto cartDto = new CartDto("Customer", items);
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto("Customer", 1, cartDto, OrderType.DINE_IN.name());

        mockMvc.perform(post("/orders/placeOrder")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(orderDetailsDto))
                .cookie(new Cookie("JSESSIONID", "1"))) // Add the cookie here
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

}