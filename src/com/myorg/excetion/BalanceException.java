package com.myorg.excetion;

import org.algo.exception.PortfolioException;

public class BalanceException extends PortfolioException{
	
	public BalanceException(float amount) {
		
		super("Balance can't be negetive, " + amount + " is too big.");
	}
	public BalanceException() {
		
		super("Balance can't be negetive, ");
	}
}
