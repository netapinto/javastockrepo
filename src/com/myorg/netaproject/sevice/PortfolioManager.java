package com.myorg.netaproject.sevice;


import java.util.Calendar;

import com.myorg.netaproject.model.Portfolio;
import com.myorg.netaproject.model.Stock;

/** PortfolioManager class is a program that manage the portfolio by
 * setting the date and the other details of the stock.
 */

public class PortfolioManager {

		Portfolio myPortfolio;
			
		/**************getPortfolio*******************
		 * @return myPortfolio */
		public Portfolio getPortfolio(String title){
		Portfolio myPortfolio = new Portfolio (title);
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		
		/**************setting the date**************/
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 12, 15);
		java.util.Date date1 = cal.getTime();
	
		cal.set(2014, 12, 15);
		java.util.Date date2 = cal.getTime();
		
		cal.set(2014, 12, 15);
		java.util.Date date3 = cal.getTime();
		
		/**********insert the details to new stocks**********/
		Stock s1 = new Stock ("PIH", 10f, 8.5f, date1, 20);
		Stock s2 = new Stock ("AAL", 30.0f, 25.5f, date2, 30);
		Stock s3 = new Stock ("CAAS", 20.0f, 15.5f, date3, 40);
		
		/*******buy the stocks to myPorfolio*******/
		myPortfolio.buyStock(s1, 20);
		myPortfolio.buyStock(s2, 30);
		myPortfolio.buyStock(s3, 40);
		
		
		/*******sell the stocks of myPorfolio*******/
		myPortfolio.sellStock("AAL", (-1));
		
		/*******remove the stock from myPorfolio*******/
		myPortfolio.removeStock("CAAS");
		
		/**@return myPortfolio */
		return myPortfolio;
			
		}
	
}



