package com.myorg.netaproject.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/** Stock class is a program that create the stocks.
 */

public class Stock {
	
		/******constant******/	
		private static final int BUY=0;
		private static final int SELL=1;
		private static final int REMOVE=0;
		private static final int HOLD=0;

		/******private parameters******/
		private String symbol;
		private float ask;
		private float bid;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		 
		/**********c'tor*********/
		/** Constructor for the Portfolio class
		@param symbol
		@param ask
		@param bid
		@param date
		*/
		public Stock(String symbol, float ask,float bid, Date date) {
			this.symbol = symbol;
			this.ask = ask;
			this.bid = bid;
			this.date = date;
		}

		/******copy c'tor******/
		public Stock(Stock s) {
			this(new String(s.getSymbol()), s.getAsk(), s.getBid(), new Date(s.getDate().getTime()));
		}

		/******setters & getters methods******/
		public String getSymbol() {
			return symbol;
		}
		public void setSymbol(String symbol) {
			this.symbol = symbol;
		}
		public float getBid() {
			return bid;
		}
		
		public void setBid(float bid) {
			this.bid = bid;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}

		public float getAsk() {
			return ask;
		}
		public void setAsk(float ask) {
			this.ask = ask;
		}

		/**************getHtmlDescription*******************
		 * @return a string that includes the details of the stock.*/
		public String getHtmlDescription(){
			DateFormat datef = new SimpleDateFormat("MM/dd/yyyy");
			String dateStr = datef.format(getDate());
			
			String ret =  "<b>symbol</b>:" + getSymbol()+ ", <b>ask</b>:"+getAsk()+ ", <b>bid</b>:"+getBid()+ ", <b>date</b>:"+dateStr;
			return ret;
		}

}