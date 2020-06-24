package com.greenacademy.restaurantmgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT DISTINCT u FROM User as u WHERE u.userName = :userName")
	User findByUserName(@Param("userName") String userName);
	
	@Query("SELECT u FROM User as u JOIN u.userType t WHERE t.id = 1")
	List<User> findAllAdminUsers();

	@Query("SELECT u FROM User as u JOIN u.userType t WHERE t.id = 2")
	List<User> findAllManagers();
	
	@Query("SELECT u FROM User as u JOIN u.userType t WHERE t.id = 3")
	List<User> findAllAEmployees();
	
	@Query("SELECT u FROM User as u WHERE u.userName = :userName")
	List<User> findAllByUserName(@Param("userName") String userName);
}