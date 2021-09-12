package com.hcl.portfolio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.portfolio.dto.PortfolioResponseDto;
import com.hcl.portfolio.exception.PortfolioNotFoundException;
import com.hcl.portfolio.model.Portfolio;
import com.hcl.portfolio.service.PortfolioService;

@RestController
public class PortfolioController {
	
	@Autowired
	PortfolioService portfolioservice;
	

	 @PutMapping({"/portfolios/{id}"})
	    public ResponseEntity<Portfolio> updateUser(@PathVariable("id") int id, @RequestBody PortfolioResponseDto portfolioResponseDto)
	    		throws PortfolioNotFoundException {
	     if(id==0) {   
		 throw new PortfolioNotFoundException("portfolio not found");
	     }
	     Portfolio s1= portfolioservice.updatePortfolio(id, portfolioResponseDto);
	        return new ResponseEntity<Portfolio>(s1, HttpStatus.OK);

}
}
