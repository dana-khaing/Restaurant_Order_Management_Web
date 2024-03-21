package com.oaxaca.table_service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TableService {

  public RestaurantTableRepository tableRepository;

  @Autowired
  public TableService(RestaurantTableRepository tableRepository) {
    this.tableRepository = tableRepository;
  }

  public List<RestaurantTable> getAllTables() {
    List<RestaurantTable> tables = new ArrayList<>();
    tableRepository.findAll().forEach(tables::add);
    return tables;
  }
  
  public RestaurantTable getTableById(int id) {
    return tableRepository.findById(id).orElse(null);
  }
  
  public void addTable(RestaurantTable table) {
    tableRepository.save(table);
  }
  
  public void deleteTable(int tableNumber) {
    tableRepository.deleteById(tableNumber);
  }

}
