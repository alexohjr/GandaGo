package com.ezenit.gandago.order.models;

public class OrderListDTO {
	
	private String strName;
	private String strImage;
	private String menuName;
	private int menuAmount;
	private int orderPrice;
	private String orderDate;
	
	public OrderListDTO() {}
	
	public OrderListDTO(String strName, String strImage, String menuName, int menuAmount, int orderPrice,
			String orderDate) {
		this.strName = strName;
		this.strImage = strImage;
		this.menuName = menuName;
		this.menuAmount = menuAmount;
		this.orderPrice = orderPrice;
		this.orderDate = orderDate;
	}
	
	public String getStrName() {
		return strName;
	}
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String getStrImage() {
		return strImage;
	}
	public void setStrImage(String strImage) {
		this.strImage = strImage;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
		return "OrderListDTO [strName=" + strName + ", strImage=" + strImage + ", menuName=" + menuName
				+ ", menuAmount=" + menuAmount + ", orderPrice=" + orderPrice + ", orderDate=" + orderDate + "]";
	}

}
