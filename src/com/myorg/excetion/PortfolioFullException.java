package com.myorg.excetion;

import org.algo.exception.PortfolioException;

public class PortfolioFullException extends PortfolioException {
	
		public PortfolioFullException() {
		
		super("Stock can not be added, the portfolio is full");
	}

}
