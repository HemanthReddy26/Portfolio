package com.hcl.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioDetailsResponseDto {
	
	private PurchaseResponseDto purchaseResponseDto;
	
	private double currentPrice;

}
