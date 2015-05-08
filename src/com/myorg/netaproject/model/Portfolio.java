package com.myorg.netaproject.model;

/** Portfolio class is a program that implements an application that 
 * Includes an array of stocks.
 */

public class Portfolio {
 
		/******constant******/
		private final int MAX_PORTFOLIO_SIZE=5;
	
		/******private parameters******/
		private String title;
		private Stock[] stocks;
		private int portfolioSize=0;	
		private String ret;
	
		/**********c'tor*********/
		/** Constructor for the Portfolio class
		@param stocks - an array os stocks.
		@param title- the title of the potfolio.
		*/
		public Portfolio(String title){
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.setTitle("Portfolio 1");
		}
	
		/******copy c'tor******/
		public Portfolio(Portfolio portfolio){
			this.stocks=new Stock [MAX_PORTFOLIO_SIZE];
			this.setTitle("Portfolio #2");
			this.portfolioSize=portfolio.portfolioSize;
			
			for(int i=0; i<portfolioSize ; i++){
				Stock coppied = new Stock(portfolio.stocks[i]);
				this.stocks[i]=coppied;
			}
		}
	
		/******setters & getters methods******/
		private String getTitle() {
			return title;
		}
	
		private void setTitle(String title) {
			this.title = title;
		}
	
		public Stock[] getStocks() {
			return stocks;
		}
	
		/***************addStock********************
		 * Gets an array stocks and adds a new stock.*/
		public void addStock(Stock stock){
			if (stock!=null && portfolioSize<MAX_PORTFOLIO_SIZE){
				this.stocks[portfolioSize] = stock;
				portfolioSize++;		
			}
			else{
			System.out.println("Sorry, Portfolio is full, or stock is null");
			}
		}
	
	
		/**************getHtmlString*******************
		 * @return a string that includes title of the portfolio and the stocks.*/
		public String getHtmlString(){
			ret = new String ("<h1> " +this.getTitle()+ " </h1>");	
			for (int i=0 ; i< portfolioSize; i++){
				Stock current = this.stocks[i];
				ret+=current.getHtmlDescription()+"<br>";
			}
			return ret;
			}
	
		/***************removeStock********************
		 * Gets an array stocks &  index and remove the stock.
		 * @param - array, index in the array.*/
		public void removeStock (Stock[] stocks, int index){
			for (int i=index; i<portfolioSize; i++){
				if (this.stocks[i+1]==null){
					stocks[i]=null;
				}
				else{
					this.stocks[i]=stocks[i+1];
				}
			}
			portfolioSize--;	
		}
	
		/***************changeStock**********************
		 * Gets the value of bid & the index for change in the array of stocks.
		 * @param - bid, index in the array.*/
		public void changeStock (float bid, int indexForChange){
			this.stocks[indexForChange].setBid(bid);	
		}
		
	
	}

	

