package com.hcl.user.controller;

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
import com.hcl.user.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	@Mock
	UserServiceImpl userServiceImpl;

	@InjectMocks
	UserController userController;

	static Credentials credentials;

	@BeforeAll
	public static void setUp() {

		credentials = new Credentials();
		credentials.setPassword("123456");
		credentials.setUserName("pavan");

	}

	@Test
	@DisplayName("User Login: positive scenerio")
	public void loginTest() throws InvalidCredentialsException {

		when(userServiceImpl.login(credentials)).thenReturn("Login Succsessfull");

		String result = userServiceImpl.login(credentials);

		assertEquals("Login Succsessfull", result.toString());

	}

	@Test
	@DisplayName("User Login: Negative Scenario")
	public void loginTestN() throws InvalidCredentialsException {
		when(userServiceImpl.login(credentials)).thenThrow(InvalidCredentialsException.class);
		assertThrows(InvalidCredentialsException.class, () -> userController.login(credentials));
	}

}
