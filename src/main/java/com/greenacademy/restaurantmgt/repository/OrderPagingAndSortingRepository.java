package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.Order;

@Repository
public interface OrderPagingAndSortingRepository extends PagingAndSortingRepository<Order, Long> {
	
}
