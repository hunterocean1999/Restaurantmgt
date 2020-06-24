package com.greenacademy.restaurantmgt.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greenacademy.restaurantmgt.entities.TotalFinance;
import com.greenacademy.restaurantmgt.repository.TotalFinancePagingAndSortingRepository;
import com.greenacademy.restaurantmgt.repository.TotalFinanceRepository;
import com.greenacademy.restaurantmgt.service.TotalFinanceService;

@Transactional
@Service
public class TotalFinanceServiceImpl implements TotalFinanceService {
	
	@Autowired
	private TotalFinanceRepository totalFinanceRepository;
	
	@Autowired
	private TotalFinancePagingAndSortingRepository  totalFinancePagingAndSortingRepository;
	
	
	@Override
	public List<TotalFinance> getAll() {
		return totalFinanceRepository.findAll();
	}

	@Override
	public TotalFinance get(Long id) {
		return totalFinanceRepository.getOne(id);
	}

	@Override
	public TotalFinance add(TotalFinance totalFinance) {
		return totalFinanceRepository.save(totalFinance);
	}

	@Override
	public TotalFinance update(TotalFinance totalFinance) {
		return totalFinanceRepository.save(totalFinance);
	}

	@Override
	public void delete(TotalFinance totalFinance) {
		totalFinanceRepository.delete(totalFinance);
	}

	@Override
	public void delete(Long id) {
		TotalFinance totalFinance = totalFinanceRepository.getOne(id);
		totalFinanceRepository.delete(totalFinance);
	}

	@Override
	public void addOrderAmountToRevenue(TotalFinance totalFinance, double amount) {
		double revenue = totalFinance.getRevenue();
		revenue += amount;
		totalFinance.setRevenue(revenue);
	}
	
	@Override
	public void addIngredientCostToExpense(TotalFinance totalFinance, double cost) {
		double expense = totalFinance.getExpense();
		expense += cost;
		totalFinance.setRevenue(expense);
	}

	@Override
	public void calculateProfit(TotalFinance totalFinance) {
		double revenue = totalFinance.getRevenue();
		double expense = totalFinance.getExpense();
		double profit = revenue - expense;
		totalFinance.setProfit(profit);
	}

	@Override
	public Page<TotalFinance> getAllWithPagination(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo,  pageSize, Sort.by(sortBy));
		return totalFinancePagingAndSortingRepository.findAll(paging);
	}
}
