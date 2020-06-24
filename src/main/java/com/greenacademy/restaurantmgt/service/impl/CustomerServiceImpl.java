package com.greenacademy.restaurantmgt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.Customer;
import com.greenacademy.restaurantmgt.model.CustomerInfo;
import com.greenacademy.restaurantmgt.repository.CustomerPagingAndSortingRepository;
import com.greenacademy.restaurantmgt.repository.CustomerRepository;
import com.greenacademy.restaurantmgt.service.CustomerService;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerPagingAndSortingRepository customerPagingAndSortingRepository;

	
	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}
	

	@Override
	public String castError(CustomerInfo customerInfo) {
		if (customerInfo.getFirstName() == null || customerInfo.getFirstName().isEmpty()) {
			return "Customer first name must not be empty";
		}

		if (customerInfo.getLastName() == null || customerInfo.getLastName().isEmpty()) {
			return "Customer last name must not be empty";
		}

		if (customerInfo.getStreet() == null || customerInfo.getStreet().isEmpty()) {
			return "Street address must not be empty";
		}

		if (customerInfo.getDistrict() == null || customerInfo.getDistrict().isEmpty()) {
			return "District address must not be empty";
		}

		if (customerInfo.getCity() == null || customerInfo.getCity().isEmpty()) {
			return "City must not be empty";
		}

		if (customerInfo.getEmail() == null || customerInfo.getEmail().isEmpty()) {
			return "Customer email must not be empty";
		}

		if (customerInfo.getNumberPhone() == null) {
			return "Customer phone number must not be empty";
		}
		
//		List<Customer> customerList = customerService.getAll();
//		for (Customer cus : customerList) {
//			if (cus.getPhone().equals(customerInfo.getNumberPhone())) {
//				return "Phone number already exist";
//			}
//		}

		return null;
	}

	
	@Override
	public Customer get(Long id) {
		return customerRepository.getOne(id);
	}

	
	@Override
	public Customer add(Customer customer) {
		return customerRepository.save(customer);
	}

	
	@Override
	public Customer update(Customer customer) {
		return customerRepository.save(customer);
	}

	
	@Override
	public void delete(Customer customer) {
		customerRepository.delete(customer);
	}

	
	@Override
	public void delete(Long id) {
		Customer customer = customerRepository.getOne(id);
		customerRepository.delete(customer);
	}

	
	@Override
	public Page<Customer> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		return customerPagingAndSortingRepository.findAll(paging);
	}

	
	@Override
	public Page<Customer> getAllByCustomerNumberPhoneWithPagination(Integer pageNo, Integer pageSize, String sortBy,
			Integer numberPhone) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
	
		return customerPagingAndSortingRepository.findAllByCustomerPhone(numberPhone, paging);
	}

}
