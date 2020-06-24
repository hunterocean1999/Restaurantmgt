package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.Customer;

@Repository
public interface CustomerPagingAndSortingRepository extends PagingAndSortingRepository<Customer, Long>{
	@Query("SELECT c FROM Customer as c WHERE c.phone = :phone")
	Page<Customer> findAllByCustomerPhone(@Param("phone")Integer customerNumberPhone, Pageable pageable);
}
