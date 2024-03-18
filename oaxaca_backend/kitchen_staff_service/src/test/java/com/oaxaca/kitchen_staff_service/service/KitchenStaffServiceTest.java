// FILEPATH: /Users/botirkhaltaev/Desktop/TeamProject29/oaxaca_backend/kitchen_staff_service/src/test/java/com/oaxaca/kitchen_staff_service/service/KitchenStaffServiceTest.java

package com.oaxaca.kitchen_staff_service.service;

import com.oaxaca.kitchen_staff_service.exception.KitchenStaffCreationFailedException;
import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.repository.KitchenStaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class KitchenStaffServiceTest {

    private KitchenStaffRepository kitchenStaffRepository;
    private KitchenStaffService kitchenStaffService;

    @BeforeEach
    public void setup() {
        kitchenStaffRepository = Mockito.mock(KitchenStaffRepository.class);
        kitchenStaffService = new KitchenStaffService(kitchenStaffRepository);
    }

    @Test
    public void testCreateKitchenStaffSuccess() {
        KitchenStaff kitchenStaff = new KitchenStaff("j123", "password", "John", "Doe", "Chef");
        when(kitchenStaffRepository.save(any(KitchenStaff.class))).thenReturn(kitchenStaff);

        KitchenStaff createdKitchenStaff = kitchenStaffService.createKitchenStaff(kitchenStaff);

        assertEquals(kitchenStaff, createdKitchenStaff);
    }

    @Test
    public void testCreateKitchenStaffFail() {
        KitchenStaff kitchenStaff = new KitchenStaff();

        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(kitchenStaff));
    }

    @Test
    public void testFindKitchenStaffById() {
        KitchenStaff kitchenStaff = new KitchenStaff("j123", "password", "John", "Doe", "Chef");
        when(kitchenStaffRepository.findById(1L)).thenReturn(Optional.of(kitchenStaff));

        KitchenStaff foundKitchenStaff = kitchenStaffService.findKitchenStaffById(1L);

        assertEquals(kitchenStaff, foundKitchenStaff);
    }

    @Test
    public void testFindKitchenStaffByFirstName() {
        KitchenStaff kitchenStaff = new KitchenStaff("j123", "password", "John", "Doe", "Chef");
        when(kitchenStaffRepository.findByFirstName("John")).thenReturn(Optional.of(kitchenStaff));

        KitchenStaff foundKitchenStaff = kitchenStaffService.findKitchenStaffByFirstName("John");

        assertEquals(kitchenStaff, foundKitchenStaff);
    }

    @Test
    public void testFindKitchenStaffByLastName() {
        KitchenStaff kitchenStaff = new KitchenStaff("j123", "password", "John", "Doe", "Chef");
        when(kitchenStaffRepository.findByLastName("Doe")).thenReturn(Optional.of(kitchenStaff));

        KitchenStaff foundKitchenStaff = kitchenStaffService.findKitchenStaffByLastName("Doe");

        assertEquals(kitchenStaff, foundKitchenStaff);
    }

  

    @Test
    public void testFindKitchenStaffByFirstNameAndLastName() {
        KitchenStaff kitchenStaff = new KitchenStaff("j123", "password", "John", "Doe", "Chef");
        when(kitchenStaffRepository.findByFirstNameAndLastName("John", "Doe")).thenReturn(Optional.of(kitchenStaff));
    }

}