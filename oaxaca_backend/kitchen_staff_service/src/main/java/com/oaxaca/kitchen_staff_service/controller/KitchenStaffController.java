package com.oaxaca.kitchen_staff_service.controller;

import java.util.HashMap;
import java.util.Map;

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

@RestController
@RequestMapping("/kitchen_staff")
public class KitchenStaffController {

    private final AuthenticationManager authenticationManager;

    public KitchenStaffController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginKitchenStaff(@RequestBody KitchenStaff kitchenStaff) {
        if (kitchenStaff == null || kitchenStaff.getFirstName() == null || kitchenStaff.getFirstName().isEmpty()
                || kitchenStaff.getLastName() == null || kitchenStaff.getLastName().isEmpty()
                || kitchenStaff.getRole() == null || kitchenStaff.getRole().isEmpty()) {

            Map<String, String> response = new HashMap<>();
            response.put("message", "Kitchen Staff login failed. Missing fields.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(kitchenStaff.getUsername(), kitchenStaff.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        if (authenticationResponse == null) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Customer Login Failed");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer Logged in Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/me")
    public ResponseEntity<?> getKitchenStaff() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        KitchenStaffDetails kitchenStaffDetails = (KitchenStaffDetails) authentication.getPrincipal();

        Map<String, Object> response = new HashMap<>();
        response.put("KitchenStaffDetails", kitchenStaffDetails);
        return new ResponseEntity<>(kitchenStaffDetails, HttpStatus.OK);
    }

}
