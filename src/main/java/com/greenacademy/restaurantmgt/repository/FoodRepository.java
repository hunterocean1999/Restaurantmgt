package com.greenacademy.restaurantmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long>{
	@Query("SELECT DISTINCT f FROM Food as f WHERE f.foodName = :foodName")
	Food findByFoodName(@Param("foodName") String foodName);
	
	@Query("SELECT f FROM Food as f JOIN f.foodType t WHERE t.id = 1")
	List<Food> findAllFoods();

	@Query("SELECT f FROM Food as f JOIN f.foodType t WHERE t.id = 2")
	List<Food> findAllDrinks();
	
	@Query("SELECT f FROM Food as f WHERE f.foodName = :foodName")
	List<Food> findAllFoodName(@Param("foodName") String foodName);
	
	@Query("SELECT f FROM Food as f WHERE f.menuType.id = :id")
	List<Food> findAllMenuId(@Param("id") Long id);
}
