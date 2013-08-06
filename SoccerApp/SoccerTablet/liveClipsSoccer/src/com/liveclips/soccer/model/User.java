package com.liveclips.soccer.model;

import com.liveclips.soccer.commons.UserTypeEnum;


public class User {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private UserTypeEnum userType;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public UserTypeEnum getUserType() {
		return userType;
	}
	public void setUserType(UserTypeEnum userType) {
		this.userType = userType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
