package com.hcl.management.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="account")
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	
	@Column(name="account_no")
	private Long AccountNo;
	
	@Column(name="account_type")
	private String accountType;
	
	@Column(name="balance")
	private double balance;
	
	@Column(name="user_id")
	private int userId;

}
