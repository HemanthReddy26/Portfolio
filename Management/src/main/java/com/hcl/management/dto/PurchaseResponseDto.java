package com.hcl.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponseDto {

	private String name;

	private int quantity;
	
	private double price;
	
	private String dateTime;
}
