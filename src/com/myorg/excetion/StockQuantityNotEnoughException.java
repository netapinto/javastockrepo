package com.myorg.excetion;

import org.algo.exception.PortfolioException;

public class StockQuantityNotEnoughException extends PortfolioException {

		public StockQuantityNotEnoughException() {
		
		super("There is not enough quantity of stocks to sell");
	}

}
