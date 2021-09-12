package com.hcl.management.exception;

public class ManagementException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ManagementException(String message) {
		super();
		this.message = message;
	}
	
	

}
