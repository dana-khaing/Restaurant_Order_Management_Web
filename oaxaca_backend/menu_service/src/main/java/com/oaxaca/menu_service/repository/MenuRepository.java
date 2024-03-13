package com.oaxaca.menu_service.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.oaxaca.menu_service.model.MenuItem;

/**
 * MenuRepository is a class that extends CrudRepository, which is an interface for database
 * operations. This makes it possible to call methods for interacting with the database.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */

@Repository
public interface MenuRepository extends CrudRepository<MenuItem, Integer> {

}
