package com.greenacademy.restaurantmgt.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greenacademy.restaurantmgt.entities.Permission;
import com.greenacademy.restaurantmgt.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserServiceImpl userService;

	public final static String ROLE_PREFIX = "ROLE_";// Spring Security 4

	public final static String PERMISSION_AUTHENTICATED = "AUTHENTICATED";

	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userService.getByUserName(userName);
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		try {
			user = userService.getByUserName(userName);

			if (user == null) {
				return null;
			}

			GrantedAuthority role = new SimpleGrantedAuthority(ROLE_PREFIX + PERMISSION_AUTHENTICATED);// required to login																					
			authorities.add(role);
			
			GrantedAuthority roleByType = new SimpleGrantedAuthority(ROLE_PREFIX + user.getUserType().getTypeName());																					
			authorities.add(roleByType);
			
			if (user.getPermission() != null && user.getPermission().size() > 0) {
				List<Permission> permissions = user.getPermission();
				for (Permission permission : permissions) {
					GrantedAuthority auth = new SimpleGrantedAuthority(ROLE_PREFIX + permission.getPermissionName());
					authorities.add(auth);
				}
			}	
		} catch (Exception e) {
			throw new UsernameNotFoundException("User not found.");
		}

		org.springframework.security.core.userdetails.User secUser = new org.springframework.security.core.userdetails.User(
				userName, user.getPassword(), user.getIsActive(), true, true, true, authorities);
		return secUser;
	}
}