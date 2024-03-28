package com.oaxaca.kitchen_staff_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.oaxaca.kitchen_staff_service.exception.KitchenStaffCreationFailedException;
import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.repository.KitchenStaffRepository;

@Service
public class KitchenStaffService {

    @Autowired
    private KitchenStaffRepository kitchenStaffRepository;

    public KitchenStaffService() {

    }

    /**
     * Constructor for KitchenStaffService.
     *
     * @param kitchenStaffRepository The kitchen staff repository.
     */
    public KitchenStaffService(KitchenStaffRepository kitchenStaffRepository) {
        this.kitchenStaffRepository = kitchenStaffRepository;
    }

    /**
     * Creates a new kitchen staff member.
     *
     * @param kitchenStaff The kitchen staff member to be created.
     * @return The created kitchen staff member.
     * @throws KitchenStaffCreationFailedException If kitchen staff creation fails.
     */

    public KitchenStaff createKitchenStaff(KitchenStaff kitchenStaff) {
        if (kitchenStaff == null || kitchenStaff.getFirstName() == null || kitchenStaff.getFirstName().isEmpty()
                || kitchenStaff.getLastName() == null || kitchenStaff.getLastName().isEmpty()
                || kitchenStaff.getRole() == null || kitchenStaff.getRole().isEmpty()) {
            throw new KitchenStaffCreationFailedException("Kitchen Staff creation failed");
        }

        return kitchenStaffRepository.save(kitchenStaff);
    }
    /**
     * Finds a kitchen staff member by ID.
     *
     * @param id The ID of the kitchen staff member.
     * @return The found kitchen staff member, or null if not found.
     */
    public KitchenStaff findKitchenStaffById(@NonNull Long id) {
        return kitchenStaffRepository.findById(id).orElse(null);
    }
    /**
     * Finds a kitchen staff member by first name.
     *
     * @param firstName The first name of the kitchen staff member.
     * @return The found kitchen staff member, or null if not found.
     */
    public KitchenStaff findKitchenStaffByFirstName(String firstName) {
        return kitchenStaffRepository.findByFirstName(firstName).orElse(null);
    }
    /**
     * Finds a kitchen staff member by last name.
     *
     * @param lastName The last name of the kitchen staff member.
     * @return The found kitchen staff member, or null if not found.
     */
    public KitchenStaff findKitchenStaffByLastName(String lastName) {
        return kitchenStaffRepository.findByLastName(lastName).orElse(null);
    }


    /**
     * Finds a kitchen staff member by first and last name.
     *
     * @param firstName The first name of the kitchen staff member.
     * @param lastName  The last name of the kitchen staff member.
     * @return The found kitchen staff member, or null if not found.
     */
    public KitchenStaff findKitchenStaffByFirstNameAndLastName(String firstName, String lastName) {
        return kitchenStaffRepository.findByFirstNameAndLastName(firstName, lastName).orElse(null);
    }

}
