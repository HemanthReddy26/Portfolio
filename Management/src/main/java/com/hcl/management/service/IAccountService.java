package com.hcl.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hcl.management.dto.AccountResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.model.Account;
import com.hcl.user.exception.UserNotFoundException;

public interface IAccountService {

	public List<AccountResponseDto> findByUserId(int userId) throws ManagementException, UserNotFoundException;
	
	public AccountResponseDto findByAccountId(int accountId) throws ManagementException;
	
	//Converting AccountList to AccountResponseDtoList
	public static List<AccountResponseDto> convertAccountListToAccountResponseDtoList(List<Account> accountList){
		List<AccountResponseDto> accountResponseDtoList=new ArrayList<AccountResponseDto>();
		accountList.forEach(account->{
			AccountResponseDto accountResponseDto=new AccountResponseDto();
			BeanUtils.copyProperties(account, accountResponseDto);
			accountResponseDtoList.add(accountResponseDto);
		});
		
		return accountResponseDtoList;
	}
}
