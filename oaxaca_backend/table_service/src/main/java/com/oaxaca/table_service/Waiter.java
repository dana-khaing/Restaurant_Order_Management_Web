package com.oaxaca.table_service;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;


import jakarta.persistence.Column;

@Entity
public class Waiter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private String restaurantAddress;

    @Column(nullable = false)
    private String managerName;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Column
    private Boolean rememberMe;

    public Waiter() {
    }

    public Waiter(String username, String password, String name, String lastname, String email, String managerName,
            String restaurantName, String restaurantAddress, LocalDate dateOfBirth) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.managerName = managerName;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.dateOfBirth = dateOfBirth;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.name;
    }

    public void setFirstName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return this.lastname;
    }

    public void setLastName(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManager(String managerName) {
        this.managerName = managerName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", username='" + getUsername() + "'" +
                ", password='" + getPassword() + "'" +
                ", name='" + getFirstName() + "'" +
                ", lastname='" + getLastName() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Waiter)) {
            return false;
        }
        Waiter waiter = (Waiter) o;
        return Objects.equals(id, waiter.id) && Objects.equals(username, waiter.username)
                && Objects.equals(password, waiter.password) && Objects.equals(name, waiter.name)
                && Objects.equals(lastname, waiter.lastname) && Objects.equals(email, waiter.email);
    }

}
