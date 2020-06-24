package com.greenacademy.restaurantmgt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.UserType;
import com.greenacademy.restaurantmgt.repository.UserTypeRepository;
import com.greenacademy.restaurantmgt.service.UserTypeService;

@Transactional
@Service
public class UserTypeServiceImpl implements  UserTypeService {
	
	@Autowired
	private UserTypeRepository userTypeRepository;

	
	@Override
	public List<UserType> getAll() {
		return userTypeRepository.findAll();
	}

	
	@Override
	public UserType get(Long id) {
		return userTypeRepository.getOne(id);
	}

	
	@Override
	public String add(UserType type) {
		userTypeRepository.save(type);
		return null;
	}

	
	@Override
	public String update(UserType type) {
		userTypeRepository.save(type);
		return null;
	}

	
	@Override
	public void delete(UserType type) {
		userTypeRepository.delete(type);
	}

	
	@Override
	public void delete(Long id) {
		UserType type = userTypeRepository.getOne(id);
		userTypeRepository.delete(type);
	}
}
