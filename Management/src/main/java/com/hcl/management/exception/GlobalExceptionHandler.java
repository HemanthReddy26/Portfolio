package com.hcl.management.exception;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hcl.management.util.DateTimeUtil;
import com.hcl.portfolio.exception.PortfolioNotFoundException;
import com.hcl.user.exception.InvalidCredentialsException;

import feign.FeignException;


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ManagementException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ManagementException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(401);
		errorResponse.setLocalDateTime(DateTimeUtil.dateTime());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex) {
		
		ValidationErrorResponse errorResponse = new ValidationErrorResponse();
		errorResponse.setMessage("Input Data is Invalid");
		errorResponse.setLocalDateTime(DateTimeUtil.dateTime());
		errorResponse.setStatusCode(com.hcl.management.ApiStatusCode.INVALID_DATA);
		
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		System.out.println(errors);
		errors.forEach(error -> {
			errorResponse.getErrorsMap().put(error.getField(), error.getDefaultMessage());
		});
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ConstraintViolationException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(401);
		errorResponse.setLocalDateTime(DateTimeUtil.dateTime());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(PortfolioNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(PortfolioNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(401);
		errorResponse.setLocalDateTime(DateTimeUtil.dateTime());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(InvalidCredentialsException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("Invalid Credentials");
		errorResponse.setStatusCode(401);
		errorResponse.setLocalDateTime(DateTimeUtil.dateTime());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(FeignException ex) {
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.contentUTF8());
		errorResponse.setStatusCode(401);
		errorResponse.setLocalDateTime(DateTimeUtil.dateTime());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(EntityNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("No such data exists in databse");
		errorResponse.setStatusCode(401);
		errorResponse.setLocalDateTime(DateTimeUtil.dateTime());
		
		return new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.OK);
	}
	
}
