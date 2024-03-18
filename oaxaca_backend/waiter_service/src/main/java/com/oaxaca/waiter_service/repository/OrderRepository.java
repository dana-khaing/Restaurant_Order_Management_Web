package com.oaxaca.waiter_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.shared_library.model.order.OrderType;
import com.oaxaca.waiter_service.model.Order;

import java.util.List;
import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long>{

    @SuppressWarnings("null")
    public Optional<Order> findById(Long id);
    
    public Page<Order> findAllByOrderByCreationDateDesc(Pageable pageable);

    public Page<Order> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);




    
    
}
