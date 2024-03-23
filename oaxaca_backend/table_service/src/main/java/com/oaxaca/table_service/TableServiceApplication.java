package com.oaxaca.table_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TableServiceApplication is a class that handles starting the table service. It automatically
 * initialises subsequent classes, such as the RestaurantTableRepository, TableService and
 * TableController.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */

@SpringBootApplication
public class TableServiceApplication {

  /**
   * Starts the table service as a Spring Boot application.
   * 
   * @param args Not utilised
   */
  public static void main(String[] args) {
    SpringApplication.run(TableServiceApplication.class, args);
  }

}
