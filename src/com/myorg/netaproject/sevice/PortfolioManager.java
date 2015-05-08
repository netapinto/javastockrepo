package com.myorg.netaproject.sevice;


import java.util.Calendar;

import com.myorg.netaproject.model.Portfolio;
import com.myorg.netaproject.model.Stock;

/** PortfolioManager class is a program that manage the portfolio by
 * setting the date and the other details of the stock.
 */

public class PortfolioManager {

		Portfolio portfolio1;
		
		/**************getPortfolio*******************
		 * @return portfolio number 2.*/
		public Portfolio getPortfolio(String title){
		Portfolio portfolio1 = new Portfolio (title);

		/**************setting the date**************/
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 10, 15);
		java.util.Date date1 = cal.getTime();
	
		cal.set(2014, 10, 15);
		java.util.Date date2 = cal.getTime();
		
		cal.set(2014, 10, 15);
		java.util.Date date3 = cal.getTime();
		
		/**********insert the details to new stocks**********/
		Stock s1 = new Stock ("PIH", 13.1f, 12.4f, date1);
		Stock s2 = new Stock ("AAL", 5.78f, 5.5f, date2);
		Stock s3 = new Stock ("CAAS", 32.2f, 31.5f, date3);
		
		/*******adding the stocks to porfolio number 1*******/
		portfolio1.addStock(s1);
		portfolio1.addStock(s2);
		portfolio1.addStock(s3);
		
		/**@return portfolio number */
		return portfolio1;
			
		}
	
		/**************getPortfolio*******************
		 * @return portfolio number 2.*/
		public Portfolio getPortfolio(Portfolio portfolio){
			Portfolio portfolio2=new Portfolio(portfolio);
			return portfolio2;
		}
	}



