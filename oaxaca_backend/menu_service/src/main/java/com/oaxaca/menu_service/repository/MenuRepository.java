package com.oaxaca.menu_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.oaxaca.menu_service.model.MenuItem;

@Repository
public interface MenuRepository extends CrudRepository<MenuItem, Integer> {
  
}
