package com.hcl.management.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.exception.PortfolioNotFoundException;

@FeignClient(name="portfolioservice",url="http://localhost:8091/")
public interface PortfolioConnect {
	
	@GetMapping("/portfolio/{portfolioId}")
	public PortfolioResponseDto findById(@PathVariable int portfolioId) throws PortfolioNotFoundException;
	
	@GetMapping("/portfolios/{portfolioName}")
	 public ResponseEntity<PortfolioResponseDto> findByName(@PathVariable String portfolioName) throws PortfolioNotFoundException;
}
