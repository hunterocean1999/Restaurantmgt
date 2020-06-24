package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.MenuType;

@Transactional
@Service
public interface MenuTypeService {
	
	public List<MenuType> getAll();
	
	public MenuType get(Long id);

	public String add(MenuType type);
	
	public String update(MenuType type);
	
	public void delete(MenuType type);
	
	public void delete(Long id);
	
}
