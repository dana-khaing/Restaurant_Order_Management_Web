
package com.oaxaca.kitchen_staff_service.service;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.repository.KitchenStaffRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class KitchenStaffDetailsServiceTest {

    @Test
    public void loadUserByUsername_UserExists_ReturnsUserDetails() {
        // Arrange
        KitchenStaffRepository mockRepository = mock(KitchenStaffRepository.class);
        KitchenStaffDetailsService service = new KitchenStaffDetailsService(mockRepository);
        KitchenStaff mockUser = new KitchenStaff("username", "password", "firstName", "lastName", "chef");
        when(mockRepository.findByUsername("username")).thenReturn(Optional.of(mockUser));

        // Act
        UserDetails result = service.loadUserByUsername("username");

        // Assert
        assertEquals(mockUser.getUsername(), result.getUsername());
        assertEquals(mockUser.getPassword(), result.getPassword());
    }

    @Test
    public void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        // Arrange
        KitchenStaffRepository mockRepository = mock(KitchenStaffRepository.class);
        KitchenStaffDetailsService service = new KitchenStaffDetailsService(mockRepository);
        when(mockRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("username"));
    }
}