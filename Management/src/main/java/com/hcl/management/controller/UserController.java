package com.hcl.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.management.feignclient.UserConnect;
import com.hcl.user.dto.Credentials;
import com.hcl.user.exception.InvalidCredentialsException;

@RestController
public class UserController {
	
	@Autowired
	UserConnect userConnect;
	
	@PostMapping("/users/login")
	public ResponseEntity<String> login(@Valid @RequestBody Credentials credentials)  throws InvalidCredentialsException {
		 return userConnect.login(credentials);
	}
	
	@PostMapping("/authenticate")
	public String authenticate(@Valid @RequestBody Credentials credentials) {
		return userConnect.generateToken(credentials);
	}

}
