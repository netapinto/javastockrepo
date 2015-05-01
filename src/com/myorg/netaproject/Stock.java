package com.myorg.netaproject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Stock {
		private static final int BUY=0;
		private static final int SELL=1;
		private static final int REMOVE=0;
		private static final int HOLD=0;

		private String symbol;
		private float ask;
		private float bid;
		private Date date;
		private int recommendation;
		private int stockQuantity;
		 
		
		public Stock(String symbol, float ask,float bid, Date date) {
			this.symbol = symbol;
			this.ask = ask;
			this.bid = bid;
			this.date = date;
		}
		
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

		
		public String getHtmlDescription(){
			DateFormat datef = new SimpleDateFormat("MM/dd/yyyy");
			String dateStr = datef.format(getDate());
			
			String ret =  "<b>symbol</b>:" + getSymbol()+ ", <b>ask</b>:"+getAsk()+ ", <b>bid</b>:"+getBid()+ ", <b>date</b>:"+dateStr;
			return ret;
		}

}