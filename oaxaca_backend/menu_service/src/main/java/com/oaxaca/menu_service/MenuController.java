package com.oaxaca.menu_service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class MenuController {
  @Autowired
  private MenuService menuService;
  
  @RequestMapping("/menu")
  public List<MenuItem> getFullMenu() {
    return menuService.getFullMenu();
  }
  
  @RequestMapping("/menu/{id}")
  public MenuItem getMenuItem(@PathVariable int id) {
    return menuService.getMenuItem(id);
  }
}
