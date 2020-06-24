package com.greenacademy.restaurantmgt.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.greenacademy.restaurantmgt.service.UserService;

@Component
public class InitializationLoader {
	@Autowired
	private UserService userService;

	@PostConstruct
	public void init() {
		try {
			if (userService.getByUserName("admin") == null) {
				userService.createDefaultAdmin();
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}