
package com.oaxaca.kitchen_staff_service.service;

import com.oaxaca.kitchen_staff_service.exception.KitchenStaffCreationFailedException;
import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.repository.KitchenStaffRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KitchenStaffServiceTest {

    @Mock
    KitchenStaffRepository kitchenStaffRepository;

    @InjectMocks
    KitchenStaffService kitchenStaffService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createKitchenStaff_NullKitchenStaff_ThrowsException() {
        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(null));
    }

    @Test
    void createKitchenStaff_NullFirstName_ThrowsException() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setFirstName(null);
        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(kitchenStaff));
    }

    @Test
    void createKitchenStaff_EmptyFirstName_ThrowsException() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setFirstName("");
        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(kitchenStaff));
    }

    @Test
    void createKitchenStaff_NullLastName_ThrowsException() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setLastName(null);
        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(kitchenStaff));
    }


    void createKitchenStaff_EmptyLastName_ThrowsException() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setLastName("");
        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(kitchenStaff));
    }

    @Test
    void createKitchenStaff_NullRole_ThrowsException() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setRole(null);
        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(kitchenStaff));
    }

    @Test
    void createKitchenStaff_EmptyRole_ThrowsException() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setRole("");
        assertThrows(KitchenStaffCreationFailedException.class, () -> kitchenStaffService.createKitchenStaff(kitchenStaff));
    }


    @Test
    void createKitchenStaff_ValidKitchenStaff_ReturnsKitchenStaff() {
        KitchenStaff kitchenStaff = new KitchenStaff();
        kitchenStaff.setFirstName("John");
        kitchenStaff.setLastName("Doe");
        kitchenStaff.setRole("Chef");

        when(kitchenStaffRepository.save(any(KitchenStaff.class))).thenReturn(kitchenStaff);

        KitchenStaff createdKitchenStaff = kitchenStaffService.createKitchenStaff(kitchenStaff);

        assertEquals(kitchenStaff, createdKitchenStaff);
        verify(kitchenStaffRepository, times(1)).save(kitchenStaff);
    }
}