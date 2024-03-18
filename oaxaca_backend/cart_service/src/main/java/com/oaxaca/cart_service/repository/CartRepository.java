package com.oaxaca.cart_service.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.oaxaca.cart_service.model.Cart;

public interface CartRepository extends CrudRepository<Cart, String>{


  

    public Optional<Cart> findById(Long id);
    
    
}
