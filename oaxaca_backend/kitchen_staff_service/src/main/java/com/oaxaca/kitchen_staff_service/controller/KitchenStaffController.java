package com.oaxaca.kitchen_staff_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.model.KitchenStaffDetails;
import com.oaxaca.kitchen_staff_service.service.KitchenStaffService;

@RestController
@RequestMapping("/kitchen_staff")
public class KitchenStaffController {

    private final KitchenStaffService kitchenStaffService;

    private final AuthenticationManager authenticationManager;

    public KitchenStaffController(AuthenticationManager authenticationManager, KitchenStaffService kitchenStaffService) {
        this.authenticationManager = authenticationManager;
        this.kitchenStaffService = kitchenStaffService;
    }

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
            return new ResponseEntity<>("Kitchen Staff login failed. Wrong username or password or missing fields.",
                    HttpStatus.BAD_REQUEST);
        }

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(kitchenStaff.getUsername(), kitchenStaff.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        if (authenticationResponse == null) {
            return new ResponseEntity<>("Customer Login Failed", HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

        return new ResponseEntity<>("Customer Logged in Successfully", HttpStatus.OK);

    }

    @GetMapping("/me")
    public ResponseEntity<?> getKitchenStaff() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KitchenStaffDetails kitchenStaffDetails = (KitchenStaffDetails) authentication.getPrincipal();
        return new ResponseEntity<>(kitchenStaffDetails, HttpStatus.OK);
    }

}
