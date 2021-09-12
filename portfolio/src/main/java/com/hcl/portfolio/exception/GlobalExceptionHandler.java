package com.hcl.portfolio.exception;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(PortfolioNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(PortfolioNotFoundException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(567);
		errorResponse.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(MethodArgumentNotValidException ex) {

		ValidationErrorResponse errorResponse = new ValidationErrorResponse();
		errorResponse.setMessage("Input Data is Invalid");
		errorResponse.setDateTime(LocalDateTime.now());
		errorResponse.setStatusCode(400);

		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		errors.forEach(error -> {
			errorResponse.getErrorsMap().put(error.getField(), error.getDefaultMessage());
		});

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(ConstraintViolationException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setStatusCode(400);
		errorResponse.setDateTime(LocalDateTime.now());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);
	}

}
