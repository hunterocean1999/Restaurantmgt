package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.UserType;

@Transactional
@Service
public interface UserTypeService {
	
	public List<UserType> getAll();
	
	public UserType get(Long id);

	public String add(UserType type);
	
	public String update(UserType type);
	
	public void delete(UserType type);
	
	public void delete(Long id);
}
