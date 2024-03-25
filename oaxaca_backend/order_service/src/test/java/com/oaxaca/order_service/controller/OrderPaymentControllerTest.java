// FILEPATH: /Users/botirkhaltaev/Desktop/TeamProject29/oaxaca_backend/order_service/src/test/java/com/oaxaca/order_service/controller/OrderPaymentControllerTest.java

package com.oaxaca.order_service.controller;

import com.oaxaca.order_service.service.OrderPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class OrderPaymentControllerTest {

    @Mock
    private OrderPaymentService orderPaymentService;

    @InjectMocks
    private OrderPaymentController orderPaymentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void payOrder_NullOrderId_ReturnsBadRequest() {
        ResponseEntity<Map<String, Object>> response = orderPaymentController.payOrder(null);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Order ID cannot be null", response.getBody().get("error"));
    }

    @Test
    void payOrder_PaymentSuccessful_ReturnsOk() {
        when(orderPaymentService.payOrder(1L)).thenReturn(true);
        ResponseEntity<Map<String, Object>> response = orderPaymentController.payOrder(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Payment successful", response.getBody().get("message"));
    }

    @Test
    void payOrder_PaymentFailed_ReturnsBadRequest() {
        when(orderPaymentService.payOrder(1L)).thenReturn(false);
        ResponseEntity<Map<String, Object>> response = orderPaymentController.payOrder(1L);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Payment failed", response.getBody().get("error"));
    }
}
