package com.myorg.netaproject.sevice;


import java.util.Calendar;
import com.myorg.netaproject.Stock;
import com.myorg.netaproject.model.Portfolio;


public class PortfolioManager {

	Portfolio portfolio;
	
	public Portfolio getPortfolio(){
	Portfolio portfolio = new Portfolio ();
	
	Calendar cal = Calendar.getInstance();
	cal.set(2014, 10, 15);
	java.util.Date date1 = cal.getTime();

	cal.set(2014, 10, 15);
	java.util.Date date2 = cal.getTime();
	
	cal.set(2014, 10, 15);
	java.util.Date date3 = cal.getTime();
	
	Stock s1 = new Stock ("PIH", 13.1f, 12.4f, date1);
	Stock s2 = new Stock ("AAL", 5.78f, 5.5f, date2);
	Stock s3 = new Stock ("CAAS", 32.2f, 31.5f, date3);
	
	portfolio.addStock(s1);
	portfolio.addStock(s2);
	portfolio.addStock(s3);
	
	return portfolio;
		
	}

}



