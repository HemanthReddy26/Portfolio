package com.hcl.user.service;

import com.hcl.user.dto.Credentials;
import com.hcl.user.dto.UserResponseDto;
import com.hcl.user.exception.InvalidCredentialsException;
import com.hcl.user.exception.UserNotFoundException;

public interface IUserService {
	
	public String login(Credentials credentials) throws InvalidCredentialsException;

	public UserResponseDto findById(int userId) throws UserNotFoundException;
}
