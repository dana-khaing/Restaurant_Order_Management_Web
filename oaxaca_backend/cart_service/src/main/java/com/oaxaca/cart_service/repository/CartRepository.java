package com.oaxaca.cart_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.cart_service.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{


    public Cart findByUserId(Long userId);

    public void deleteByUserId(Long userId);

    public void deleteById(Long id);

    public Optional<Cart> findById(Long id);
    
    
}
