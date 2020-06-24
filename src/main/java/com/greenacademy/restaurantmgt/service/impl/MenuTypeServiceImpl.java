package com.greenacademy.restaurantmgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.Food;
import com.greenacademy.restaurantmgt.entities.MenuType;
import com.greenacademy.restaurantmgt.repository.FoodRepository;
import com.greenacademy.restaurantmgt.repository.MenuTypeRepository;
import com.greenacademy.restaurantmgt.service.MenuTypeService;

@Service
public class MenuTypeServiceImpl implements MenuTypeService {
	
	@Autowired
	private MenuTypeRepository menuTypeRepository;
	
	@Autowired
	private FoodRepository foodRepository;
	

	@Override
	public MenuType get(Long id) {
		return menuTypeRepository.getOne(id);
	}

	
	@Override
	public List<MenuType> getAll() {
		return menuTypeRepository.findAll();
	}

	
	@Override
	public String add(MenuType type) {
		if (type.getTypeName() == null || type.getTypeName().isEmpty()) {
			return "Type name must not be empty";
		}
		menuTypeRepository.save(type);
		return null;
	}

	
	@Override
	public String update(MenuType type) {
		if (type.getTypeName() == null || type.getTypeName().isEmpty()) {
			return "Type name must not be empty";
		}
		menuTypeRepository.save(type);
		return null;
	}

	
	@Override
	public void delete(MenuType type) {
		menuTypeRepository.delete(type);
	}

	
	@Override
	public void delete(Long id) {
		Food food = foodRepository.getOne(id);
		food.setMenuType(null);
		MenuType type = menuTypeRepository.getOne(id);
		menuTypeRepository.delete(type);
	}
}
