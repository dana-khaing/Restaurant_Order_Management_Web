package com.oaxaca.menu_service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

  private MenuRepository menuRepository;

  @Autowired
  public MenuService(MenuRepository menuRepository) {
    this.menuRepository = menuRepository;
  }

  public List<MenuItem> getFullMenu() {
    List<MenuItem> menu = new ArrayList<>();
    menuRepository.findAll().forEach(menu::add);
    return menu;
  }

  public MenuItem getMenuItem(int id) {
    return menuRepository.findById(id).orElse(null);
  }

  public void addMenuItem(MenuItem menuItem) {
    menuRepository.save(menuItem);
  }

  public void updateMenuItem(int id, MenuItem menuItem) {
      menuRepository.save(menuItem);
  }

  public void deleteMenuItem(int id) {
    menuRepository.deleteById(id);
  }
}
