package com.oaxaca.menu_service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
  
  @PostMapping("/menu")
  public void addMenuItem(@RequestBody MenuItem menuItem) {
    menuService.addMenuItem(menuItem);
  }
  
  @PutMapping("/menu/{id}")
  public void updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable int id) {
    menuService.updateMenuItem(id, menuItem);
  }
  
  @DeleteMapping("/menu/{id}")
  public void deleteMenuItem(@PathVariable int id) {
    menuService.deleteMenuItem(id);
  }
}
