package com.myorg.excetion;

import org.algo.exception.PortfolioException;

public class BalanceIsNotEnoughException extends PortfolioException{

		public BalanceIsNotEnoughException() {
		
		super("There is not enoughe money in balance for buy");
	}
}
