package com.oaxaca.order_service.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.oaxaca.order_service.event.OrderPaidEvent;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class OrderPaymentServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private OrderPaymentService orderPaymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void payOrder_WithNullOrderId_ReturnsFalse() {
        boolean result = orderPaymentService.payOrder(null);
        assertFalse(result);
    }

    @Test
    void payOrder_OrderDoesNotExist_ReturnsFalse() {
        when(orderRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        boolean result = orderPaymentService.payOrder(1L);
        assertFalse(result);
    }

    @Test
    void payOrder_SuccessfulPayment_ReturnsTrueAndPublishesEvent() throws StripeException {
        Order mockOrder = new Order();
        mockOrder.setId(1L);
        mockOrder.setTotal(1000); // Assuming total is in cents
        mockOrder.setOrderStatus(OrderStatus.PENDING);
        when(orderRepository.findById(1L)).thenReturn(Optional.of(mockOrder));

        try (MockedStatic<Charge> mockedCharge = mockStatic(Charge.class)) {
            Charge mockChargeInstance = mock(Charge.class);
            when(mockChargeInstance.getPaid()).thenReturn(true);
            mockedCharge.when(() -> Charge.create(any())).thenReturn(mockChargeInstance);

            boolean result = orderPaymentService.payOrder(1L);

            assertTrue(result);
            verify(orderRepository, times(1)).save(mockOrder);
            verify(applicationEventPublisher, times(1)).publishEvent(any(OrderPaidEvent.class));
        }
    }
}
