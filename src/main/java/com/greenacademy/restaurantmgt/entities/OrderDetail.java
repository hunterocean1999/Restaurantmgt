package com.greenacademy.restaurantmgt.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

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
@Table(name = "order_details", catalog = "restaurantmgt")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long   id;
	private int    quanity;
	private double price;
	private double amount;

	private Order  order;
	private Food   food;

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ORDER_DETAILS_ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name = "ORDER_DETAILS_QUANITY", length = 120)
	public int getQuanity() {
		return quanity;
	}
	public void setQuanity(int quanity) {
		this.quanity = quanity;
	}

	
	@Column(name = "ORDER_DETAILS_PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	
	@Column(name = "ORDER_DETAILS_AMOUNT", length = 120)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "ORDER_ID", nullable = true)
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "FOOD_ID", nullable = true)
	public Food getFood() {
		return food;
	}
	public void setFood(Food food) {
		this.food = food;
	}
}