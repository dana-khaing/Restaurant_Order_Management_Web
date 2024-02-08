
package com.oaxaca.kitchen_staff_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KitchenStaffControllerTest {


    @Test
    public void testLoginKitchenStaffSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager);
        Authentication authentication = mock(Authentication.class);
        KitchenStaff kitchenStaff = new KitchenStaff("user", "password", "firstName", "lastName", "chef");
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Act
        ResponseEntity<?> response = controller.loginKitchenStaff(kitchenStaff);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Customer Logged in Successfully", response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testLoginKitchenStaffFail() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager);
        KitchenStaff kitchenStaff = new KitchenStaff("user", "password", "firstName", "lastName", "chef");
        when(authenticationManager.authenticate(any())).thenReturn(null);

        // Act
        ResponseEntity<?> response = controller.loginKitchenStaff(kitchenStaff);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Customer Login Failed", response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
    }
}