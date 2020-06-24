package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.TotalFinance;

@Repository
public interface TotalFinanceRepository extends JpaRepository<TotalFinance, Long> {

}
