package com.hcl.management.feignclient;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.hcl.user.dto.Credentials;
import com.hcl.user.dto.UserResponseDto;
import com.hcl.user.exception.InvalidCredentialsException;
import com.hcl.user.exception.UserNotFoundException;

@FeignClient(name = "userservice", url = "http://localhost:8090/")
public interface UserConnect {

	@PostMapping("/users/login")
	public ResponseEntity<String> login(@Valid @RequestBody Credentials credentials) throws InvalidCredentialsException;

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserResponseDto> findById(@PathVariable int userId) throws UserNotFoundException;

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody Credentials credentials);
}
