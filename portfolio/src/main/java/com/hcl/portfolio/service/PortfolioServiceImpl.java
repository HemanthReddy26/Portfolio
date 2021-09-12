package com.hcl.portfolio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.model.Portfolio;
import com.hcl.portfolio.repository.PortfolioRepository;

@Service
public class PortfolioServiceImpl implements PortfolioService{
	
	@Autowired
	PortfolioRepository portfolioRepository;

	@Override
	public Portfolio updatePortfolio(int portfolioId, PortfolioResponseDto portfolioResponseDto) {
	
		Portfolio port = portfolioRepository.findById(portfolioId).get();
		port.setName(portfolioResponseDto.getName());
		port.setTotalPrice(portfolioResponseDto.getTotalPrice());
		
		portfolioRepository.save(port);
		return port;
	}

}
