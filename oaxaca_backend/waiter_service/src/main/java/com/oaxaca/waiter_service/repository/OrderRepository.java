package com.oaxaca.waiter_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;
import com.oaxaca.waiter_service.model.Order;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long>{

    public Optional<Order> findById(Long id);

    public List<Order> findByCustomerName(String customerName);

    public List<Order> findByOrderStatus(OrderStatus orderStatus);

    public List<Order> findByOrderType(OrderType orderType);

    public List<Order> findByCustomerNameAndOrderStatus(String customerName, OrderStatus orderStatus);

    public List<Order> findByCustomerNameAndOrderType(String customerName, OrderType orderType);

    public List<Order> findByOrderStatusAndOrderType(OrderStatus orderStatus, OrderType orderType);


    
    
}
