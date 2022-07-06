package com.ezenit.gandago.member.models;

public class MemberDTO {
	
	private String userId;			// 유저 아이디
	private String userPwd;			// 유저 패스워드
	private String userName;		// 유저 이름
	private String userTel;			// 유저 연락처
	private String userBtd;			// 유저 생년월일
	private String userAddr;			// 유저 주소
	
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
