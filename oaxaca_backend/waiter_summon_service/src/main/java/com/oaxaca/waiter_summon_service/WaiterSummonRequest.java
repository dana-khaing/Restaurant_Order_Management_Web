package com.oaxaca.waiter_summon_service;

import java.time.OffsetDateTime;
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
  
  public WaiterSummonRequest() {
    
  }
  
}
