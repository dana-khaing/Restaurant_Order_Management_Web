package com.oaxaca.waiter_service.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.oaxaca.shared_library.model.order.OrderStatus;
import com.oaxaca.waiter_service.model.Order;

import java.util.Optional;


public interface OrderRepository extends JpaRepository<Order, Long>{

  /**
   * Retrieves an Order by its ID.
   * 
   * @param id The ID of the Order.
   * @return An Optional containing the Order, or an empty Optional if not found.
   */
    @SuppressWarnings("null")
    public Optional<Order> findById(Long id);
    /**
     * Retrieves all Orders, sorted by creation date in descending order.
     * 
     * @param pageable The pagination information.
     * @return A Page containing the Orders.
     */
    public Page<Order> findAllByOrderByCreationDateDesc(Pageable pageable);
    /**
     * Retrieves Orders by their status.
     * 
     * @param orderStatus The status of the Orders to retrieve.
     * @param pageable The pagination information.
     * @return A Page containing the Orders with the specified status.
     */
    public Page<Order> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);




    
    
}
