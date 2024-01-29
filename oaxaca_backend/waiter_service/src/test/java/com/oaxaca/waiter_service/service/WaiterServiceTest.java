package com.oaxaca.waiter_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.oaxaca.waiter_service.exception.WaiterCreationFailedException;
import com.oaxaca.waiter_service.model.Waiter;
import com.oaxaca.waiter_service.repository.WaiterRepository;

public class WaiterServiceTest {

    @Mock
    private WaiterRepository waiterRepository;

    @InjectMocks
    private WaiterService waiterService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateWaiter() throws Exception {
        Waiter waiter = new Waiter();
        waiter.setUsername("janedoe");
        waiter.setPassword("ilovepizza");
        waiter.setEmail("email");
        waiter.setName("Jane");
        waiter.setLastname("Doe");
        waiter.setManagerId(123L);
        waiter.setManager("manager");
        waiter.setRestaurantId(123L);
        waiter.setRestaurantName("oaxaca");

        when(waiterRepository.save(waiter)).thenReturn(waiter);

        Waiter createdWaiter = waiterService.createWaiter(waiter);

        assertEquals(waiter, createdWaiter);
    }

    @Test
    public void testNullWaiterCreate() {
        Waiter waiter = null;

        assertThrows(WaiterCreationFailedException.class, () -> {
            waiterService.createWaiter(waiter);
        });
    }

    @Test
    public void testWaiterCreateInsufficientInfo(){
        Waiter waiter = new Waiter(); // Missing Name field
        waiter.setUsername("janedoe");
        waiter.setPassword("ilovepizza");
        waiter.setEmail("email");
        waiter.setLastname("Doe");
        waiter.setManagerId(123L);
        waiter.setManager("manager");
        waiter.setRestaurantId(123L);
        waiter.setRestaurantName("oaxaca");

        assertThrows(WaiterCreationFailedException.class, () -> {
            waiterService.createWaiter(waiter);
        });
    }
}