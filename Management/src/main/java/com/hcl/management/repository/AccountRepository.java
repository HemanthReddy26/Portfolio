package com.hcl.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.management.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
	
	public List<Account> findAccountByUserId(int userId);

}
