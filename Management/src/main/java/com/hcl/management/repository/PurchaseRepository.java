package com.hcl.management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.management.model.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	
	public List<Purchase> findPurchaseByAccountAccountId(int accountId);

}
