package com.oaxaca.table_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * TableDatabaseInitialiser is a class that initialises the database with unassigned tables. Upon
 * startup of the Spring Boot application, this component checks if the currently connected database
 * is currently empty (embedded or external). If it is empty, it adds the tables through the table
 * service, otherwise it does not make any modifications.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */

@Component
public class TableDatabaseInitialiser implements CommandLineRunner {

  /**
   * Repository for "tables" relation in database, schema corresponds with RestaurantRepository
   * class
   */
  @Autowired
  private RestaurantTableRepository tableRepository;

  /** TableService to interact with the database */
  @Autowired
  private TableService tableService;

  /** Constant for how many restaurant tables should be added to the database by default */
  private final int NUMBER_OF_TABLES = 30;

  /** Initialises the database upon startup of the Spring Boot application if it is empty. */
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
