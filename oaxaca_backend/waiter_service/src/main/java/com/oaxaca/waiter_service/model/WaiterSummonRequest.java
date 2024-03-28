package com.oaxaca.waiter_service.model;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "WaiterSummonRequests")
public class WaiterSummonRequest {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int requestId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "customer", nullable = false)
  private Customer customer;

  // This can be remapped to the customer attribute later, table number can be manually specified
  // for now
  @Column(name = "tableNumber", nullable = false)
  private int tableNumber;

  @Column(name = "summonRequestTime", columnDefinition = "TIMESTAMP WITH TIME ZONE",
      nullable = false)
  private OffsetDateTime summonRequestTime;

  @Column(name = "isCustomerServed", nullable = false)
  private boolean isCustomerServed;
  
  // Default no-argument constructor
  // This is required by JPA for database functionality to work
  public WaiterSummonRequest() {
    
  }
  /**
   * Constructs a new WaiterSummonRequest.
   * 
   * @param customer The customer associated with the summon request.
   * @param tableNumber The table number for the summon request.
   */
  public WaiterSummonRequest(Customer customer, int tableNumber) {
    this.customer = customer;
    this.tableNumber = tableNumber;
    this.summonRequestTime = OffsetDateTime.now(ZoneId.of("Europe/London"));
    this.isCustomerServed = false;
  }

  public int getRequestId() {
    return requestId;
  }
  
  // No setter for requestId, this field is immutable

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public int getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(int tableNumber) {
    this.tableNumber = tableNumber;
  }

  public OffsetDateTime getSummonRequestTime() {
    return summonRequestTime;
  }

  public void setSummonRequestTime(OffsetDateTime summonRequestTime) {
    this.summonRequestTime = summonRequestTime;
  }

  public boolean getIsCustomerServed() {
    return isCustomerServed;
  }

  public void setIsCustomerServed(boolean isCustomerServed) {
    this.isCustomerServed = isCustomerServed;
  }
  
}
