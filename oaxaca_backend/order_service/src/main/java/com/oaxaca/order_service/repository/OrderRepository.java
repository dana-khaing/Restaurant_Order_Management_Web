package com.oaxaca.order_service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.order_service.model.Order;
import com.oaxaca.shared_library.model.order.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {

    public Optional<Order> findById(Long id);


    public Page<Order> findAllByOrderByCreationDateDesc(Pageable pageable);

    public Page<Order> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);

}
