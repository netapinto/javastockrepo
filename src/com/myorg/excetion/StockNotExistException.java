package com.myorg.excetion;

import org.algo.exception.PortfolioException;

import com.myorg.netaproject.model.Stock;

public class StockNotExistException extends PortfolioException {
	
	public StockNotExistException() {
		
		super("The stock is not exists");
	}

}
