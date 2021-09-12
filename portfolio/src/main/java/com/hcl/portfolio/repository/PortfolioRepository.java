package com.hcl.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.portfolio.model.Portfolio;


@Repository
public interface PortfolioRepository  extends JpaRepository<Portfolio,Integer>  {

}
