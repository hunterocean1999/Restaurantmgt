package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.TotalFinance;

@Repository
public interface TotalFinancePagingAndSortingRepository extends PagingAndSortingRepository<TotalFinance, Long>{

}
