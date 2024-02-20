package com.oaxaca.menu_service;

import org.springframework.data.repository.CrudRepository;

public interface MenuRepository extends CrudRepository<MenuItem, Integer> {
  
}
