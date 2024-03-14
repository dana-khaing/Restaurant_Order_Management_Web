package com.oaxaca.table_service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantTableRepository extends CrudRepository<RestaurantTable, Integer>{

}
