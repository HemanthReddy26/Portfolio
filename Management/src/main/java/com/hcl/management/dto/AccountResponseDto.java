package com.hcl.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

	private Long AccountNo;
	
	private String accountType;
	
	private double balance;



}
