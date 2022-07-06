package com.ezenit.gandago.store.models;

public class MenuDTO {
	private int menuNum;
	private String menuName;
	private String menuInfo;
	private int menuPrice;
	private String menuImage;
	private int strNum;
	
	public MenuDTO() {}

	public MenuDTO(int menuNum, String menuName, String menuInfo, int menuPrice, String menuImage, int strNum) {
		this.menuNum = menuNum;
		this.menuName = menuName;
		this.menuInfo = menuInfo;
		this.menuPrice = menuPrice;
		this.menuImage = menuImage;
		this.strNum = strNum;
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

	public String getMenuInfo() {
		return menuInfo;
	}

	public void setMenuInfo(String menuInfo) {
		this.menuInfo = menuInfo;
	}

	public int getMenuPrice() {
		return menuPrice;
	}

	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}

	public String getMenuImage() {
		return menuImage;
	}

	public void setMenuImage(String menuImage) {
		this.menuImage = menuImage;
	}

	public int getStrNum() {
		return strNum;
	}

	public void setStrNum(int strNum) {
		this.strNum = strNum;
	}

	@Override
	public String toString() {
		return "MenuDTO [menuNum=" + menuNum + ", menuName=" + menuName + ", menuInfo=" + menuInfo + ", menuPrice="
				+ menuPrice + ", menuImage=" + menuImage + ", strNum=" + strNum + "]";
	}

}
