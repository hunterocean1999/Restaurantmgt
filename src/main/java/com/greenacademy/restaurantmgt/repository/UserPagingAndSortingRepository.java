package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.User;

@Repository
public interface UserPagingAndSortingRepository extends PagingAndSortingRepository<User, Long> {
	@Query("SELECT u FROM User as u WHERE u.userType.typeName = :typeName")
	Page<User> findAllByUserType(@Param("typeName") String typeName, Pageable pageable);
	
	@Query("SELECT u FROM User as u WHERE u.userName = :userName")
	Page<User> findAllByUserName(@Param("userName") String userName, Pageable pageable);
}
