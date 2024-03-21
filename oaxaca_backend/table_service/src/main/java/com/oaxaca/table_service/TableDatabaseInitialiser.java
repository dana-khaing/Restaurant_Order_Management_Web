package com.oaxaca.table_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TableDatabaseInitialiser implements CommandLineRunner {

  @Autowired
  private RestaurantTableRepository tableRepository;

  @Autowired
  private TableService tableService;

  private final int NUMBER_OF_TABLES = 30;

  @Override
  public void run(String[] args) throws Exception {
    // Check if database is empty
    if (tableRepository.count() == 0) {
      System.out.println(
          "[TableDatabaseInitialiser] => \033[0;91mIMPORTANT:\u001B[0m External database is empty or disconnected; Populating database with "
              + Integer.toString(NUMBER_OF_TABLES) + " table"
              + ((NUMBER_OF_TABLES != 1) ? "s" : ""));
      for (int i = 1; i <= NUMBER_OF_TABLES; i++) {
        tableService.addTable(new RestaurantTable(i, null));
      }
    }
  }
}
