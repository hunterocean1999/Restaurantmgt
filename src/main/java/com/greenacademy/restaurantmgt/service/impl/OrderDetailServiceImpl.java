package com.greenacademy.restaurantmgt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.OrderDetail;
import com.greenacademy.restaurantmgt.repository.OrderDetailRepository;
import com.greenacademy.restaurantmgt.service.OrderDetailService;

@Transactional
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

	@Autowired
	private OrderDetailRepository orderdetailRepository;

	
	@Override
	public List<OrderDetail> getAll() {
		return  orderdetailRepository.findAll();
	}

	
	@Override
	public OrderDetail get(Long id) {
		return orderdetailRepository.getOne(id);
	}

	
	@Override
	public OrderDetail add(OrderDetail u) {
		return orderdetailRepository.save(u);
	}

	
	@Override
	public OrderDetail update(OrderDetail u) {
		return orderdetailRepository.save(u);
	}

	
	@Override
	public void delete(OrderDetail u) {
		orderdetailRepository.delete(u);
	}

	
	@Override
	public void delete(Long id) {
		OrderDetail u = orderdetailRepository.getOne(id);
		orderdetailRepository.delete(u);
	}

	
	@Override
	public List<OrderDetail> getAllbyOrderId(Long id) {
		return orderdetailRepository.getAllbyOrderId(id);
	}	
}
