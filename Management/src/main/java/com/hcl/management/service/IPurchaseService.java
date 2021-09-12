package com.hcl.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.hcl.management.dto.PortfolioDetailsResponseDto;
import com.hcl.management.dto.PurchaseRequestDto;
import com.hcl.management.dto.PurchaseResponseDto;
import com.hcl.management.exception.ManagementException;
import com.hcl.management.model.Purchase;
import com.hcl.portfolio.exception.PortfolioNotFoundException;

public interface IPurchaseService {

	public String purchase(PurchaseRequestDto purchaseRequestDto)throws ManagementException, PortfolioNotFoundException;
	
	public List<PurchaseResponseDto> getByAccountId(int accountId) throws ManagementException;
	
	public PortfolioDetailsResponseDto getById(int purchasedId) throws ManagementException, PortfolioNotFoundException;
	
	// Converting Purchase to PurchaseResponseDto
		public static PurchaseResponseDto convertPurchaseToPurchaseResponseDto(Purchase purchase) {
				PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
				BeanUtils.copyProperties(purchase, purchaseResponseDto);
				
			return purchaseResponseDto;
		}

	// Converting PurchaseList to PurchaseResponseDtoList
	public static List<PurchaseResponseDto> convertPurchaseListToPurchaseResponseDtoList(List<Purchase> purchaseList) {
		List<PurchaseResponseDto> purchaseResponseDtoList = new ArrayList<PurchaseResponseDto>();
		purchaseList.forEach(purchase -> {
			PurchaseResponseDto purchaseResponseDto = new PurchaseResponseDto();
			BeanUtils.copyProperties(purchase, purchaseResponseDto);
			purchaseResponseDtoList.add(purchaseResponseDto);
		});

		return purchaseResponseDtoList;
	}
}
