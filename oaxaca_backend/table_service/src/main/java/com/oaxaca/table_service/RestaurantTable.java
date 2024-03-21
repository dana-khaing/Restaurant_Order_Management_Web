package com.oaxaca.table_service;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * RestaurantTable is a class that represents one table at the Oaxaca restaurant. It also represents
 * the schema for the "tables" relation in the database.<br>
 * <br>
 * Due to a naming conflict with the \@Table annotation from jakara.persistence, this class has been
 * renamed to RestaurantTable.
 * 
 * @author Michael Goodwin (michael.goodwin.2022@live.rhul.ac.uk)
 */
@Entity
@Table(name = "tables")
public class RestaurantTable {

  // User story: Waiter - table assignment
  /** Number of the table, acts as Id number */
  @Id
  private int tableNumber;

  /** Waiter ID of waiter assigned to this table */
  @Column(name = "assignedWaiter", nullable = true)
  private Integer assignedWaiter;

  /**
   * Default no-argument constructor. This is required by JPA for database functionality to work and
   * should be avoided in programming under normal circumstances.
   */
  public RestaurantTable() {

  }

  /**
   * Creates a new restaurant table with no assigned waiter.
   * 
   * @param tableNumber Number of the table
   */
  public RestaurantTable(int tableNumber) {
    this.tableNumber = tableNumber;
    this.assignedWaiter = null;
  }

  /**
   * Creates a new restaurant table with a waiter already assigned.
   * 
   * @param tableNumber Number of the table
   * @param assignedWaiter ID of waiter to assign
   */
  public RestaurantTable(int tableNumber, Integer assignedWaiter) {
    this.tableNumber = tableNumber;
    this.assignedWaiter = assignedWaiter;
  }

  /**
   * Returns the table number of this RestaurantTable instance.
   * 
   * @return Table number of this RestaurantTable instance
   */
  public int getTableNumber() {
    return tableNumber;
  }

  /**
   * Sets the table number of this RestaurantTable instance.
   * 
   * @param tableNumber New table number of this RestaurantTable instance
   */
  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }

  /**
   * Returns the ID of the waiter assigned to this RestaurantTable instance.
   * 
   * @return ID of the waiter assigned to this RestaurantTable instance
   */
  public Integer getAssignedWaiter() {
    return assignedWaiter;
  }

  /**
   * Sets the waiter assigned to this RestaurantTable instance.
   * 
   * @param assignedWaiter ID of the waiter to assign to this RestaurantTable instance
   */
  public void setAssignedWaiter(Integer assignedWaiter) {
    this.assignedWaiter = assignedWaiter;
  }

}
