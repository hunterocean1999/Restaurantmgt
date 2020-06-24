package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.UserType;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
	
}
