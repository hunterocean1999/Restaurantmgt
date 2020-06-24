package com.greenacademy.restaurantmgt.model;

public class CartLineInfo {
	private ProductInfo productInfo;
	private Integer quantity;
	
	
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(int i) {
		this.quantity = i;
	}

	
	public double getAmount() {
		return this.productInfo.getGiasp() * this.quantity;
	}
}
