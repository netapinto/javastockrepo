package com.myorg.excetion;

import org.algo.exception.PortfolioException;

import com.myorg.netaproject.model.Stock;

public class StockAlreadyExistsException extends PortfolioException {

	public StockAlreadyExistsException() {
		
		super("The stock is alreadt exists");
	}

}
