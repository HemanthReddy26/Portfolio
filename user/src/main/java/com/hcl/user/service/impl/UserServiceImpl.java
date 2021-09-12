package com.hcl.user.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hcl.user.dto.Credentials;
import com.hcl.user.dto.UserResponseDto;
import com.hcl.user.exception.InvalidCredentialsException;
import com.hcl.user.exception.UserNotFoundException;
import com.hcl.user.model.User;
import com.hcl.user.repository.UserRepository;
import com.hcl.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService,UserDetailsService {

	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public String login(Credentials credentials) throws InvalidCredentialsException {
		User user=userRepository.findByUserNameAndPassword(credentials.getUserName(),credentials.getPassword());
		if(user==null) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		return "Login Success";
	}

	@Override
	public UserResponseDto findById(int userId) throws UserNotFoundException {
		User user=userRepository.getById(userId);
		if(user==null) {
			throw new UserNotFoundException("UserId doesnt exists");
		}
		UserResponseDto userResponseDto=new UserResponseDto();
		BeanUtils.copyProperties(user, userResponseDto);
		return  userResponseDto;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

}
