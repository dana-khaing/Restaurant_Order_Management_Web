package com.oaxaca.kitchen_staff_service.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.oaxaca.kitchen_staff_service.service.KitchenStaffService;
import com.oaxaca.kitchen_staff_service.model.KitchenStaff;

public class KitchenStaffControllerTest {

    @InjectMocks
    KitchenStaffController kitchenStaffController;

    @Mock
    KitchenStaffService kitchenStaffService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateKitchenStaff() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setFirstName("John");
        kitchenStaff.setLastName("Doe");
        kitchenStaff.setRole("Chef");

        when(kitchenStaffService.createKitchenStaff(any(KitchenStaff.class))).thenReturn(kitchenStaff);

        ResponseEntity<?> response = kitchenStaffController.createKitchenStaff(kitchenStaff);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Kitchen Staff created.", response.getBody());
    }

    @Test
    public void testLoginKitchenStaff() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setFirstName("John");
        kitchenStaff.setLastName("Doe");
        kitchenStaff.setRole("Chef");

        when(kitchenStaffService.findKitchenStaffByFirstNameAndLastName(kitchenStaff.getFirstName(), kitchenStaff.getLastName())).thenReturn(kitchenStaff);

        ResponseEntity<?> response = kitchenStaffController.loginKitchenStaff(kitchenStaff);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Kitchen Staff logged in.", response.getBody());
    }
}