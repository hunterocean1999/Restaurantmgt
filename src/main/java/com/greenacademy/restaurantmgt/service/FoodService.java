package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.Food;

@Transactional
@Service
public interface FoodService {
	
	public List<Food> getAll();
	
	public Food get(Long id);
	
	public Food add(Food food);
	
	public Food update(Food food);
	
	public void delete(Food food);
	
	public void delete(Long id);
	
	public List<Food> getAllByFoodName(String foodName);
	
	public List<Food> getAllByMenuId(Long id);
	
	
	/* PageSize */
	public Page<Food> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
	
	public Page<Food> getAllByFoodTypeWithPagination(Integer pageNo, Integer pageSize, String sortBy, String typeName);
	
	public Page<Food> getAllByFoodNameWithPagination(Integer pageNo, Integer pageSize, String sortBy, String foodName);
	
	public Page<Food> getAllByMenuTypeWithPagination(Integer pageNo, Integer pageSize, String sortBy, Long menuId);
	
	
	/* Food & Drink */
	public List<Food> getAllFoods();
	public Food addfood(Food food);
	
	public List<Food> getAllDrinks();
	public Food addDrink(Food food);
	
	/* ------------ */
	
	
	public String castError(Food food);
}
