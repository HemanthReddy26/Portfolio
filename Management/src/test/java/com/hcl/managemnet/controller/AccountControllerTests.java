package com.hcl.managemnet.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.hcl.management.controller.AccountController;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.model.Account;
import com.hcl.management.service.impl.AccountServiceImpl;
import com.hcl.user.exception.UserNotFoundException;
import com.hcl.user.model.User;

@ExtendWith(MockitoExtension.class)
public class AccountControllerTests {

	@Mock
	AccountServiceImpl iAccountService;

	@InjectMocks
	AccountController accountController;

	static com.hcl.management.dto.AccountResponseDto AccountResponseDto;
	static Account account;
	static User user;
	static List<Account> accounts;
	static List<com.hcl.management.dto.AccountResponseDto> accountresponses;

	@BeforeAll
	public static void setup() {

		AccountResponseDto = new com.hcl.management.dto.AccountResponseDto();
		AccountResponseDto.setAccountNo((long) 1);
		AccountResponseDto.setAccountType("SA");
		AccountResponseDto.setBalance(12.0);

		// accountresponses.add(AccountResponseDto);

	}

	@Test
	@DisplayName("Get All Accounts : Positive Scenerio")
	public void accounts1() throws ManagementException, UserNotFoundException {

		// context
		when(iAccountService.findByUserId(1)).thenReturn(accountresponses);

		// event
		ResponseEntity<List<com.hcl.management.dto.AccountResponseDto>> result = accountController.findByUserId(1);

		// outcome
		assertEquals(result.getBody(), accountresponses);

	}

	@Test
	@DisplayName("Get All Accounts : Negative Scenerio")
	public void accounts2() throws ManagementException, UserNotFoundException {

		// context
		when(iAccountService.findByUserId(1)).thenThrow(ManagementException.class);

		// outcome
		assertThrows(ManagementException.class, () -> accountController.findByUserId(1));

	}

}
