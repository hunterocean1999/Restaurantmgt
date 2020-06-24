package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.Customer;
import com.greenacademy.restaurantmgt.model.CustomerInfo;

@Transactional
@Service
public interface CustomerService {
	
	public List<Customer> getAll();
	
	public Customer get(Long id);
	
	public Customer add(Customer customer);
	
	public Customer update(Customer customer);
	
	public void delete(Customer customer);
	
	public void delete(Long id);
	
	public String castError(CustomerInfo customerInfo);
	
	
	/* PageSize */
	public Page<Customer> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
	
	public Page<Customer> getAllByCustomerNumberPhoneWithPagination(Integer pageNo, Integer pageSize, String sortBy, Integer customerName);
}
