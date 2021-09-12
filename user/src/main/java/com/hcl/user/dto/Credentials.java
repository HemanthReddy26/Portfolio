package com.hcl.user.dto;

import javax.validation.constraints.NotEmpty;

public class Credentials {
    
	@NotEmpty(message="userName shouldn't be empty")
	private String userName;
    
	@NotEmpty(message="password shouldn't be empty")
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Credentials(String userName, String password) {

		this.userName = userName;
		this.password = password;
	}

	public Credentials() {

	}

}
