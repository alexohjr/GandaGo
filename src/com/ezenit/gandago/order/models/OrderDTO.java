package com.ezenit.gandago.order.models;

public class OrderDTO {
	
	private int strNum;
	private int menuNum;
	private int menuAmount;
	private int orderPrice;
	private String orderDate;

	public OrderDTO() {}
	
	public OrderDTO(int strNum, int menuNum, int menuAmount, int orderPrice, String orderDate) {
		this.strNum = strNum;
		this.menuNum = menuNum;
		this.menuAmount = menuAmount;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
	}

	public int getStrNum() {
		return strNum;
	}
	public void setStrNum(int strNum) {
		this.strNum = strNum;
	}
	public int getMenuNum() {
		return menuNum;
	}
	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}
	public int getMenuAmount() {
		return menuAmount;
	}
	public void setMenuAmount(int menuAmount) {
		this.menuAmount = menuAmount;
	}
	public int getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	@Override
	public String toString() {
		return "OrderDTO [strNum=" + strNum + ", menuNum=" + menuNum + ", menuAmount=" + menuAmount + ", orderPrice="
				+ orderPrice + ", orderDate=" + orderDate + "]";
	}

}
