package com.oaxaca.table_service;

public class Table {

  private int tableNumber;
  private Waiter assignedWaiter;
  
  public Table(int tableNumber) {
    this.tableNumber = tableNumber;
    this.assignedWaiter = null;
  }
  
  public Table(int tableNumber, Waiter assignedWaiter) {
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
