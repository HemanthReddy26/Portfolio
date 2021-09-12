package com.hcl.user.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.hcl.user.dto.Credentials;
import com.hcl.user.dto.UserResponseDto;
import com.hcl.user.exception.InvalidCredentialsException;
import com.hcl.user.exception.UserNotFoundException;
import com.hcl.user.service.IUserService;
import com.hcl.user.util.JwtUtil;

@RestController
public class UserController {

	@Autowired
	IUserService iUserService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/users/login")
	public ResponseEntity<String> login(@Valid @RequestBody Credentials credentials)
			throws InvalidCredentialsException {
		return new ResponseEntity<String>(iUserService.login(credentials), HttpStatus.OK);
	}

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable int userId) throws UserNotFoundException {
		return new ResponseEntity<UserResponseDto>(iUserService.findById(userId), HttpStatus.OK);
	}

	   @PostMapping("/authenticate")
	    public String generateToken(@RequestBody Credentials credentials) {
	            authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(credentials.getUserName(), credentials.getPassword()));
	        return jwtUtil.generateToken(credentials.getUserName());
	    }
}
