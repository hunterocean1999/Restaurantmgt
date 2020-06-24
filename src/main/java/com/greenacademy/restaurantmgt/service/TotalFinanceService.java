package com.greenacademy.restaurantmgt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.TotalFinance;

@Transactional
@Service
public interface TotalFinanceService {
	public List<TotalFinance> getAll();
	
	public TotalFinance get(Long id);
	
	public TotalFinance add(TotalFinance totalFinance);
	
	public TotalFinance update(TotalFinance totalFinance);
	
	public void delete(TotalFinance totalFinance);
	
	public void delete(Long id);
	
	public void addOrderAmountToRevenue(TotalFinance totalFinance, double amount);
	
	public void addIngredientCostToExpense(TotalFinance totalFinance, double cost);
	
	public void calculateProfit(TotalFinance totalFinance);
	
	
	/* PageSize */
	public Page<TotalFinance> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy);
}
