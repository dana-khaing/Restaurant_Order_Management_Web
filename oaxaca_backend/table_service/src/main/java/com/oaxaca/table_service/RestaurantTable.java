package com.oaxaca.table_service;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tables")
public class RestaurantTable {

  @Id
  private int tableNumber;
  
  @ManyToOne
  private Waiter assignedWaiter;
  
  public RestaurantTable() {
    
  }
  
  public RestaurantTable(int tableNumber) {
    this.tableNumber = tableNumber;
    this.assignedWaiter = null;
  }
  
  public RestaurantTable(int tableNumber, Waiter assignedWaiter) {
    this.tableNumber = tableNumber;
    this.assignedWaiter = assignedWaiter;
  }
  
  public int getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }

  public Waiter getAssignedWaiter() {
    return assignedWaiter;
  }

  public void setAssignedWaiter(Waiter assignedWaiter) {
    this.assignedWaiter = assignedWaiter;
  }
  
}