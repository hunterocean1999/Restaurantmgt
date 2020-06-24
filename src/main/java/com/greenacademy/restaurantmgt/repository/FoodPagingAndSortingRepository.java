package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.Food;

@Repository
public interface FoodPagingAndSortingRepository extends PagingAndSortingRepository<Food, Long> {
	@Query("SELECT f FROM Food as f WHERE f.foodType.typeName = :typeName")
	Page<Food> findAllByFoodType(@Param("typeName")String typeName, Pageable pageable);
	
	@Query("SELECT f FROM Food as f WHERE f.foodName = :foodName")
	Page<Food> findAllByFoodName(@Param("foodName")String foodName, Pageable pageable);
	
	@Query("select f from Food as f where f.menuType.id = :id")
	Page<Food> findAllByMenuType (@Param("id")Long id, Pageable pageable );
}
