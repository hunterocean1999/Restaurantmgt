package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.Order;
import com.greenacademy.restaurantmgt.model.CartInfo;

@Transactional
@Service
public interface OrderService {

	public List<Order> getAll();
	
	public Order get(Long id);
		
	public Order update(Order u);
	
	public void delete(Order u);
	
	public void delete(Long id);
	
	public Order addOrder(CartInfo cartInfo);
	
	public List <Order> search(java.sql.Date fromDate,java.sql.Date toDate);
	
	/* PageSize */
	public Page<Order> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
}
