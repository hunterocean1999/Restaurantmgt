package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.FoodType;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodType, Long>{

}
