package com.oaxaca.waiter_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.waiter_service.service.WaiterService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.oaxaca.waiter_service.model.*;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RememberMeServices rememberMeServices;

    @Operation(summary = "Register a new waiter", description = "Registers a new waiter")
    @ApiResponse(responseCode = "200", description = "Waiter created successfully")
    @ApiResponse(responseCode = "400", description = "Waiter creation failed: missing required fields")
    @PostMapping("/register")
    public ResponseEntity<?> createWaiter(@RequestBody Waiter waiter) {
        if (waiter == null) {
            return ResponseEntity.badRequest().body("Waiter creation failed: missing required fields");
        }

        Map<String, String> response = new HashMap<>();

        if (waiter.getFirstName() == null || waiter.getFirstName().isEmpty() || waiter.getLastName() == null
                || waiter.getLastName().isEmpty() || waiter.getUsername() == null || waiter.getUsername().isEmpty()
                || waiter.getPassword() == null || waiter.getPassword().isEmpty() || waiter.getEmail() == null
                || waiter.getEmail().isEmpty() || waiter.getRestaurantName() == null
                || waiter.getRestaurantName().isEmpty() || waiter.getManagerName() == null
                || waiter.getManagerName().isEmpty() || waiter.getRestaurantAddress() == null
                || waiter.getRestaurantAddress().isEmpty() || waiter.getDateOfBirth() == null
                || waiter.getDateOfBirth().toString().isEmpty()) {
            response.put("message", "Waiter creation failed: missing required fields");
            return ResponseEntity.badRequest().body(response);
        }

        if (waiterService.findWaiterByUsername(waiter.getUsername()) != null) {
            response.put("message", "Waiter creation failed: username already exists");
            return ResponseEntity.badRequest().body(response);
        }

        waiterService.createWaiter(waiter);

        response.put("message", "Waiter created successfully");
        return ResponseEntity.ok().body(response);

    }
    @Operation(summary = "Login a waiter", description = "Logs in a waiter")
    @ApiResponse(responseCode = "200", description = "Waiter Logged in Successfully")
    @ApiResponse(responseCode = "400", description = "Login failed: Missing details")
    @ApiResponse(responseCode = "401", description = "Authentication failed")
    @PostMapping("/login")
    public ResponseEntity<?> loginWaiter(@RequestBody Waiter waiter, HttpServletRequest request,
            HttpServletResponse response) {
        Optional<String> missingField = validateMandatoryFieldLogin(waiter);
        if (missingField.isPresent()) {
            return ResponseEntity.badRequest().body("Login failed. Missing details: " + missingField.get());
        }

        try {

            Authentication authentication = attemptAuthentication(waiter.getUsername(), waiter.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            if (waiter.getRememberMe() != null && waiter.getRememberMe()) {
                rememberMeServices.loginSuccess(request, response, authentication);
            }

            Map<String, String> result = new HashMap<>();
            result.put("message", "Waiter Logged in Successfully");
            return ResponseEntity.ok(result);
        } catch (AuthenticationException e) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "Authentication failed: " + e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }

    private Optional<String> validateMandatoryFieldLogin(Waiter waiter) {
        if (waiter.getUsername() == null || waiter.getUsername().isEmpty()) {
            return Optional.of("username");
        }
        if (waiter.getPassword() == null || waiter.getPassword().isEmpty()) {
            return Optional.of("password");
        }
        if (waiter.getFirstName() == null || waiter.getFirstName().isEmpty()) {
            return Optional.of("firstName");
        }
        if (waiter.getLastName() == null || waiter.getLastName().isEmpty()) {
            return Optional.of("lastName");
        }

        return Optional.empty();
    }

    private Authentication attemptAuthentication(String username, String password) throws AuthenticationException {
        Authentication request = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(request);
    }
    @Operation(summary = "Validate remember me", description = "Validates the remember me token")
    @ApiResponse(responseCode = "200", description = "User authenticated with remember-me token")
    @ApiResponse(responseCode = "401", description = "Invalid or missing remember-me token")
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

}
