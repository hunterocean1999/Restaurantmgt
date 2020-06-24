package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.OrderDetail;

@Transactional
@Service
public interface OrderDetailService {

	public List<OrderDetail> getAll();

	public OrderDetail get(Long id);

	public OrderDetail add(OrderDetail u);

	public OrderDetail update(OrderDetail u);

	public void delete(OrderDetail u);

	public void delete(Long id);
	
	public List<OrderDetail> getAllbyOrderId(Long id);
}
