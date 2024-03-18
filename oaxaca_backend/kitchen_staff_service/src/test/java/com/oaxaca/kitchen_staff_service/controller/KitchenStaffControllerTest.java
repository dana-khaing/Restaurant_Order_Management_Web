
package com.oaxaca.kitchen_staff_service.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Collections;

public class KitchenStaffControllerTest {

    @Test
    public void testLoginKitchenStaffSuccess() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        RememberMeServices rememberMeServices = mock(RememberMeServices.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager, rememberMeServices);
        Authentication authentication = mock(Authentication.class);
        KitchenStaff kitchenStaff = new KitchenStaff("user", "password", "firstName", "lastName", "chef");
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        // Act
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        ResponseEntity<?> response = controller.loginKitchenStaff(kitchenStaff, request, res);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.singletonMap("message", "Customer Logged in Successfully"), response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
        assertEquals(authentication, SecurityContextHolder.getContext().getAuthentication());
    }

    @Test
    public void testLoginKitchenStaffFail() {
        // Arrange
        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        RememberMeServices rememberMeServices = mock(RememberMeServices.class);
        KitchenStaffController controller = new KitchenStaffController(authenticationManager, rememberMeServices);
        KitchenStaff kitchenStaff = new KitchenStaff("user", "password", "firstName", "lastName", "chef");
        when(authenticationManager.authenticate(any())).thenReturn(null);

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse res = mock(HttpServletResponse.class);
        // Act
        ResponseEntity<?> response = controller.loginKitchenStaff(kitchenStaff, request, res);

        // Assert
        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(Collections.singletonMap("message", "Customer Login Failed"), response.getBody());
        verify(authenticationManager, times(1)).authenticate(any());
    }
}