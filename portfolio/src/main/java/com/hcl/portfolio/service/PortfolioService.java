package com.hcl.portfolio.service;

import org.springframework.stereotype.Service;

import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.model.Portfolio;

@Service
public interface PortfolioService {
	
	public Portfolio updatePortfolio(int portfolioId, PortfolioResponseDto portfoliodto);

}
