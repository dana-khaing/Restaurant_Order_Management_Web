package com.oaxaca.kitchen_staff_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.service.KitchenStaffService;

@RestController
@RequestMapping("/kitchen_staff")
public class KitchenStaffController {

    @Autowired
    private KitchenStaffService kitchenStaffService;

    @PostMapping("/register")
    public ResponseEntity<?> createKitchenStaff(@RequestBody KitchenStaff kitchenStaff) {
        if (kitchenStaff == null || kitchenStaff.getFirstName() == null || kitchenStaff.getFirstName().isEmpty()
                || kitchenStaff.getLastName() == null || kitchenStaff.getLastName().isEmpty()
                || kitchenStaff.getRole() == null || kitchenStaff.getRole().isEmpty()) {
            return new ResponseEntity<>("Kitchen Staff creation failed", HttpStatus.BAD_REQUEST);
        }

        kitchenStaffService.createKitchenStaff(kitchenStaff);
        return new ResponseEntity<>("Kitchen Staff created.", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginKitchenStaff(@RequestBody KitchenStaff kitchenStaff) {
        if (kitchenStaff == null || kitchenStaff.getFirstName() == null || kitchenStaff.getFirstName().isEmpty()
                || kitchenStaff.getLastName() == null || kitchenStaff.getLastName().isEmpty()
                || kitchenStaff.getRole() == null || kitchenStaff.getRole().isEmpty()) {
            return new ResponseEntity<>("Kitchen Staff login failed. Wrong username or password",
                    HttpStatus.BAD_REQUEST);
        }

        KitchenStaff foundKitchenStaff = kitchenStaffService
                .findKitchenStaffByFirstNameAndLastName(kitchenStaff.getFirstName(), kitchenStaff.getLastName());
        if (foundKitchenStaff == null) {
            return new ResponseEntity<>("Kitchen Staff login failed", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Kitchen Staff logged in.", HttpStatus.OK);
    }

    

}
