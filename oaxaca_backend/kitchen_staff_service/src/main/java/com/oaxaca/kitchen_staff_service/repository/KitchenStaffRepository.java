package com.oaxaca.kitchen_staff_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.kitchen_staff_service.model.KitchenStaff;


public interface KitchenStaffRepository extends JpaRepository<KitchenStaff, Long> {
  /**
   * Finds a kitchen staff member by first name.
   *
   * @param firstName The first name of the kitchen staff member.
   * @return The found kitchen staff member, or an empty Optional if not found.
   */
    Optional<KitchenStaff> findByFirstName(String firstName);

    /**
     * Finds a kitchen staff member by last name.
     *
     * @param lastName The last name of the kitchen staff member.
     * @return The found kitchen staff member, or an empty Optional if not found.
     */
    Optional<KitchenStaff> findByLastName(String lastName);

    /**
     * Finds a kitchen staff member by first and last name.
     *
     * @param firstName The first name of the kitchen staff member.
     * @param lastName  The last name of the kitchen staff member.
     * @return The found kitchen staff member, or an empty Optional if not found.
     */
    Optional<KitchenStaff> findByFirstNameAndLastName(String firstName, String lastName);
    /**
     * Finds a kitchen staff member by username.
     *
     * @param username The username of the kitchen staff member.
     * @return The found kitchen staff member, or an empty Optional if not found.
     */
    Optional<KitchenStaff> findByUsername(String username);
    

    
}
