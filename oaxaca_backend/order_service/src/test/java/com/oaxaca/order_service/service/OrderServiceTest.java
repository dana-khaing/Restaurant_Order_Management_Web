package com.oaxaca.order_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.oaxaca.order_service.dto.CartDto;
import com.oaxaca.order_service.dto.CartItemDto;
import com.oaxaca.order_service.dto.OrderDetailsDto;
import com.oaxaca.order_service.model.Order;
import com.oaxaca.order_service.model.OrderItem;
import com.oaxaca.order_service.repository.OrderRepository;
import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Test
    void testPlaceOrder() {

        // Arrange
        CartItemDto cartItemDto = new CartItemDto("test", "test", 0, 5f, null, 0, 3);
        CartItemDto cartItemDto2 = new CartItemDto("test", "test", 0, 5f, null, 0, 1);
        ArrayList<CartItemDto> items = new ArrayList<>();
        items.add(cartItemDto);
        items.add(cartItemDto2);
        CartDto cartDto = new CartDto("test", items);
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto("test", 1, cartDto, "DINE_IN");
        ArrayList<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem());
        orderItems.add(new OrderItem());
        Order expectedOrder = new Order(OrderType.DINE_IN, OrderStatus.PENDING, "test", 1, orderItems);
        // Act

        when(orderRepository.save(any(Order.class))).thenReturn(new Order());

        Order result = orderService.placeOrder(orderDetailsDto);

        // Assert

        assertEquals(expectedOrder, result);

    }

}
