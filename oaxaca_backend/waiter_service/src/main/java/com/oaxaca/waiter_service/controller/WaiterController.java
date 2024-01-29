package com.oaxaca.waiter_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.oaxaca.waiter_service.service.WaiterService;
import com.oaxaca.waiter_service.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/waiter")
public class WaiterController {

    @Autowired
    private WaiterService waiterService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> createWaiter(@RequestParam String name, @RequestParam String lastname,
            @RequestParam String username, @RequestParam String password, @RequestParam String email,
            @RequestParam String restaurantName, @RequestParam String managerName) {
        try {
            Waiter waiter = new Waiter(name, lastname, username, password, email, restaurantName, managerName);
            waiterService.createWaiter(waiter);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Waiter creation failed");
        }
        return ResponseEntity.ok().body("Waiter created successfully");
    }

    @RequestMapping("/login")
    public ResponseEntity<String> loginWaiter(@RequestParam String username, @RequestParam String password) {
        try {
            Waiter waiter = waiterService.findWaiterByUsername(username);
            if (waiter == null) {
                return ResponseEntity.badRequest().body("Incorrect username or password");
            }
            if (!waiter.getPassword().equals(password)) {
                return ResponseEntity.badRequest().body("Incorrect username or password");
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("Waiter login failed");
        }
        return ResponseEntity.ok().body("Waiter logged in successfully");
    }

}
