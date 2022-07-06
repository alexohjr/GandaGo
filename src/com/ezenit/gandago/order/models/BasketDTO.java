package com.ezenit.gandago.order.models;

public class BasketDTO {
	
	private int strNum;
	private String strName;
	private int strMinOrder;
	private int menuNum;
	private String menuName;
	private String menuDetail;
	private int menuAmount;
	private int menuPrice; 

	public BasketDTO() {}

	public BasketDTO(int strNum, String strName, int strMinOrder, int menuNum, String menuName, String menuDetail,
			int menuAmount, int menuPrice) {
		this.strNum = strNum;
		this.strName = strName;
		this.strMinOrder = strMinOrder;
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.menuDetail = menuDetail;
		this.menuAmount = menuAmount;
		this.menuPrice = menuPrice;
	}

	public int getStrNum() {
		return strNum;
	}

	public void setStrNum(int strNum) {
		this.strNum = strNum;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public int getStrMinOrder() {
		return strMinOrder;
	}

	public void setStrMinOrder(int strMinOrder) {
		this.strMinOrder = strMinOrder;
	}

	public int getMenuNum() {
		return menuNum;
	}

	public void setMenuNum(int menuNum) {
		this.menuNum = menuNum;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDetail() {
		return menuDetail;
	}

	public void setMenuDetail(String menuDetail) {
		this.menuDetail = menuDetail;
	}

	public int getMenuAmount() {
		return menuAmount;
	}

	public void setMenuAmount(int menuAmount) {
		this.menuAmount = menuAmount;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	@Override
	public String toString() {
		return "BasketDTO [strNum=" + strNum + ", strName=" + strName + ", strMinOrder=" + strMinOrder + ", menuNum="
				+ menuNum + ", menuName=" + menuName + ", menuDetail=" + menuDetail + ", menuAmount=" + menuAmount
				+ ", menuPrice=" + menuPrice + "]";
	}
	
}
