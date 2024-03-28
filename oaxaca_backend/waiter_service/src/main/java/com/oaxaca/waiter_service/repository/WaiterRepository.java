package com.oaxaca.waiter_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.waiter_service.model.Waiter;

public interface WaiterRepository extends JpaRepository<Waiter, Long> {
  /**
   * Retrieves a Waiter by its ID.
   * 
   * @param id The ID of the Waiter.
   * @return An Optional containing the Waiter, or an empty Optional if not found.
   */
    @SuppressWarnings("null")
    Optional<Waiter> findById(Long id);
    /**
     * Retrieves a Waiter by its username.
     * 
     * @param username The username of the Waiter.
     * @return An Optional containing the Waiter, or an empty Optional if not found.
     */
    Optional<Waiter> findByUsername(String username);
    /**
     * Checks if a Waiter exists with the given username.
     * 
     * @param username The username to check.
     * @return true if a Waiter with the username exists, false otherwise.
     */
    Boolean existsByUsername(String username);
    /**
     * Retrieves a Waiter by its email.
     * 
     * @param email The email of the Waiter.
     * @return An Optional containing the Waiter, or an empty Optional if not found.
     */
    Optional<Waiter> findByEmail(String email);

}
