package com.hcl.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDto {
	
	private int portfolioId;
	
	private int accountId;
	
	private int quantity;

}
