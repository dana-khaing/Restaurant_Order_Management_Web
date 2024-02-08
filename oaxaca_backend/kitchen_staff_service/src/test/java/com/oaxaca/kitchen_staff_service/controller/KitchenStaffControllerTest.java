
package com.oaxaca.kitchen_staff_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.service.KitchenStaffService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class KitchenStaffControllerTest {

    @Test
    public void testCreateKitchenStaffSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        KitchenStaffService kitchenStaffService = mock(KitchenStaffService.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager, kitchenStaffService);
        KitchenStaff kitchenStaff = new KitchenStaff("user", "password", "firstName", "lastName", "chef");

        // Act
        ResponseEntity<?> response = controller.createKitchenStaff(kitchenStaff);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Kitchen Staff created.", response.getBody());
    }

    @Test
    public void testCreateKitchenStaffFail() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        KitchenStaffService kitchenStaffService = mock(KitchenStaffService.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager, kitchenStaffService);
        KitchenStaff kitchenStaff = new KitchenStaff();

        // Act
        ResponseEntity<?> response = controller.createKitchenStaff(kitchenStaff);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Kitchen Staff creation failed", response.getBody());
    }

    @Test
    public void testLoginKitchenStaffSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        KitchenStaffService kitchenStaffService = mock(KitchenStaffService.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager, kitchenStaffService);
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
        KitchenStaffService kitchenStaffService = mock(KitchenStaffService.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager, kitchenStaffService);
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