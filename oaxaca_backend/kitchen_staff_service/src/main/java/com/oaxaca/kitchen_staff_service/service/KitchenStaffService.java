package com.oaxaca.kitchen_staff_service.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    public KitchenStaffService(KitchenStaffRepository kitchenStaffRepository) {
        this.kitchenStaffRepository = kitchenStaffRepository;
    }

    public KitchenStaff createKitchenStaff(KitchenStaff kitchenStaff) {
        if (kitchenStaff == null || kitchenStaff.getFirstName() == null || kitchenStaff.getFirstName().isEmpty()
                || kitchenStaff.getLastName() == null || kitchenStaff.getLastName().isEmpty()
                || kitchenStaff.getRole() == null || kitchenStaff.getRole().isEmpty()) {
            throw new KitchenStaffCreationFailedException("Kitchen Staff creation failed");
        }

        return kitchenStaffRepository.save(kitchenStaff);
    }

    public KitchenStaff findKitchenStaffById(Long id) {
        return kitchenStaffRepository.findById(id).orElse(null);
    }

    public KitchenStaff findKitchenStaffByFirstName(String firstName) {
        return kitchenStaffRepository.findByFirstName(firstName).orElse(null);
    }

    public KitchenStaff findKitchenStaffByLastName(String lastName) {
        return kitchenStaffRepository.findByLastName(lastName).orElse(null);
    }



    public KitchenStaff findKitchenStaffByFirstNameAndLastName(String firstName, String lastName) {
        return kitchenStaffRepository.findByFirstNameAndLastName(firstName, lastName).orElse(null);
    }

}
