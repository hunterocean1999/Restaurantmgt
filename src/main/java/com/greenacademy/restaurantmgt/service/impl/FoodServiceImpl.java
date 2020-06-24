package com.greenacademy.restaurantmgt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.Food;
import com.greenacademy.restaurantmgt.entities.FoodType;
import com.greenacademy.restaurantmgt.repository.FoodPagingAndSortingRepository;
import com.greenacademy.restaurantmgt.repository.FoodRepository;
import com.greenacademy.restaurantmgt.repository.FoodTypeRepository;
import com.greenacademy.restaurantmgt.service.FoodService;

@Transactional
@Service
public class FoodServiceImpl implements FoodService {

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private FoodTypeRepository foodTypeRepository;

	@Autowired
	private FoodPagingAndSortingRepository foodPagingAndSortingRepository;
	
	
	@Override
	public List<Food> getAll() {
		return foodRepository.findAll();
	}

	
	@Override
	public Food get(Long id) {
		return foodRepository.getOne(id);
	}

	
	@Override
	public Food add(Food food) {
		return foodRepository.save(food);
	}

	
	@Override
	public Food update(Food food) {
		return foodRepository.save(food);
	}

	
	@Override
	public void delete(Food food) {
		foodRepository.delete(food);
	}

	
	@Override
	public void delete(Long id) {
		Food food = foodRepository.getOne(id);
		foodRepository.delete(food);
	}

	public List<Food> getAllFoods() {
		return foodRepository.findAllFoods();
	}

	
	/* Food & Drink */
	@Override
	public Food addfood(Food food) {
		FoodType foodType = foodTypeRepository.getOne(1L);
		food.setFoodType(foodType);
		return foodRepository.save(food);
	}

	@Override
	public List<Food> getAllDrinks() {
		return foodRepository.findAllDrinks();
	}

	@Override
	public Food addDrink(Food food) {
		FoodType foodType = foodTypeRepository.getOne(2L);
		food.setFoodType(foodType);
		return foodRepository.save(food);
	}

	@Override
	public List<Food> getAllByFoodName(String foodName) {
		return foodRepository.findAllFoodName(foodName);
	}
	/* ------------ */

	
	@Override
	public List<Food> getAllByMenuId(Long id) {
		return foodRepository.findAllMenuId(id);
	}

	
	@Override
	public String castError(Food food) {
		if (food.getFoodName() == null || food.getFoodName().isEmpty()) {
			return "Food name must not be empty";
		}
		
		if (food.getId() == null){
			List<Food> foodList = foodRepository.findAll();
			for (Food f : foodList) {
				if (f.getFoodName().equals(food.getFoodName())) {
					return "Food name already exist";
				}
			}
		}
		
		if (food.getDescription() == null || food.getDescription().isEmpty()) {
			return "Description must not be empty";
		}

		if (food.getUnitPrice() == null) {
			return "Unit price must not be empty";
		}
		else if (food.getUnitPrice() == 0) {
			return "Unit price must not equal to 0";
		}
		else if (food.getUnitPrice() < 0) {
			return "Unit price must not smaller than 0";
		}

		return null;
	}

	
	@Override
	public Page<Food> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return foodPagingAndSortingRepository.findAll(paging);
	}

	
	@Override
	public Page<Food> getAllByFoodTypeWithPagination(Integer pageNo, Integer pageSize, String sortBy, String typeName) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return foodPagingAndSortingRepository.findAllByFoodType(typeName, paging);
	}

	
	@Override
	public Page<Food> getAllByFoodNameWithPagination(Integer pageNo, Integer pageSize, String sortBy, String foodName) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return foodPagingAndSortingRepository.findAllByFoodName(foodName, paging);
	}

	
	@Override
	public Page<Food> getAllByMenuTypeWithPagination(Integer pageNo, Integer pageSize, String sortBy, Long idMenu) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return foodPagingAndSortingRepository.findAllByMenuType(idMenu, paging);
	}


}
