package com.oaxaca.menu_service.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oaxaca.menu_service.model.MenuItem;
import com.oaxaca.menu_service.repository.MenuRepository;

/**
 * MenuService is a class for updating and retrieving records from the database. It is a Spring Boot
 * service, meaning that it is automatically started when the Spring Boot application is run.
 * 
 * @author Michael Goodwin
 */

@Service
public class MenuService {

  /** Repository for "menu" relation in database, schema corresponds with MenuItem class */
  private MenuRepository menuRepository;


  /**
   * Constructor for starting the MenuService service.
   * 
   * @param menuRepository Repository for "menu" relation in database
   */
  @Autowired
  public MenuService(MenuRepository menuRepository) {
    this.menuRepository = menuRepository;
  }

  /**
   * Returns all records from the menu relation as a List of MenuItem objects.
   * 
   * @return All records from menu relation as a List of MenuItem objects
   */
  public List<MenuItem> getFullMenu() {
    List<MenuItem> menu = new ArrayList<>();
    menuRepository.findAll().forEach(menu::add);
    return menu;
  }

  /**
   * Returns a single MenuItem instance by its Id
   * 
   * @param id Id of requested MenuItem instance in database
   * @return Requested MenuItem if a record matching id exists, otherwise null
   */
  public MenuItem getMenuItem(int id) {
    return menuRepository.findById(id).orElse(null);
  }

  /**
   * Adds a new MenuItem record to the menu relation in the database.
   * 
   * @param menuItem New MenuItem to add to database
   */
  public void addMenuItem(MenuItem menuItem) {
    menuRepository.save(menuItem);
  }

  /**
   * Updates an existing MenuItem record in the menu relation of the database.
   * 
   * @param id Id of MenuItem to update
   * @param menuItem New MenuItem to overwrite existing MenuItem
   */
  public void updateMenuItem(int id, MenuItem menuItem) {
    menuRepository.save(menuItem);
  }

  /**
   * Deletes an existing MenuItem record from the menu relation of the database.
   * 
   * @param id Id of MenuItem to delete
   */
  public void deleteMenuItem(int id) {
    menuRepository.deleteById(id);
  }
}
