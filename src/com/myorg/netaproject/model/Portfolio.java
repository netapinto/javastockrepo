package com.myorg.netaproject.model;

import com.myorg.netaproject.Stock;

public class Portfolio {
 
	private final int MAX_PORTFOLIO_SIZE=5;

	private String title;
	private Stock[] stocks;
	private int portfolioSize=0;	
	private String ret;
	
	public Portfolio(){
	this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
	this.setTitle("portfolio");
	}
	
	private String getTitle() {
		return title;
	}

	private void setTitle(String title) {
		this.title = title;
	}

	private Stock[] getStocks() {
		return stocks;
	}

	public void addStock(Stock stock){
		if (stock!=null && portfolioSize<MAX_PORTFOLIO_SIZE){
			this.stocks[portfolioSize] = stock;
			portfolioSize++;		
		}
		else{
		System.out.println("Sorry, Portfolio is full, or stock is null");
		}
	}
	
	public String getHtmlString(){
		ret = new String ("<h1> " +this.getTitle()+ " </h1>");	
		for (int i=0 ; i< portfolioSize; i++){
			Stock current = this.stocks[i];
			ret+=current.getHtmlDescription()+"<br>";
		}
		return ret;
		}
}
