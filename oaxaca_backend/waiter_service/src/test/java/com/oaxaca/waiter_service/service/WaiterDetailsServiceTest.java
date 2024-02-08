package com.oaxaca.waiter_service.service;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.oaxaca.waiter_service.model.Waiter;
import com.oaxaca.waiter_service.repository.WaiterRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class WaiterDetailsServiceTest {

    @Test
    public void testLoadUserByUsernameSuccess() {
        // Arrange
        WaiterRepository waiterRepository = mock(WaiterRepository.class);
        WaiterDetailsService waiterDetailsService = new WaiterDetailsService(waiterRepository);
        Waiter waiter = new Waiter("username", "password", "Bob", "Jenkins", "email@email.com", "Jad", "Oaxaca",
                "10 Street", LocalDate.now());
        when(waiterRepository.findByUsername("username")).thenReturn(Optional.of(waiter));

        // Act
        UserDetails userDetails = waiterDetailsService.loadUserByUsername("username");

        // Assert
        assertNotNull(userDetails);
        assertEquals(waiter.getUsername(), userDetails.getUsername());
        verify(waiterRepository, times(1)).findByUsername("username");
    }

    @Test
    public void testLoadUserByUsernameFail() {
        // Arrange
        WaiterRepository waiterRepository = mock(WaiterRepository.class);
        WaiterDetailsService waiterDetailsService = new WaiterDetailsService(waiterRepository);
        when(waiterRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> waiterDetailsService.loadUserByUsername("username"));
        verify(waiterRepository, times(1)).findByUsername("username");
    }
}
