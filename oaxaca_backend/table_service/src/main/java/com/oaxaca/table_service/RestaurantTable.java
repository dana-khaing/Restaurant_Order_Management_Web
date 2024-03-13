package com.oaxaca.table_service;

public class RestaurantTable {

  private int tableNumber;
  
  private Waiter assignedWaiter;
  
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
