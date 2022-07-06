package com.ezenit.gandago.store.models;

public class StoreDTO {
	
	private int strNum;
	private String strName;
	private String strGroup;
	private String strAddr;
	private int strMinOrder;
	private String strTime;
	private String strPhone;
	private String strImage;
	private String strBigImage;
	
	public StoreDTO() {}

	public StoreDTO(int strNum, String strName, String strGroup, String strAddr, int strMinOrder, String strTime,
			String strPhone, String strImage, String strBigImage) {
		this.strNum = strNum;
		this.strName = strName;
		this.strGroup = strGroup;
		this.strAddr = strAddr;
		this.strMinOrder = strMinOrder;
		this.strTime = strTime;
		this.strPhone = strPhone;
		this.strImage = strImage;
		this.strBigImage = strBigImage;
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

	public String getStrGroup() {
		return strGroup;
	}

	public void setStrGroup(String strGroup) {
		this.strGroup = strGroup;
	}

	public String getStrAddr() {
		return strAddr;
	}

	public void setStrAddr(String strAddr) {
		this.strAddr = strAddr;
	}

	public int getStrMinOrder() {
		return strMinOrder;
	}

	public void setStrMinOrder(int strMinOrder) {
		this.strMinOrder = strMinOrder;
	}

	public String getStrTime() {
		return strTime;
	}

	public void setStrTime(String strTime) {
		this.strTime = strTime;
	}

	public String getStrPhone() {
		return strPhone;
	}

	public void setStrPhone(String strPhone) {
		this.strPhone = strPhone;
	}

	public String getStrImage() {
		return strImage;
	}

	public void setStrImage(String strImage) {
		this.strImage = strImage;
	}

	public String getStrBigImage() {
		return strBigImage;
	}

	public void setStrBigImage(String strBigImage) {
		this.strBigImage = strBigImage;
	}

	@Override
	public String toString() {
		return "StoreDTO [strNum=" + strNum + ", strName=" + strName + ", strGroup=" + strGroup + ", strAddr=" + strAddr
				+ ", strMinOrder=" + strMinOrder + ", strTime=" + strTime + ", strPhone=" + strPhone + ", strImage="
				+ strImage + ", strBigImage=" + strBigImage + "]";
	}

	
}
