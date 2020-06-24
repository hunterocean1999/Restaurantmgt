package com.greenacademy.restaurantmgt.repository;

import java.sql.Date;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	@Query("SELECT p FROM Order p WHERE p.orderDate BETWEEN :fromDate AND :toDate")
	List<Order> searchByDates(@Param("fromDate")Date fromDate,@Param("toDate") Date toDate);
}
