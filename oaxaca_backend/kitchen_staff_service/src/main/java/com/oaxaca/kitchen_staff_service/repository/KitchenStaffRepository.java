package com.oaxaca.kitchen_staff_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;


public interface KitchenStaffRepository extends JpaRepository<KitchenStaff, Long> {

    Optional<KitchenStaff> findByFirstName(String firstName);

    Optional<KitchenStaff> findByLastName(String lastName);


    Optional<KitchenStaff> findByFirstNameAndLastName(String firstName, String lastName);

    Optional<KitchenStaff> findByUsername(String username);
    

    
}
