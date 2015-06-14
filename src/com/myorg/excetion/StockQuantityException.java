package com.myorg.excetion;

import org.algo.exception.PortfolioException;

public class StockQuantityException extends PortfolioException{

	public StockQuantityException() {
		
		super("Error - Quantity negative number");
	}
}
