package com.hcl.managemnet.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hcl.management.dto.AccountResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.model.Account;
import com.hcl.management.repository.AccountRepository;
import com.hcl.management.service.impl.AccountServiceImpl;
import com.hcl.user.exception.UserNotFoundException;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTests {

	@Mock
	AccountRepository accountRepository;

	@InjectMocks
	AccountServiceImpl accountservice;

	static AccountResponseDto AccountResponseDto;
	static Account account;
	static List<Account> accounts = new ArrayList<Account>();
	static List<AccountResponseDto> accountsdto = new ArrayList<AccountResponseDto>();

	@BeforeAll
	public static void setup() {
		account = new Account();
		account.setAccountId(1);
		account.setAccountNo(1234L);
		account.setAccountType("CA");
		account.setBalance(0.0);
		accounts.add(account);

		AccountResponseDto = new AccountResponseDto();
		AccountResponseDto.setAccountNo(1L);
		AccountResponseDto.setAccountType("SA");
		AccountResponseDto.setBalance(12.0);

		accountsdto.add(AccountResponseDto);
	}

	@Test
	@DisplayName("Get All Accounts : Positive Scenerio")
	public void accounts1() throws ManagementException, UserNotFoundException {

		// context
		when(accountRepository.findAccountByUserId(1)).thenReturn(accounts);

		// event
		List<AccountResponseDto> accountlist = accountservice.findByUserId(1);

		// outcome
		assertEquals(accounts.size(), accountlist.size());
	}

	@Test
	@DisplayName("Get All Accounts : Negative Scenerio")
	public void accounts2() throws ManagementException {

		// context
		when(accountRepository.findAccountByUserId(1)).thenReturn(new ArrayList<Account>());

		// outcome
		assertThrows(ManagementException.class, () -> accountservice.findByUserId(1));

	}

}
