package com.hcl.management.exception;

public class ErrorResponse {

		
	private String message;
	private int statusCode;
	private String localDateTime;
	
	
	public ErrorResponse(String message, int statusCode, String localDateTime) {
		super();
		this.message = message;
		this.statusCode = statusCode;
		this.localDateTime = localDateTime;
	}
	
	
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(String localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	

}
