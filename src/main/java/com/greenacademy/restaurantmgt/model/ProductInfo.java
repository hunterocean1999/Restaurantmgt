package com.greenacademy.restaurantmgt.model;

import com.greenacademy.restaurantmgt.entities.Food;

public class ProductInfo {
	private Long   masp;
	private String tensp;
	private Double giasp;
	private String hinhanhsp;

	
	public ProductInfo() {
	}
	public ProductInfo(Food food) {
		this.masp = food.getId();
		this.tensp = food.getFoodName();
		this.giasp = food.getUnitPrice();
		this.hinhanhsp = food.getImage();
	}

	
	public ProductInfo(Long masp, String tensp, double giasp, String hinhanhsp) {
		this.masp = masp;
		this.tensp = tensp;
		this.giasp = giasp;
		this.hinhanhsp = hinhanhsp;
	}

	
	public Long getMasp() {
		return masp;
	}
	public void setMasp(Long masp) {
		this.masp = masp;
	}
	
	
	public String getTensp() {
		return tensp;
	}
	public void setTensp(String tensp) {
		this.tensp = tensp;
	}
	
	
	public double getGiasp() {
		return giasp;
	}
	public void setGiasp(double giasp) {
		this.giasp = giasp;
	}
	
	
	public String getHinhanhsp() {
		return hinhanhsp;
	}
	public void setHinhanhsp(String hinhanhsp) {
		this.hinhanhsp = hinhanhsp;
	}
}
