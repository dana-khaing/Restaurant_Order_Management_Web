package com.oaxaca.table_service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TableController {

  @Autowired
  private TableService tableService;

  @RequestMapping("/tables")
  public List<RestaurantTable> getAllTables() {
    return tableService.getAllTables();
  }
  
  @RequestMapping("/tables/{id}")
  public RestaurantTable getTableById(@PathVariable int id) {
    return tableService.getTableById(id);
  }
}
