package com.oaxaca.order_service.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.oaxaca.order_service.model.Order;
import com.oaxaca.shared_library.model.order.OrderStatus;

public interface OrderRepository extends JpaRepository<Order, Long> {
  /**
   * Retrieves an order by its ID.
   *
   * @param id The ID of the order.
   * @return An Optional containing the order, or an empty Optional if not found.
   */
    @NonNull
    public Optional<Order> findById(@NonNull Long id);
    /**
     * Retrieves all orders sorted by creation date in descending order.
     *
     * @param pageable The pagination information.
     * @return A page of orders sorted by creation date.
     */
    @Query("SELECT o FROM Order o WHERE o.creationDate = CURRENT_DATE ORDER BY o.creationDate DESC")
    public Page<Order> findAllByOrderByCreationDateDesc(Pageable pageable);
    /**
     * Retrieves orders by their status.
     *
     * @param orderStatus The status of the orders to retrieve.
     * @param pageable    The pagination information.
     * @return A page of orders with the specified status.
     */
    public Page<Order> findByOrderStatus(OrderStatus orderStatus, Pageable pageable);

}
