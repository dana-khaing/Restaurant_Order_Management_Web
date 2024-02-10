package com.oaxaca.kitchen_staff_service.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;
import com.oaxaca.kitchen_staff_service.model.KitchenStaffDetails;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/kitchen_staff")
public class KitchenStaffController {

    private final AuthenticationManager authenticationManager;
    private final RememberMeServices rememberMeServices;


    public KitchenStaffController(AuthenticationManager authenticationManager, RememberMeServices rememberMeServices) {
        this.authenticationManager = authenticationManager;
        this.rememberMeServices = rememberMeServices;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginKitchenStaff(@RequestBody KitchenStaff kitchenStaff, HttpServletRequest request,
            HttpServletResponse res) {
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

        this.rememberMeServices.loginSuccess(request, res, authenticationResponse);

        
        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer Logged in Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @GetMapping("/validate-remember-me")
    public ResponseEntity<?> validateRememberMe(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = rememberMeServices.autoLogin(request, response);

        if (authentication != null && authentication instanceof RememberMeAuthenticationToken) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            Map<String, String> result = new HashMap<>();
            result.put("message", "User authenticated with remember-me token");
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing remember-me token");
        }
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
