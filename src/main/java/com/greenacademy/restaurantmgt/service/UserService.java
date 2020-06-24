package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.User;

@Transactional
@Service
public interface UserService {
	
	public List<User> getAll();
	
	public User get(Long id);
	
	public User add(User user);
	
	public User update(User user);
	
	public void delete(User user);
	
	public void delete(Long id);
	
	public User getByUserName(String userName);
	
	public List<User> getAllByUserName(String userName);
	
	
	/* admin */
	public List<User> getAllAdmins();
	public User addAdmin(User user);
	
	/*  manager */
	public List<User> getAllManagers();
	public User addManager(User user);
	
	/* employee */
	public List<User> getAllEmployees();
	public User addEmployee(User user);
	
	
	/* Cast error */
	public String castError(User user);
	
	
	/* PageSize */
	public Page<User> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
	
	public Page<User> getAllByUserTypeWithPagination(Integer pageNo, Integer pageSize, String sortBy, String typeName);
	
	public Page<User> getAllByUserNameWithPagination(Integer pageNo, Integer pageSize, String sortBy, String userName);
	
	
	/* Create default value */
	public void createDefaultAdmin() throws Exception;
	
}
