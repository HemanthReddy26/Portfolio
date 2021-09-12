package com.hcl.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.management.dto.PortfolioDetailsResponseDto;
import com.hcl.management.dto.PurchaseRequestDto;
import com.hcl.management.dto.PurchaseResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.feignclient.PortfolioConnect;
import com.hcl.management.model.Account;
import com.hcl.management.model.Purchase;
import com.hcl.management.repository.AccountRepository;
import com.hcl.management.repository.PurchaseRepository;
import com.hcl.management.service.IPurchaseService;
import com.hcl.management.util.DateTimeUtil;
import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.exception.PortfolioNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseServiceImpl implements IPurchaseService {
	
	@Autowired
	PortfolioConnect portfolioConnect;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	PurchaseRepository purchaseRepository;
	
	public String purchase(PurchaseRequestDto purchaseRequestDto) throws ManagementException, PortfolioNotFoundException {	
		Account account=accountRepository.getById(purchaseRequestDto.getAccountId());
		if(account==null) {
			log.warn("No such account exists");
			throw new ManagementException("Account Id doesn't exists");
		}
		
		PortfolioResponseDto portfolioResponseDto=portfolioConnect.findById(purchaseRequestDto.getPortfolioId());
		
		Purchase purchase=new Purchase();
		purchase.setName(portfolioResponseDto.getName());
		purchase.setQuantity(purchaseRequestDto.getQuantity());
		purchase.setPrice(portfolioResponseDto.getPrice());
		purchase.setDateTime(DateTimeUtil.dateTime());
		purchase.setAccount(account);
		log.info("saving the data in the database");
		purchaseRepository.save(purchase);
		log.info("data saved successfully");
		return "Purchased Successfully";
	}
	
	public List<PurchaseResponseDto> getByAccountId(int accountId) throws ManagementException{
		if(accountRepository.getById(accountId)==null) {
			throw new ManagementException("No such account id exists");
		}
		List<Purchase> purchaseList=purchaseRepository.findPurchaseByAccountAccountId(accountId);
		if(purchaseList.isEmpty()) {
			throw new ManagementException("No purchases for this account");
		}
		return IPurchaseService.convertPurchaseListToPurchaseResponseDtoList(purchaseList);
		
	}
	
	public PortfolioDetailsResponseDto getById(int purchasedId) throws ManagementException, PortfolioNotFoundException{
		Purchase purchase=purchaseRepository.getById(purchasedId);
		if(purchase==null) {
			throw new ManagementException("Purchase id doesn't exists");
		}
		PortfolioDetailsResponseDto portfolioDetailsResponseDto=new PortfolioDetailsResponseDto();
		portfolioDetailsResponseDto.setPurchaseResponseDto(IPurchaseService.convertPurchaseToPurchaseResponseDto(purchase));
		portfolioDetailsResponseDto.setCurrentPrice(portfolioConnect.findByName(purchase.getName()).getBody().getPrice());
		return portfolioDetailsResponseDto;
				
		
	}

}
