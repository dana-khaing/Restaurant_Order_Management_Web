package com.oaxaca.menu_service;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.oaxaca.menu_service.model.MenuItem;
import com.oaxaca.menu_service.repository.MenuRepository;
import com.oaxaca.menu_service.service.MenuService;

/**
 * MenuDatabaseInitialiser is a class that initialises the database with a demo menu. Upon startup
 * of the Spring Boot application, this component checks if the currently connected database is
 * currently empty (embedded or external). If it is empty, it adds the demo menu through the menu
 * service, otherwise it does not make any modifications.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */

@Component
public class MenuDatabaseInitialiser implements CommandLineRunner {

  /** Repository for "menu" relation in database, schema corresponds with MenuItem class */
  @Autowired
  private MenuRepository menuRepository;

  /** MenuService to interact with the database */
  @Autowired
  private MenuService menuService;

  /**
   * Menu item 1 of demo menu: Tlayudas (Vegetarian option)
   */
  private MenuItem item1 = new MenuItem(1, 1, "Tlayudas (Vegetarian option)",
      "Large, thin, crispy tortillas with vegetable toppings.", 6.50f, Arrays.asList("Gluten"), 528,
      true, "https://i.imgur.com/RFKBd0u.jpg");
  /**
   * Menu item 2 of demo menu: Tostadas de Chapulines
   */
  private MenuItem item2 = new MenuItem(2, 1, "Tostadas de Chapulines",
      "Tostadas with guacamole and seasoned chapulines.", 7.99f, Arrays.asList("Shellfish"), 315,
      true, "https://i.imgur.com/5H51tjZ.jpg");
  /**
   * Menu item 3 of demo menu: Guacamole
   */
  private MenuItem item3 = new MenuItem(3, 1, "Guacamole",
      "Fresh avocados, tomatoes, onions, cilantro, lime juice, salt, and pepper.", 6.50f,
      Arrays.asList("None"), 256, true, "https://i.imgur.com/yrThUBQ.jpg");
  /**
   * Menu item 4 of demo menu: Mole Negro with Chicken
   */
  private MenuItem item4 =
      new MenuItem(4, 2, "Mole Negro with Chicken", "Slow-cooked chicken in dark mole sauce.",
          11.99f, Arrays.asList("Nuts", "Gluten"), 681, true, "https://imgur.com/a/jCFy2AI");
  /**
   * Menu item 5 of demo menu: Shrimp ceviche
   */
  private MenuItem item5 = new MenuItem(5, 2, "Shrimp ceviche",
      "Shrimp marinated in lime juice, mixed with tomatoes, onions, cilantro, and avocado.", 12.99f,
      Arrays.asList("Shellfish"), 421, true, "https://i.imgur.com/FdQuLGD.jpg");
  /**
   * Menu item 6 of demo menu: Tacos al pastor
   */
  private MenuItem item6 = new MenuItem(6, 2, "Tacos al pastor",
      "Marinated and grilled pork served in corn tortillas with pineapple and cilantro.", 9.99f,
      Arrays.asList("Gluten"), 553, true, "https://i.imgur.com/62TifLV.jpg");
  /**
   * Menu item 7 of demo menu: Tlayudas con Tasajo
   */
  private MenuItem item7 = new MenuItem(7, 2, "Tlayudas con Tasajo",
      "Grilled and seasoned thinly sliced beef on tlayudas.", 12.99f, Arrays.asList("Gluten"), 602,
      true, "https://i.imgur.com/w1fIP2V.jpg");
  /**
   * Menu item 8 of demo menu: Enfrijoladas
   */
  private MenuItem item8 = new MenuItem(8, 2, "Enfrijoladas",
      "Rolled tortillas filled with chicken and black bean sauce.", 11.99f, Arrays.asList("Gluten"),
      499, true, "https://i.imgur.com/p7jgTYk.jpg");
  /**
   * Menu item 9 of demo menu: Chiles Rellenos de Quesillo
   */
  private MenuItem item9 = new MenuItem(9, 3, "Chiles Rellenos de Quesillo",
      "Poblano peppers stuffed with Oaxacan cheese.", 10.99f, Arrays.asList("Dairy"), 457, true,
      "https://i.imgur.com/pbrI8Yq.jpg");
  /**
   * Menu item 10 of demo menu: Sikil P''ak
   */
  private MenuItem item10 = new MenuItem(10, 3, "Sikil P''ak (Pumpkin Seed Dip)",
      "Dip made with pumpkin seeds, tomatoes, and spices.", 8.99f, Arrays.asList(), 351, true,
      "https://i.imgur.com/58dkmUd.jpg");
  /**
   * Menu item 11 of demo menu: Chocolate Oaxaqueño
   */
  private MenuItem item11 =
      new MenuItem(11, 4, "Chocolate Oaxaqueño", "Oaxacan-style hot chocolate.", 6.99f,
          Arrays.asList("Dairy"), 292, true, "https://i.imgur.com/JX6oK5v.jpg");
  /**
   * Menu item 12 of demo menu: Nicuatole
   */
  private MenuItem item12 =
      new MenuItem(12, 4, "Nicuatole", "Traditional Oaxacan dessert with masa and chocolate.",
          7.99f, Arrays.asList("Dairy"), 330, true, "https://i.imgur.com/t9RwmN9.jpg");
  /**
   * Menu item 13 of demo menu: Tres Leches Cake
   */
  private MenuItem item13 = new MenuItem(13, 4, "Tres Leches Cake",
      "Sponge cake soaked in three kinds of milk, topped with whipped cream and fresh berries.",
      8.99f, Arrays.asList("Dairy", "Gluten"), 585, true, "https://i.imgur.com/SfLe62k.jpg");
  /**
   * Menu item 14 of demo menu: Mango Sorbet
   */
  private MenuItem item14 = new MenuItem(14, 4, "Mango Sorbet", "Refreshing mango sorbet.", 5.99f,
      Arrays.asList("None"), 201, true, "https://i.imgur.com/Xk55Nxd.jpg");
  /**
   * Menu item 15 of demo menu: Mezcal Cocktail
   */
  private MenuItem item15 =
      new MenuItem(15, 5, "Mezcal Cocktail", "Various mezcal-based cocktails.", 9.99f,
          Arrays.asList("None"), 253, true, "https://i.imgur.com/4EOAui8.jpg");
  /**
   * Menu item 16 of demo menu: Horchata
   */
  private MenuItem item16 = new MenuItem(16, 5, "Horchata", "Rice and based horchata.", 4.99f,
      Arrays.asList("None"), 139, true, "https://i.imgur.com/mfaDzvs.jpg");
  /**
   * Menu item 17 of demo menu: Coca-Cola
   */
  private MenuItem item17 = new MenuItem(17, 5, "Coca-Cola", "Carbonated soft drink with ice",
      2.80f, Arrays.asList("None"), 210, true, "https://i.imgur.com/zYCTtjY.jpg");

  /** Initialises the database upon startup of the Spring Boot application if it is empty. */
  @Override
  public void run(String[] args) throws Exception {
    // Check if database is empty
    if (menuRepository.count() == 0) {
      System.out.println(
          "[MenuDatabaseInitialiser] => \033[0;91mIMPORTANT:\u001B[0m External database is empty or disconnected; Populating database with demo menu");
      Arrays
          .asList(item1, item2, item3, item4, item5, item6, item7, item8, item9, item10, item11,
              item12, item13, item14, item15, item16, item17)
          .forEach(item -> menuService.addMenuItem(item));
    }
  }
}
