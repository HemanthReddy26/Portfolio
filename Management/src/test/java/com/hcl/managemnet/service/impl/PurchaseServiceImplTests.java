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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.hcl.management.dto.PortfolioDetailsResponseDto;
import com.hcl.management.dto.PurchaseRequestDto;
import com.hcl.management.dto.PurchaseResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.feignclient.PortfolioConnect;
import com.hcl.management.model.Account;
import com.hcl.management.model.Purchase;
import com.hcl.management.repository.AccountRepository;
import com.hcl.management.repository.PurchaseRepository;
import com.hcl.management.service.impl.PurchaseServiceImpl;
import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.exception.PortfolioNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PurchaseServiceImplTests {

	@Mock
	PurchaseRepository purchaseRepository;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock
	PortfolioConnect portfolioConnect;
	
	@InjectMocks
	PurchaseServiceImpl purchaseServiceImpl;
	
	static Account account;
	
	static Purchase purchase;

	static PortfolioResponseDto portfolioResponseDto;
	
	static PurchaseRequestDto purchaseRequestDto;

	static PurchaseResponseDto purchaseResponseDto;
	
	static List<Purchase> purchaseList;

	static List<PurchaseResponseDto> purchaseResponseDtolist;

	static PortfolioDetailsResponseDto portfolioDetailsResponseDto;

	@BeforeAll
	public static void setUp() {
		
		account = new Account();
		account.setAccountId(1);
		account.setAccountNo(123456789L);
		account.setAccountType("SA");
		account.setBalance(20000);
		
		purchase=new Purchase();
		purchase.setId(1);
		purchase.setName("tata");
		purchase.setQuantity(5);
		purchase.setPrice(12000);
		purchase.setDateTime("25-06-2001");
		purchase.setAccount(account);
		
		purchaseList=new ArrayList<Purchase>();
		purchaseList.add(purchase);		
		
		portfolioResponseDto=new PortfolioResponseDto();
		portfolioResponseDto.setPortfolioId(1);
		portfolioResponseDto.setName("tata");
		portfolioResponseDto.setPrice(100);

		purchaseResponseDto = new PurchaseResponseDto();
		purchaseResponseDto.setName("tata");
		purchaseResponseDto.setPrice(12000);
		purchaseResponseDto.setDateTime("25-06-2001");
		purchaseResponseDto.setQuantity(5);

		purchaseRequestDto = new PurchaseRequestDto();
		purchaseRequestDto.setAccountId(1);
		purchaseRequestDto.setPortfolioId(1);
		purchaseRequestDto.setQuantity(10);

		purchaseResponseDtolist = new ArrayList<PurchaseResponseDto>();
		purchaseResponseDtolist.add(purchaseResponseDto);

		portfolioDetailsResponseDto = new PortfolioDetailsResponseDto();
		portfolioDetailsResponseDto.setCurrentPrice(100);
		portfolioDetailsResponseDto.setPurchaseResponseDto(purchaseResponseDto);

	}
	
	@Test
	@DisplayName("Purchase: positive scenerio")
	public void purchase() throws PortfolioNotFoundException, ManagementException {
		when(accountRepository.getById(purchaseRequestDto.getAccountId())).thenReturn(account);
		when(portfolioConnect.findById(purchaseRequestDto.getPortfolioId())).thenReturn(portfolioResponseDto);
		//when(purchaseRepository.save(purchase)).thenReturn(purchase);
		assertEquals("Purchased Successfully",purchaseServiceImpl.purchase(purchaseRequestDto));
	}
	
	@Test
	@DisplayName("Purchase: negative scenerio")
	public void purchase1() throws PortfolioNotFoundException, ManagementException {
		when(accountRepository.getById(purchaseRequestDto.getAccountId())).thenReturn(null);
		//when(portfolioConnect.findById(purchaseRequestDto.getPortfolioId())).thenReturn(portfolioResponseDto);
		//when(purchaseRepository.save(purchase)).thenReturn(purchase);
		assertThrows(ManagementException.class,()-> purchaseServiceImpl.purchase(purchaseRequestDto));
	}
	
	@Test
	@DisplayName("Purchase: negative scenerio")
	public void purchase2() throws PortfolioNotFoundException, ManagementException {
		when(accountRepository.getById(purchaseRequestDto.getAccountId())).thenReturn(account);
		when(portfolioConnect.findById(purchaseRequestDto.getPortfolioId())).thenThrow(PortfolioNotFoundException.class);
		//when(purchaseRepository.save(purchase)).thenReturn(purchase);
		assertThrows(PortfolioNotFoundException.class,()-> purchaseServiceImpl.purchase(purchaseRequestDto));
	}
	
	@Test
	@DisplayName("list of portfolios: positive scenerio")
	public void portfolioList() throws ManagementException {
		when(purchaseRepository.findPurchaseByAccountAccountId(1)).thenReturn(purchaseList);
		assertEquals(purchaseResponseDtolist.size(),purchaseServiceImpl.getByAccountId(1).size());
	}
	
	@Test
	@DisplayName("list of portfolios:negative scenerio")
	public void portfolioList1() throws ManagementException {
		when(purchaseRepository.findPurchaseByAccountAccountId(1)).thenReturn(new ArrayList<Purchase>());
		assertThrows(ManagementException.class,()-> purchaseServiceImpl.getByAccountId(1));
	}
	
	@Test
	@DisplayName("getPortfolioById:positive scenerio")
	public void getById() throws ManagementException, PortfolioNotFoundException {
		when(purchaseRepository.getById(1)).thenReturn(purchase);
		when(portfolioConnect.findByName(purchase.getName())).thenReturn(new ResponseEntity<PortfolioResponseDto>(portfolioResponseDto,HttpStatus.OK));
		assertEquals(portfolioDetailsResponseDto,purchaseServiceImpl.getById(1));
	}
	
	@Test
	@DisplayName("getPortfolioById:negative scenerio")
	public void getById1() throws ManagementException, PortfolioNotFoundException {
		when(purchaseRepository.getById(1)).thenReturn(null);
		assertThrows(ManagementException.class,()-> purchaseServiceImpl.getById(1));
	}
	
	@Test
	@DisplayName("getPortfolioById:negative scenerio")
	public void getById2() throws ManagementException, PortfolioNotFoundException {
		when(purchaseRepository.getById(1)).thenReturn(purchase);
		when(portfolioConnect.findByName(purchase.getName())).thenThrow(PortfolioNotFoundException.class);
		assertThrows(PortfolioNotFoundException.class,()-> purchaseServiceImpl.getById(1));
	}
	


}
