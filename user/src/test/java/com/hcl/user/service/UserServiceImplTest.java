package com.hcl.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcl.user.dto.Credentials;
import com.hcl.user.exception.InvalidCredentialsException;
import com.hcl.user.model.User;
import com.hcl.user.repository.UserRepository;
import com.hcl.user.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
	
	@InjectMocks
	UserServiceImpl userServiceImpl;
	
	
	@Mock
	UserRepository userRepository;
	
	static User user;
	
	static Credentials credentials;
	
	
	@BeforeAll
	public static void setUp() {
		user=new User();
		user.setUserName("pavan");
		user.setPassword("123456");
		
		credentials= new Credentials();
		credentials.setUserName("pavan");
		credentials.setPassword("123456");
				
		}
	
	@Test
	@DisplayName("Login:Positive Scenario")
	public void loginTest() throws InvalidCredentialsException {
		when(userRepository.findByUserNameAndPassword("pavan", "123456")).thenReturn(user);
		
		String r=userServiceImpl.login(credentials);
		assertEquals("Login Success", r);
	}
	
	
	@Test
	@DisplayName("Login:Negative Scenario")
	public void loginTestN() {
		when(userRepository.findByUserNameAndPassword("pavan", "123456")).thenReturn(null);
		
		
		assertThrows(InvalidCredentialsException.class, () -> userServiceImpl.login(credentials));
	}
}
