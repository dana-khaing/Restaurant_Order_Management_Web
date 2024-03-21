package com.oaxaca.table_service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TableService is a class for updating and retrieving records from the database. It is a Spring
 * Boot service, meaning that it is automatically started when the Spring Boot application is run.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */
@Service
public class TableService {

  /** Repository for "tables" relation in database, schema corresponds with RestaurantTable class */
  public RestaurantTableRepository tableRepository;

  /**
   * Constructor for starting the TableService service. This is automatically called by the Spring
   * Boot application during startup.
   * 
   * @param tableRepository Repository for "tables" relation in database
   */
  @Autowired
  public TableService(RestaurantTableRepository tableRepository) {
    this.tableRepository = tableRepository;
  }

  /**
   * Returns all records from the menu relation as a List of RestaurantTable objects.
   * 
   * @return All records from menu relation as a List of RestaurantTable objects
   */
  public List<RestaurantTable> getAllTables() {
    List<RestaurantTable> tables = new ArrayList<>();
    tableRepository.findAll().forEach(tables::add);
    return tables;
  }

  /**
   * Returns a single RestaurantTable instance by its table number
   * 
   * @param tableNumber Table number of requested RestaurantTable instance in database
   * @return Requested RestaurantTable if a record matching table number exists, otherwise null
   */
  public RestaurantTable getTableById(int tableNumber) {
    return tableRepository.findById(tableNumber).orElse(null);
  }

  /**
   * Adds a new RestaurantTable record to the tables relation in the database.
   * 
   * @param table New RestaurantTable record to add to database
   */
  public void addTable(RestaurantTable table) {
    tableRepository.save(table);
  }

  /**
   * Deletes an existing RestaurantTable record from the tables relation of the database.
   * 
   * @param tableNumber Table number of restaurant table to delete
   */
  public void deleteTable(int tableNumber) {
    tableRepository.deleteById(tableNumber);
  }

  /**
   * Assigns a waiter to a table and writes the updated restaurant table to the tables relation in
   * the database.
   * 
   * @param tableNumber Table number to assign a waiter to
   * @param waiterId Id of waiter to assign to restaurant table
   */
  public void assignWaiterToTable(int tableNumber, int waiterId) {
    // Check if the restaurant table exists in the database
    RestaurantTable table = tableRepository.findById(tableNumber).orElse(null);
    if (table == null) {
      System.out
          .println("[TableService] => Error while assigning waiter " + Integer.toString(waiterId)
              + " to table " + Integer.toString(tableNumber) + " - Table not found in database");
      return;
    }

    // Assign waiter to restaurant table and write to database
    table.setAssignedWaiter(waiterId);
    tableRepository.save(table);
  }

  /**
   * Unassigns a waiter from a table and writes the updated restaurant table to the tables relation
   * in the database. The assignedWaiter field becomes null.
   * 
   * @param tableNumber Table number to unassign a waiter from
   */
  public void unassignWaiterFromTable(int tableNumber) {
    // Check if the restaurant table exists in the database
    RestaurantTable table = tableRepository.findById(tableNumber).orElse(null);
    if (table == null) {
      System.out.println("[TableService] => Error while unassigning the waiter from table "
          + Integer.toString(tableNumber) + " - Table not found in database");
      return;
    }

    // Unassign waiter from restaurant table and write to database
    table.setAssignedWaiter(null);
    tableRepository.save(table);
  }

}
