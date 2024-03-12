package com.oaxaca.menu_service.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.oaxaca.menu_service.model.MenuItem;
import com.oaxaca.menu_service.service.MenuService;

/**
 * MenuController is a class that handles requests to the menu service. It behaves as a controller
 * for the menu service.
 * 
 * @author Michael Goodwin
 */

@RestController
public class MenuController {

  /** MenuService to forward requests to */
  @Autowired
  private MenuService menuService;

  /**
   * Returns full menu from menu service as a List of MenuItem objects.
   * 
   * @return Full menu from menu service
   */
  @RequestMapping("/menu")
  public List<MenuItem> getFullMenu() {
    return menuService.getFullMenu();
  }

  /**
   * Returns a single MenuItem instance by Id from the menu service.
   * 
   * @param id Id of requested MenuItem
   * @return Requested MenuItem if it exists, otherwise null
   */
  @RequestMapping("/menu/{id}")
  public MenuItem getMenuItem(@PathVariable int id) {
    return menuService.getMenuItem(id);
  }

  /**
   * Adds a new MenuItem to the database through the menu service.
   * 
   * @param menuItem New MenuItem to add to database
   */
  @PostMapping("/menu")
  public void addMenuItem(@RequestBody MenuItem menuItem) {
    menuService.addMenuItem(menuItem);
  }

  /**
   * Updates an existing MenuItem in the database through the menu service.
   * 
   * @param menuItem New MenuItem to overwrite the existing MenuItem with
   * @param id Id of MenuItem to update in database
   */
  @PutMapping("/menu/{id}")
  public void updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable int id) {
    menuService.updateMenuItem(id, menuItem);
  }

  /**
   * Deletes an existing MenuItem from the database through the menu service.
   * 
   * @param id Id of MenuItem to delete from database
   */
  @DeleteMapping("/menu/{id}")
  public void deleteMenuItem(@PathVariable int id) {
    menuService.deleteMenuItem(id);
  }
}
