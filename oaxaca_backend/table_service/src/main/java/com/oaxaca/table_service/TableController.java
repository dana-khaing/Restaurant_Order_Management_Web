package com.oaxaca.table_service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TableController is a class that handles requests made to the table service. It behaves as a REST
 * controller for the table service, handling incoming requests and forwarding them to the table
 * service itself.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */
@RestController
@RequestMapping("/tables")
public class TableController {

  @Autowired
  private TableService tableService;

  /**
   * Returns all the tables from the table service as a list of RestaurantTable objects, or
   * serialised JSON if requested over HTTP. Can be invoked by making a GET request to
   * "http://localhost:8085/tables/allTables".
   * 
   * @return All tables from the table service
   */
  @GetMapping("/allTables")
  public List<RestaurantTable> getAllTables() {
    return tableService.getAllTables();
  }

  /**
   * Return a single table by table number from the table service if it exists, as a RestaurantTable
   * instance, or serialised JSON if requested over HTTP. Can be invoked by making a GET request to
   * "http://localhost:8085/tables/table/{tableNumber}", where {tableNumber} is the number of the
   * requested table.<br>
   * <br>
   * For example, "http://localhost:8085/tables/table/10" would return the restaurant table that
   * matches the table number of 10.
   * 
   * @param tableNumber Number of table to return
   * @return Requested RestaurantTable if it exists, otherwise null
   */
  @GetMapping("/table/{tableNumber}")
  public RestaurantTable getTableById(@PathVariable int tableNumber) {
    return tableService.getTableById(tableNumber);
  }

  /**
   * Add a new restaurant table (not to be confused with database relation) to the database through
   * the table service. Can be invoked by making a POST request to
   * "http://localhost:8085/tables/addTable" with the body of the request containing the new
   * RestaurantTable object formatted in JSON.
   * 
   * @param table New RestaurantTable to add to database, assignedWaiter can be omitted if there
   *        initially should not be a waiter assigned
   */
  @PostMapping("/addTable")
  public void addTable(@RequestBody RestaurantTable table) {
    tableService.addTable(table);
  }

  /**
   * Delete a restaurant table (not to be confused with database relation) from the database through
   * the table service. Can be invoked by making a DELETE request to
   * "http://localhost:8085/tables/deleteTable/{tableNumber}", where {tableNumber} is the number of
   * the table to be deleted. The body of the request can be left empty.<br>
   * <br>
   * For example, a DELETE request to "http://localhost:8085/tables/deleteTable/6" would delete the
   * restaurant table matching the table number 6 from the database.
   * 
   * @param tableNumber Table number of restaurant table to delete from database
   */
  @DeleteMapping("/deleteTable/{tableNumber}")
  public void deleteTable(@PathVariable int tableNumber) {
    tableService.deleteTable(tableNumber);
  }

  /**
   * Assigns a waiter to a restaurant table, if it exists. Can be invoked by making a POST request
   * to "http://localhost:8085/tables/assignWaiter/{tableNumber}/{waiterId}", where {tableNumber} is
   * the number of the table to assign the waiter to and {waiterId} is the ID of the waiter to
   * assign. The body of the request can be left empty.<br>
   * <br>
   * For example, a POST request to "http://localhost:8085/tables/assignWaiter/17/5" would assign
   * the waiter of ID 5 to table 17.
   * 
   * @param tableNumber Table number to assign waiter to
   * @param waiterId ID of waiter to assign to table
   */
  @PostMapping("/assignWaiter/{tableNumber}/{waiterId}")
  public void assignWaiterToTable(@PathVariable int tableNumber, @PathVariable int waiterId) {
    tableService.assignWaiterToTable(tableNumber, waiterId);
  }

  /**
   * Unassigns the waiter from a restaurant table, if it exists. Can be invoked by making a POST
   * request to "http://localhost:8085/tables/unassignWaiter/{tableNumber}", where {tableNumber} is
   * the number of the table to unassign the waiter from. The result is that the assignedWaiter
   * field is set to null. The body of the request can be left empty.<br>
   * <br>
   * For example, a POST request to "http://localhost:8085/tables/unassignWaiter/3" would unassign
   * the waiter from table 3. The assignedWaiter field of table 3 would be set to null.
   * 
   * @param tableNumber Table number to unassign the waiter from
   */
  @PostMapping("/unassignWaiter/{tableNumber}")
  public void unassignWaiterFromTable(@PathVariable int tableNumber) {
    tableService.unassignWaiterFromTable(tableNumber);
  }

}
