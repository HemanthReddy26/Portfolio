package com.hcl.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.management.dto.AccountResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.service.IAccountService;
import com.hcl.user.exception.UserNotFoundException;

@RestController
public class AccountController {
	
	@Autowired
	IAccountService iAccountService;
	
	@GetMapping("/users/{userId}/accounts")
	public ResponseEntity<List<AccountResponseDto>> findByUserId(@PathVariable int userId) throws ManagementException, UserNotFoundException{
		return new ResponseEntity<List<AccountResponseDto>>(iAccountService.findByUserId(userId),HttpStatus.OK);
	}

}
