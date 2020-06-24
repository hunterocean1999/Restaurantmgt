package com.greenacademy.restaurantmgt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.FoodType;
import com.greenacademy.restaurantmgt.entities.User;
import com.greenacademy.restaurantmgt.entities.UserType;
import com.greenacademy.restaurantmgt.repository.FoodTypeRepository;
import com.greenacademy.restaurantmgt.repository.UserPagingAndSortingRepository;
import com.greenacademy.restaurantmgt.repository.UserRepository;
import com.greenacademy.restaurantmgt.repository.UserTypeRepository;
import com.greenacademy.restaurantmgt.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userReposity;
	
	@Autowired
	private UserTypeRepository userTypeReposity;
	
	@Autowired
	private UserPagingAndSortingRepository userPagingAndSortingRepository;
	
	@Autowired
	private FoodTypeRepository foodTypeRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private static final String DEFAULT_INITIAL_PASSWORD = "admin";
	

	@Override
	public List<User> getAll() {
		return userReposity.findAll();
	}

	
	@Override
	public User get(Long id) {
		return userReposity.getOne(id);
	}

	
	@Override
	public User add(User user) {
		return userReposity.save(user);
	}

	
	@Override
	public User update(User user) {
		return userReposity.save(user);
	}

	
	@Override
	public void delete(User user) {
		userReposity.delete(user);
	}

	
	@Override
	public void delete(Long id) {
		User user = userReposity.getOne(id);
		userReposity.delete(user);
	} 
	
	
	@Override
	public User getByUserName(String userName) {
		return userReposity.findByUserName(userName);
	}
	
	
	@Override
	public List<User> getAllByUserName(String userName){
		return userReposity.findAllByUserName(userName);
	}
	
	
	/*  admin */
	@Override
	public List<User> getAllAdmins(){
		return userReposity.findAllAdminUsers();
	}
	
	@Override
	public User addAdmin(User user) {
		UserType userType = userTypeReposity.getOne(1L);
		user.setUserType(userType);
		String password = user.getPassword();
		String passwordEncoded = passwordEncoder.encode(password);
		user.setPassword(passwordEncoded);
		user.setIsActive(true);
		return userReposity.save(user);
	}
	
	
	/*  manager */
	@Override
	public List<User> getAllManagers(){
		return userReposity.findAllManagers();
	}
	
	@Override
	public User addManager(User user) {
		UserType userType = userTypeReposity.getOne(2L);
		user.setUserType(userType);
		String password = user.getPassword();
		String passwordEncoded = passwordEncoder.encode(password);
		user.setPassword(passwordEncoded);
		user.setIsActive(true);
		return userReposity.save(user);
	}
	
	
	/* employee */
	@Override
	public List<User> getAllEmployees(){
		return userReposity.findAllAEmployees();
	}
	
	@Override
	public User addEmployee(User user) {
		UserType userType = userTypeReposity.getOne(3L);
		user.setUserType(userType);
		String password = user.getPassword();
		String passwordEncoded = passwordEncoder.encode(password);
		user.setPassword(passwordEncoded);
		user.setIsActive(true);
		return userReposity.save(user);
	}
	
	
	/* Cast error */
	@Override
	public String castError(User user) {
		if (user.getUserName() == null || user.getUserName().isEmpty()) {
			return "User name must not be empty";
		}
		
		if (user.getId() == null){
			List<User> userList = userReposity.findAll();
			for (User u : userList) {
				if (u.getUserName().equals(user.getUserName())) {
					return "User name already exist";
				}
			}
		}
		
		if (user.getPassword() == null || user.getPassword().isEmpty()) {
			return "Password must not be empty";
		}
		
		if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
			return "First name must not be empty";
		}
		
		if (user.getLastName() == null || user.getLastName().isEmpty()) {
			return "Last name must not be empty";
		}
		
		if (user.getAge() == null) {
			return "Age must not be empty";
		} 
		else if (user.getAge() < 0) {
			return "Age must not smaller than 0";
		}
		else if (user.getAge() == 0) {
			return "Age must not equal to 0";
		}
		
		if (user.getPhoneNumber() == null) {
			return "Phone number must not be empty";
		}
		
		if (user.getEmail() == null || user.getEmail().isEmpty()) {
			return "Email must not be empty";
		}
		
		return null;
	}
	
	
	/* Paging */
	@Override
	public Page<User> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo,  pageSize, Sort.by(sortBy));
		return userPagingAndSortingRepository.findAll(paging);
	}
	
	/* Paging sort by user type */
	@Override
	public Page<User> getAllByUserTypeWithPagination(Integer pageNo, Integer pageSize, String sortBy, String typeName) {
		Pageable paging = PageRequest.of(pageNo,  pageSize, Sort.by(sortBy));
		return userPagingAndSortingRepository.findAllByUserType(typeName, paging);
	}
	
	/* Paging sort by user name */
	@Override
	public Page<User> getAllByUserNameWithPagination(Integer pageNo, Integer pageSize, String sortBy, String userName) {
		Pageable paging = PageRequest.of(pageNo,  pageSize, Sort.by(sortBy));
		return userPagingAndSortingRepository.findAllByUserName(userName, paging);
	}
	
	
	/* Create default value */
	@Override
	public void createDefaultAdmin() throws Exception {
		String password = passwordEncoder.encode(DEFAULT_INITIAL_PASSWORD);
		// creation of the super admin admin:admin)
		 User user = new User();
																			
		 user.setUserName("admin");
		 user.setPassword(password);
		 user.setFirstName("Administrator");
		 user.setLastName("User");
		 user.setAge(20);
		 user.setPhoneNumber("123456789");
		 user.setEmail("admin@greenacedamy.com");
		 user.setAvatarName("P1.jpg");
		 user.setIsActive(true);
		 
		 UserType userType = new UserType();
		 userType.setTypeName("admin");
		 user.setUserType(userType);
		 
		 userReposity.save(user); 
		 
		 
		/* khoi tao user type */
		 userType = new UserType();
		 userType.setTypeName("manager");
		 userTypeReposity.save(userType);
		 
		 userType = new UserType();
		 userType.setTypeName("employee");
		 userTypeReposity.save(userType);
		 /* ----------------- */
		 
		 
		 /* khoi tao food type */
		 FoodType foodType = new FoodType();
		 foodType.setTypeName("food");
		 foodTypeRepository.save(foodType);
		 
		 foodType = new FoodType();
		 foodType.setTypeName("drink");
		 foodTypeRepository.save(foodType);
		 /* ----------------- */
	}	
}
