package com.oaxaca.table_service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * RestaurantTableRepository is a class that extends CrudRepository, which is an interface for
 * database operations. This makes it possible to call methods for interacting with the database,
 * specifically for the "tables" relation, based on the RestaurantTable class as a schema.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */

@Repository
public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Integer> {

}
