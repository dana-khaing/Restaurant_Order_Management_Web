package com.oaxaca.waiter_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.waiter_service.model.Waiter;

public interface WaiterRepository extends JpaRepository<Waiter, Long> {

    Optional<Waiter> findById(Long id);

    Optional<Waiter> findByUsername(String username);

    Boolean existsByUsername(String username);

    Optional<Waiter> findByEmail(String email);

}
