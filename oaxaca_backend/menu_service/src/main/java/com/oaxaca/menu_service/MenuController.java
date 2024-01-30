package com.oaxaca.menu_service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MenuController {

  @Autowired
  private MenuService menuService;
  
  @RequestMapping("/menu")
  public List<MenuItem> getAllTopics() {
    return menuService.getFullMenu();
  }
  
  @RequestMapping("/menu/{id}")
  public MenuItem getTopic(@PathVariable int id) {
    return menuService.getMenuItem(id);
  }
}
