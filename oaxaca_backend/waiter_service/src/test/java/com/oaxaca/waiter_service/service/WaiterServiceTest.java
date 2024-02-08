// FILEPATH: /Users/botirkhaltaev/Desktop/TeamProject29/oaxaca_backend/waiter_service/src/test/java/com/oaxaca/waiter_service/service/WaiterServiceTest.java

package com.oaxaca.waiter_service.service;

import com.oaxaca.waiter_service.exception.WaiterCreationFailedException;
import com.oaxaca.waiter_service.model.Waiter;
import com.oaxaca.waiter_service.repository.WaiterRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

public class WaiterServiceTest {

    @Test
    public void testCreateWaiterSuccess() {
        // Arrange
        WaiterRepository waiterRepository = Mockito.mock(WaiterRepository.class);
        WaiterService waiterService = new WaiterService(waiterRepository);
        Waiter waiter = new Waiter("name", "lastname", "username", "password", "email", "restaurantName", "managerName",
                "10 Street", LocalDate.now());
        when(waiterRepository.save(waiter)).thenReturn(waiter);

        // Act
        Waiter result = waiterService.createWaiter(waiter);

        // Assert
        assertEquals(waiter, result);
    }

    @Test
    public void testCreateWaiterNull() {
        // Arrange
        WaiterRepository waiterRepository = Mockito.mock(WaiterRepository.class);
        WaiterService waiterService = new WaiterService(waiterRepository);

        // Act & Assert
        assertThrows(WaiterCreationFailedException.class, () -> waiterService.createWaiter(null));
    }

    @Test
    public void testCreateWaiterMissingFields() {
        // Arrange
        WaiterRepository waiterRepository = Mockito.mock(WaiterRepository.class);
        WaiterService waiterService = new WaiterService(waiterRepository);
        Waiter waiter = new Waiter();

        // Act & Assert
        assertThrows(WaiterCreationFailedException.class, () -> waiterService.createWaiter(waiter));
    }
}