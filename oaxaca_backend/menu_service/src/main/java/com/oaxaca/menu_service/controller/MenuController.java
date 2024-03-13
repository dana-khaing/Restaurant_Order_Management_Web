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
 * for the menu service, handling incoming requests and forwarding them to the menu service itself.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */

@RestController
public class MenuController {

  /** MenuService to forward requests to */
  @Autowired
  private MenuService menuService;

  /**
   * Returns the full menu from menu service as a List of MenuItem objects, or serialised JSON if
   * requested over HTTP. Can be invoked by making a GET request to "http://localhost:8083/menu".
   * 
   * @return Full menu from menu service
   */
  @RequestMapping("/menu")
  public List<MenuItem> getFullMenu() {
    return menuService.getFullMenu();
  }

  /**
   * Returns a single item on the menu by Id from the menu service if it exists, as a MenuItem
   * instance, or serialised JSON if requested over HTTP. Can be invoked by making a GET request to
   * "http://localhost:8083/menu/{id}", where id is the id number of the menu item.<br>
   * <br>
   * For example, "http://localhost:8083/menu/2" would return the menu item that matches the id of
   * 2.
   * 
   * @param id Id of requested MenuItem
   * @return Requested MenuItem if it exists, otherwise null
   */
  @RequestMapping("/menu/{id}")
  public MenuItem getMenuItem(@PathVariable int id) {
    return menuService.getMenuItem(id);
  }

  /**
   * Adds a new menu item to the database through the menu service. Can be invoked by making a POST
   * request to "http://localhost:8083/menu" with the body of the request containing the new
   * MenuItem object formatted in serialised JSON.
   * 
   * @param menuItem New MenuItem to add to database, imageURL can be omitted if no image for menu
   *        item is available
   */
  @PostMapping("/menu")
  public void addMenuItem(@RequestBody MenuItem menuItem) {
    menuService.addMenuItem(menuItem);
  }

  /**
   * Updates an existing MenuItem in the database through the menu service. Can be invoked by making
   * a PUT request to "http://localhost:8083/menu/{id}", where id is the id number of the menu item
   * to update, with the body of the request containing the whole updated MenuItem object formatted
   * in serialised JSON.
   * 
   * @param menuItem New MenuItem to overwrite the existing MenuItem with, imageURL can be omitted
   *        if no image for the menu item is available
   * @param id Id of MenuItem to update in database
   */
  @PutMapping("/menu/{id}")
  public void updateMenuItem(@RequestBody MenuItem menuItem, @PathVariable int id) {
    menuService.updateMenuItem(id, menuItem);
  }

  /**
   * Deletes an existing MenuItem from the database through the menu service. Can be invoked by
   * making a DELETE request to "http://localhost:8083/menu/{id}", where id is the id number of the
   * menu item to delete. The body of the request can be left empty.
   * 
   * @param id Id of MenuItem to delete from database
   */
  @DeleteMapping("/menu/{id}")
  public void deleteMenuItem(@PathVariable int id) {
    menuService.deleteMenuItem(id);
  }
}
