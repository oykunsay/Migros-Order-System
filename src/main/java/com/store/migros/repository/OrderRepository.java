package com.store.migros.repository;

import com.store.migros.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT DISTINCT o FROM Order o " + "JOIN FETCH o.orderDetails d " + "JOIN FETCH d.product "
			+ "WHERE o.customer.id = :customerId")
	List<Order> findByCustomerIdWithDetailsAndProducts(@Param("customerId") Long customerId);

	@Query("SELECT DISTINCT o FROM Order o " + "JOIN FETCH o.orderDetails d " + "JOIN FETCH d.product")
	List<Order> findAllWithDetailsAndProducts();

}
