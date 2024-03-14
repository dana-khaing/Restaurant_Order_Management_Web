package com.oaxaca.table_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

  public RestaurantTableRepository tableRepository;
  
  @Autowired
  public TableService(RestaurantTableRepository tableRepository) {
    this.tableRepository = tableRepository;
  }
}
