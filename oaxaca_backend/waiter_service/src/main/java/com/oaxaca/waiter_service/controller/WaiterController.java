package com.oaxaca.waiter_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.waiter_service.service.WaiterService;
import com.oaxaca.waiter_service.model.*;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @PostMapping("/register")
    public ResponseEntity<String> createWaiter(@RequestBody Map<String, Object> waiterData) {
        if (waiterData == null) {
                return ResponseEntity.badRequest().body("Waiter creation failed: missing required fields");
        }

            String name = (String) waiterData.get("name");
            String lastname = (String) waiterData.get("lastname");
            String username = (String) waiterData.get("username");
            String password = (String) waiterData.get("password");
            String email = (String) waiterData.get("email");
            String restaurantName = (String) waiterData.get("restaurantName");
            String managerName = (String) waiterData.get("managerName");

            Waiter waiter = new Waiter(name, lastname, username, password, email, restaurantName, managerName);
            waiterService.createWaiter(waiter);
        
        return ResponseEntity.ok().body("Waiter created successfully");

    }

    @PostMapping("/login")
    public ResponseEntity<String> loginWaiter(@RequestBody Map<String, Object> loginData) {
        try {
            String username = (String) loginData.get("username");
            String password = (String) loginData.get("password");

            Waiter waiter = waiterService.findWaiterByUsername(username);
            if (waiter == null) {
                return ResponseEntity.badRequest().body("Incorrect username or password. Waiter is nulls");
            }
            if (!waiter.getPassword().equals(password)) {
                return ResponseEntity.badRequest().body("Incorrect username or password. Wrong password");
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Login failed. Missing username or password");
        }
        return ResponseEntity.ok().body("Login successful");
    }

}
