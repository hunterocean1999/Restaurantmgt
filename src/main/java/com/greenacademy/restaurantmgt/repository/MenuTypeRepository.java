package com.greenacademy.restaurantmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.greenacademy.restaurantmgt.entities.MenuType;

@Repository
public interface MenuTypeRepository extends JpaRepository<MenuType, Long>{

}
