package com.oaxaca.menu_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * MenuServiceApplication is a class that handles starting the menu service. It automatically
 * initialises subsequent classes, such as the MenuRepository, MenuService and MenuController.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */
@SpringBootApplication
public class MenuServiceApplication {

  /**
   * Starts the menu service as a Spring Boot application.
   * 
   * @param args Not utilised
   */

  public static void main(String[] args) {
    SpringApplication.run(MenuServiceApplication.class, args);
  }

}
