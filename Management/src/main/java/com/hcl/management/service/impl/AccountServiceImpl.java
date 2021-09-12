package com.hcl.management.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.management.dto.AccountResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.feignclient.UserConnect;
import com.hcl.management.model.Account;
import com.hcl.management.repository.AccountRepository;
import com.hcl.management.service.IAccountService;
import com.hcl.user.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountServiceImpl implements IAccountService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	UserConnect userConnect;

	@Override
	public List<AccountResponseDto> findByUserId(int userId) throws ManagementException, UserNotFoundException {
		log.info("Calling findByUserId method in AccountService");
		userConnect.findById(userId);
		List<Account> accountList=accountRepository.findAccountByUserId(userId);
		if(accountList.isEmpty()) {
			log.warn("No accounts found exception");
			throw new ManagementException("No accounts for this userId");
		}
		log.info("Fetched all account details successfully");	
		return IAccountService.convertAccountListToAccountResponseDtoList(accountList);
	}
	
	public AccountResponseDto findByAccountId(int accountId) throws ManagementException {
		log.info("Calling findByAccountId method in AccountService");
		if(!accountRepository.findById(accountId).isPresent()) {
			log.warn("No such account exists");
			throw new ManagementException("Account Id doesn't exists");
		}
		AccountResponseDto accountResponseDto=new AccountResponseDto();
		BeanUtils.copyProperties(accountRepository.getById(accountId),accountResponseDto);
		log.info("Fetched account details successfully");
		return accountResponseDto;
		
	}
	

}
