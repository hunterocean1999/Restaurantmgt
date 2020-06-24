package com.greenacademy.restaurantmgt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.greenacademy.restaurantmgt.service.impl.UserDetailsServiceImpl;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	};

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	};

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//		.antMatchers("/", "/home/**","/error").hasRole("ADMIN")
//		.antMatchers("/views/studentList/**").access("hasRole('ADMIN')")
//		.antMatchers("/studentList/**").hasRole("ADMIN")
//		.anyRequest().authenticated()
//		.and().formLogin()
//		.and().logout().permitAll().logoutSuccessUrl("/login")
//		.and().csrf().disable();
		
//		.antMatchers("/", "/home/**","/error").permitAll()
//		.antMatchers("/").hasRole("admin")
		.antMatchers("/home/**","/error").permitAll()
		.antMatchers("/admin/**").hasRole("AUTHENTICATED")
		.anyRequest().authenticated()
		.and().formLogin()
		.and().logout().permitAll().logoutSuccessUrl("/login")
		.and().csrf().disable();
	}
}