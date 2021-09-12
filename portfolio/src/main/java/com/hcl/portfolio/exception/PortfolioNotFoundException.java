package com.hcl.portfolio.exception;

public class PortfolioNotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String message;

	public PortfolioNotFoundException(String message) {
		super();
		this.message = message;
	}

	public PortfolioNotFoundException() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
