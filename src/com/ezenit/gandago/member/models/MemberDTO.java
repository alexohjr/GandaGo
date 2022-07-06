package com.ezenit.gandago.member.models;

public class MemberDTO {
	
	private String userId;			// ���� ���̵�
	private String userPwd;			// ���� �н�����
	private String userName;		// ���� �̸�
	private String userTel;			// ���� ����ó
	private String userBtd;			// ���� �������
	private String userAddr;			// ���� �ּ�
	
	public MemberDTO() {
	}

	public MemberDTO(String userId, String userPwd, String userName, String userTel, String userBtd, String userAddr) {
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.userTel = userTel;
		this.userBtd = userBtd;
		this.userAddr = userAddr;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserBtd() {
		return userBtd;
	}

	public void setUserBtd(String userBtd) {
		this.userBtd = userBtd;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	@Override
	public String toString() {
		return "MemberDTO [userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName + ", userTel="
				+ userTel + ", userBtd=" + userBtd + ", userAddr=" + userAddr + "]";
	}

}
