package com.greenacademy.restaurantmgt.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "total_finance", catalog ="restaurantmgt")
public class TotalFinance implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long   id;
	private Date   date;
	private double revenue;
	private double expense;
	private double profit;
		
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TOTAL_FINANCE_ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "TOTAL_FINANCE_DATE", length = 120)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	@Column(name = "TOTAL_FINANCE_REVENUE")
	public double getRevenue() {
		return revenue;
	}
	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
	
	@Column(name = "TOTAL_FINANCE_EXPENSE")
	public double getExpense() {
		return expense;
	}
	public void setExpense(double expense) {
		this.expense = expense;
	}
	
	
	@Column(name = "TOTAL_FINANCE_PROFIT")
	public double getProfit() {
		return profit;
	}
	public void setProfit(double profit) {
		this.profit = profit;
	}
}	
