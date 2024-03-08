package com.oaxaca.menu_service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * MenuRepository is a class that extends CrudRepository, which is an interface for database
 * operations. This makes it possible to call methods for interacting with the database.
 * 
 * @author Michael Goodwin
 */
@Repository
public interface MenuRepository extends CrudRepository<MenuItem, Integer> {

}
