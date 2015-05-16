package com.myorg.netaproject.model;


/** Portfolio class is a program that implements an application that 
 * Includes an array of stocks.
 */

public class Portfolio {
 
		/******enum ALGO_RECOMMENDATION******/
		public enum ALGO_RECOMMENDATION {
			BUY, SELL, REMOVE, HOLD
		}
		
		
		/******constant******/
		private final int MAX_PORTFOLIO_SIZE=5;
	
		/******private parameters******/
		private String title;
		private Stock[] stocks;
		private int portfolioSize=0;	
		private String ret;
		private float balance;
		
		
		/**********c'tor*********/
		/** Constructor for the Portfolio class
		@param stocks - an array of stocks.
		@param title- the title of the potfolio.
		*/
		public Portfolio(String title){
		this.stocks = new Stock[MAX_PORTFOLIO_SIZE];
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
		public String getTitle() {
			return title;
		}
	
		public void setTitle(String title) {
			this.title = title;
		}
	
		public Stock[] getStocks() {
			return stocks;
		}
		
		public float getStocksValue(){ /**returns value bid of all stocks in the array stocks*/
			float totalValueStocks=0;
			float valueBid;
			for(int i=0; i<portfolioSize ; i++){
				valueBid = stocks[i].getStockQuantity()* stocks[i].getBid();
				totalValueStocks +=valueBid;
 			}
			return totalValueStocks;
		}
		
		public float getBalance(){ /**returns balance*/
			return balance;
		}
		
		public float getTotalValue(){/**returns total value of the balance +  stocks's value*/
			float res=0;
			float sumValueStocks= this.getStocksValue();
			float portfolioBalance = this.getBalance();
			res = sumValueStocks + portfolioBalance;
			
			return res;
		}
	
		/***************addStock********************
		 * Gets an array stocks and adds a new stock.*/
		public void addStock(Stock stock){
			boolean ifAlreadyExists=false;
			stock.setStockQuantity(0);
			
			for (int i=0 ; i< portfolioSize; i++){
				if(this.stocks[i].getSymbol().equals(stock.getSymbol())){
					ifAlreadyExists = true;
				}
			}
			if (stock!=null && portfolioSize<MAX_PORTFOLIO_SIZE && ifAlreadyExists == false ){
				this.stocks[portfolioSize] = stock;
				portfolioSize++;		
			}
			if(portfolioSize>=MAX_PORTFOLIO_SIZE)
				System.out.println("Can’t add new stock, portfolio can have only "+MAX_PORTFOLIO_SIZE+" stocks");	
		}
	
		/**********UpdateBalance*********/
		/**
		@param amount- value to add to balance 
		*/
		public void updateBalance(float amount){
				balance += amount;
		System.out.println("balance is :" +balance);

		}
		
		/***************removeStock********************
		 * Gets an array stocks &  index and remove the stock.
		 * @param - array, index in the array.*/
		public boolean removeStock (String symbol){
			boolean Success = true;
			boolean Fail = false; 
			boolean ifAlreadyExists = false; 

			for (int i=0; i<portfolioSize; i++){ /**stock is exists*/
				if (this.stocks[i].getSymbol().equals(symbol)){
					sellStock(symbol, stocks[i].getStockQuantity()); /**first, sell the stock*/
					this.stocks[i]=null;
					this.stocks[i]=this.stocks[portfolioSize-1];
					portfolioSize--;
					return Success;
					}
			}
			if (ifAlreadyExists = false) /**stock is not exists*/
					System.out.println("Stock is not exist in the Portfolio");
					return Fail;
		}
	

		/***************sellStock********************
			 * Gets symbol &  quantity and sell the stocks.
			 * @param - symbol, quantity .*/
			public boolean sellStock(String symbol,int quantity){
				float sum = 0;
				boolean Success = true;
				boolean Fail = false; 
					
				for (int i=0; i<portfolioSize; i++){
					
					if (this.stocks[i].getSymbol().equals(symbol)){
						
						if(quantity == (-1)){ /**sell all stocks of this symbol*/
							
							int QuantityOfAllStocks = this.stocks[i].getStockQuantity();
							sum= QuantityOfAllStocks * stocks[i].getBid();
							stocks[i].setStockQuantity(0);
							System.out.println(sum);
							}
						
						else if (quantity> stocks[i].getStockQuantity()){ /**can't sell - there is not enough stocks*/
							System.out.println("Not enough stocks to sell");
							return Fail;
							}
					
						else if (quantity <= 0){ /**error - negative number*/
							return Fail;
							}
						
						else {			
							sum= quantity * stocks[i].getBid();
							stocks[i].setStockQuantity(stocks[i].getStockQuantity()-quantity);
							System.out.println(sum);
							}			
					}
				}
				updateBalance(sum);
				System.out.println(quantity + " stocks of " +symbol+ " were sold!"+sum);
				return Success;
			}	
			
		/***************buyStock********************
			 * Gets symbol &  quantity and sell the stocks.
			 * @param - symbol, quantity .*/
			public boolean buyStock (Stock stock,int quantity){
				float sum = 0;
				int newQuantity;
				boolean Success = true;
				boolean Fail = false;
				boolean ifAlreadyExists = false; 

				if ((quantity* stock.getAsk())> balance) { /**can't buy- not enough money*/
					System.out.println("Not enough balance to complete purchase.");
					return Fail;
				}
			
				for (int i=0; i<portfolioSize; i++){
					if (this.stocks[i].getSymbol().equals(stock.getSymbol())){
						
						if (quantity == -1){ /**buy stocks with all the money*/
						float quantityToBuy;
						quantityToBuy = this.balance / stock.getAsk();
						newQuantity = stocks[i].getStockQuantity() + quantity;
						stocks[i].setStockQuantity(newQuantity);
						sum= quantityToBuy* stock.getAsk();
						}
							
						else {
						newQuantity = stocks[i].getStockQuantity() + quantity;
						stocks[i].setStockQuantity(newQuantity);
						sum = quantity* stock.getAsk();
						}
					}
				}
				if (ifAlreadyExists == false){ /**buy new stock that not exists*/
					addStock(stock);
					sum = quantity* stock.getAsk();
					stock.setStockQuantity(quantity);
					System.out.println(sum);
					}
				
				System.out.println(sum);
				sum *= (-1);
				updateBalance(sum);
				System.out.println(quantity + " stocks of " +stock.getSymbol() + " were bought!");
				return Success;
				}		
			
		/***************changeStock**********************
		 * Gets the value of bid & the index for change in the array of stocks.
		 * @param - bid, index in the array.*/
		public void changeStock (float bid, int indexForChange){
			this.stocks[indexForChange].setBid(bid);	
		}

		/**************getHtmlString*******************
		* @return a string that includes title of the portfolio and the stocks.*/
		public String getHtmlString(){
			String ret = new String ("<h1> " +this.getTitle()+ " </h1> <br> Total Portfolio Value: " +this.getTotalValue()+  " $ <br>  Total Stocks value: " +this.getStocksValue()+  " $ <br>"
					+ "Balance: " +this.getBalance()+ " $ <br> <br>" );	
			
			for (int i=0 ; i< portfolioSize; i++){
				Stock current = this.stocks[i];
				ret+=current.getHtmlDescription()+"<br>";
			}
			return ret;
		}

}