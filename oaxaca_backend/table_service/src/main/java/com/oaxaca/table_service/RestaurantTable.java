package com.oaxaca.table_service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables")
public class RestaurantTable {

  @Id
  private int tableNumber;
  
  @Column(name = "assignedWaiter", nullable = true)
  private Integer assignedWaiter;
  
  public RestaurantTable() {
    
  }
  
  public RestaurantTable(int tableNumber) {
    this.tableNumber = tableNumber;
    this.assignedWaiter = null;
  }
  
  public RestaurantTable(int tableNumber, Integer assignedWaiter) {
    this.tableNumber = tableNumber;
    this.assignedWaiter = assignedWaiter;
  }
  
  public int getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }

  public Integer getAssignedWaiter() {
    return assignedWaiter;
  }

  public void setAssignedWaiter(Integer assignedWaiter) {
    this.assignedWaiter = assignedWaiter;
  }
  
}