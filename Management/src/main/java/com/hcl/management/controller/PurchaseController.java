package com.hcl.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.management.dto.PortfolioDetailsResponseDto;
import com.hcl.management.dto.PurchaseRequestDto;
import com.hcl.management.dto.PurchaseResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.service.IPurchaseService;
import com.hcl.portfolio.exception.PortfolioNotFoundException;

@RestController
public class PurchaseController {
	
	@Autowired
	IPurchaseService iPurchaseService;
	
	@PostMapping("/portfolios/purchase")
	public ResponseEntity<String> purchase(@RequestBody PurchaseRequestDto purchaseRequestDto) throws ManagementException, PortfolioNotFoundException {
		return new ResponseEntity<String>(iPurchaseService.purchase(purchaseRequestDto),HttpStatus.OK);	
		
	}
	
	@GetMapping("/portfolios/account/{accountId}/purchases")
	public ResponseEntity<List<PurchaseResponseDto>> getByAccountId(@PathVariable int accountId) throws ManagementException{
		return new ResponseEntity<List<PurchaseResponseDto>>(iPurchaseService.getByAccountId(accountId),HttpStatus.OK);
	}
	
	@GetMapping("/portfolio/purchase/{purchasedId}")
	public ResponseEntity< PortfolioDetailsResponseDto> getById(@PathVariable int purchasedId) throws ManagementException, PortfolioNotFoundException{
		return new ResponseEntity< PortfolioDetailsResponseDto>(iPurchaseService.getById(purchasedId),HttpStatus.OK);
		
	}

}
