package com.greenacademy.restaurantmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	@Query("SELECT d FROM OrderDetail as d WHERE d.order.id = :id")
	List<OrderDetail> getAllbyOrderId(@Param("id") Long id);
}
