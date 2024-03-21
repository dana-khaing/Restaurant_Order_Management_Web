package com.oaxaca.table_service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tables")
public class TableController {

  @Autowired
  private TableService tableService;

  @RequestMapping("/allTables")
  public List<RestaurantTable> getAllTables() {
    return tableService.getAllTables();
  }
  
  @RequestMapping("/table/{id}")
  public RestaurantTable getTableById(@PathVariable int id) {
    return tableService.getTableById(id);
  }
  
  @PostMapping("/addTable")
  public void addTable(@RequestBody RestaurantTable table) {
    tableService.addTable(table);
  }
  
  @DeleteMapping("/deleteTable/{tableNumber}")
  public void deleteTable(@RequestBody int tableNumber) {
    tableService.deleteTable(tableNumber);
  }
  
  @PostMapping("/assignWaiter/{tableNumber}/{waiterId}")
  public void assignWaiterToTable(@PathVariable int tableNumber, @PathVariable int waiterId) {
    tableService.assignWaiterToTable(tableNumber, waiterId);
  }
  
}
