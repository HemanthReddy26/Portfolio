package com.hcl.managemnet.controller;

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
import org.springframework.http.ResponseEntity;

import com.hcl.management.controller.PurchaseController;
import com.hcl.management.dto.PortfolioDetailsResponseDto;
import com.hcl.management.dto.PurchaseRequestDto;
import com.hcl.management.dto.PurchaseResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.model.Account;
import com.hcl.management.service.impl.PurchaseServiceImpl;
import com.hcl.portfolio.exception.PortfolioNotFoundException;

@ExtendWith(MockitoExtension.class)
public class PurchaseControllerTests {

	@Mock
	PurchaseServiceImpl purchaseServiceImpl;

	@InjectMocks
	PurchaseController purchaseController;

	static Account account;

	static PurchaseRequestDto purchaseRequestDto;

	static PurchaseResponseDto purchaseResponseDto;

	static List<PurchaseResponseDto> purchaseResponseDtolist;

	static PortfolioDetailsResponseDto portfolioDetailsResponseDto;

	@BeforeAll
	public static void setUp() {

		account = new Account();
		account.setAccountId(1);
		account.setAccountNo(123456789L);
		account.setAccountType("SA");
		account.setBalance(20000);

		purchaseResponseDto = new PurchaseResponseDto();
		purchaseResponseDto.setName("HCL");
		purchaseResponseDto.setPrice(1000);
		purchaseResponseDto.setQuantity(100);

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
	@DisplayName("Portfolios Purchase:positive Scenario")
	public void purchaseTest() throws ManagementException, PortfolioNotFoundException {
		when(purchaseServiceImpl.purchase(purchaseRequestDto)).thenReturn("Purchased Successfully");
		String result1 = purchaseServiceImpl.purchase(purchaseRequestDto);
		assertEquals("Purchased Successfully", result1);

	}

	@Test
	@DisplayName("Portfolios Purchase:Negative Scenario")
	public void purchaseTestN() throws ManagementException, PortfolioNotFoundException {
		when(purchaseServiceImpl.purchase(purchaseRequestDto)).thenThrow(PortfolioNotFoundException.class);
		assertThrows(PortfolioNotFoundException.class, () -> purchaseController.purchase(purchaseRequestDto));

	}

	@Test
	@DisplayName("Portfolios Details:positive Scenario")
	public void getByAccountId() throws ManagementException {
		when(purchaseServiceImpl.getByAccountId(1)).thenReturn(purchaseResponseDtolist);
		ResponseEntity<List<PurchaseResponseDto>> result = purchaseController.getByAccountId(1);
		assertEquals(purchaseResponseDtolist.size(), result.getBody().size());

	}

	@Test
	@DisplayName("Portfolios Details:Negative Scenario")
	public void getByAccountIdN() throws ManagementException {

		when(purchaseServiceImpl.getByAccountId(1)).thenThrow(ManagementException.class);

		// when(purchaseServiceImpl.getByAccountId(1)).thenReturn(new
		// ArrayList<PurchaseResponseDto>());

		assertThrows(ManagementException.class, () -> purchaseController.getByAccountId(1));

	}

	@Test
	@DisplayName("Portfolio Fetch:positive Scenario")
	public void getByIdTest() throws ManagementException, PortfolioNotFoundException {
		when(purchaseServiceImpl.getById(1)).thenReturn(portfolioDetailsResponseDto);

		PortfolioDetailsResponseDto result1 = purchaseServiceImpl.getById(1);
		assertEquals(portfolioDetailsResponseDto, result1);

	}

	@Test
	@DisplayName("Portfolio Fetch:Negative Scenario")
	public void getByIdTestN() throws ManagementException, PortfolioNotFoundException {
		when(purchaseServiceImpl.getById(1)).thenThrow(PortfolioNotFoundException.class);

		assertThrows(PortfolioNotFoundException.class, () -> purchaseController.getById(1));

	}

}