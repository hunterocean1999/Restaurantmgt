package com.greenacademy.restaurantmgt.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "orders", catalog ="restaurantmgt")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long   id;
	private Date   orderDate;
	private double amount;
	private int    status = 0;
	
	private Customer customer;
	private TotalFinance totalFinance;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ORDER_ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	@Column(name = "ORDER_DATE", length = 120)
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date date) {
		this.orderDate = date;
	}

	
	@Column(name = "ORDER_AMOUNT", length = 120)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	@Column(name = "ORDER_STATUS", length = 120)
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CUSTOMER_ID", nullable = true)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TOTAL_FINANCE_ID", nullable = true)
	public TotalFinance getTotalFinance() {
		return totalFinance;
	}
	public void setTotalFinance(TotalFinance totalFinance) {
		this.totalFinance = totalFinance;
	}
	
}