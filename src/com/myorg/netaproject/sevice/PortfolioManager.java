package com.myorg.netaproject.sevice;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.algo.dto.PortfolioDto;
import org.algo.dto.PortfolioTotalStatus;
import org.algo.dto.StockDto;
import org.algo.exception.PortfolioException;
import org.algo.exception.SymbolNotFoundInNasdaq;
import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;
import org.algo.service.DatastoreService;
import org.algo.service.MarketService;
import org.algo.service.PortfolioManagerInterface;
import org.algo.service.ServiceManager;

import com.myorg.excetion.BalanceException;
import com.myorg.excetion.BalanceIsNotEnoughException;
import com.myorg.excetion.PortfolioFullException;
import com.myorg.excetion.StockAlreadyExistsException;
import com.myorg.excetion.StockNotExistException;
import com.myorg.excetion.StockQuantityException;
import com.myorg.excetion.StockQuantityNotEnoughException;
import com.myorg.netaproject.model.Portfolio;
import com.myorg.netaproject.model.Stock;
import com.myorg.netaproject.model.Portfolio.ALGO_RECOMMENDATION;


/** PortfolioManager class is a program that manage the portfolio by
 * setting the date and the other details of the stock.
 */

public class PortfolioManager implements PortfolioManagerInterface {
		public enum OPERATION {BUY, SELL, REMOVE, HOLD }

		private DatastoreService datastoreService = ServiceManager.datastoreService();
		
		
		/*
		Portfolio myPortfolio;
			
		/**************getPortfolio*******************
		 * @return myPortfolio
		public Portfolio getPortfolio(String title){
		Portfolio myPortfolio = new Portfolio (title);
		myPortfolio.setTitle("Exercise 7 portfolio");
		myPortfolio.updateBalance(10000);
		
		/**************setting the date**************
		Calendar cal = Calendar.getInstance();
		cal.set(2014, 12, 15);
		java.util.Date date1 = cal.getTime();
	
		cal.set(2014, 12, 15);
		java.util.Date date2 = cal.getTime();
		
		cal.set(2014, 12, 15);
		java.util.Date date3 = cal.getTime();
		
		/**********insert the details to new stocks**********
		Stock s1 = new Stock ("PIH", 10f, 8.5f, date1, 20);
		Stock s2 = new Stock ("AAL", 30.0f, 25.5f, date2, 30);
		Stock s3 = new Stock ("CAAS", 20.0f, 15.5f, date3, 40);
		
		/*******buy the stocks to myPorfolio*******
		myPortfolio.buyStock(s1, 20);
		myPortfolio.buyStock(s2, 30);
		myPortfolio.buyStock(s3, 40);
		
		
		/*******sell the stocks of myPorfolio*******
		myPortfolio.sellStock("AAL", (-1));
		
		/*******remove the stock from myPorfolio*******
		myPortfolio.removeStock("CAAS");
		
		/**@return myPortfolio *
		return myPortfolio;
			
		}

		*/
		
		
		public PortfolioInterface getPortfolio() {
			PortfolioDto portfolioDto = datastoreService.getPortfolilo();
			return fromDto(portfolioDto);
		}
		
		/**
		 * Update portfolio with stocks
		 */
		@Override
		public void update() {
			StockInterface[] stocks = getPortfolio().getStocks();
			List<String> symbols = new ArrayList<>(Portfolio.getMAX_PORTFOLIO_SIZE());
			for (StockInterface si : stocks) {
				symbols.add(si.getSymbol());
			}

			List<Stock> update = new ArrayList<>(Portfolio.getMAX_PORTFOLIO_SIZE());
			List<Stock> currentStocksList = new ArrayList<Stock>();
			try {
				List<StockDto> stocksList = MarketService.getInstance().getStocks(symbols);
				for (StockDto stockDto : stocksList) {
					Stock stock = fromDto(stockDto);
					currentStocksList.add(stock);
				}

				for (Stock stock : currentStocksList) {
					update.add(new Stock(stock));
				}

				datastoreService.saveToDataStore(toDtoList(update));

			} catch (SymbolNotFoundInNasdaq e) {
				System.out.println(e.getMessage());
			}
		}

		public PortfolioInterface getPortfolioInterface() {
			PortfolioDto portfolioDto = datastoreService.getPortfolilo();
			return fromDto(portfolioDto);
		}
		
		/**
		 * get portfolio totals
		 */
		@Override
		public PortfolioTotalStatus[] getPortfolioTotalStatus () {

			Portfolio portfolio = (Portfolio) getPortfolio();
			Map<Date, Float> map = new HashMap<>();

			//get stock status from db.
			StockInterface[] stocks = portfolio.getStocks();
			for (int i = 0; i < stocks.length; i++) {
				StockInterface stock = stocks[i];

				if(stock != null) {
					List<StockDto> stockHistory = null;
					try {
						stockHistory = datastoreService.getStockHistory(stock.getSymbol(),30);
					} catch (Exception e) {
						return null;
					}
					for (StockDto stockDto : stockHistory) {
						Stock stockStatus = fromDto(stockDto);
						float value = stockStatus.getBid()*stockStatus.getStockQuantity();

						Date date = stockStatus.getDate();
						Float total = map.get(date);
						if(total == null) {
							total = value;
						}else {
							total += value;
						}

						map.put(date, value);
					}
				}
			}

			PortfolioTotalStatus[] ret = new PortfolioTotalStatus[map.size()];

			int index = 0;
			//create dto objects
			for (Date date : map.keySet()) {
				ret[index] = new PortfolioTotalStatus(date, map.get(date));
				index++;
			}

			//sort by date ascending.
			Arrays.sort(ret);

			return ret;
		}
		
		/**
		 * Add stock to portfolio 
		 * @throws PortfolioException 
		 */
		@Override
		public void addStock(String symbol) throws PortfolioException {
			Portfolio portfolio = (Portfolio) getPortfolio();

				StockDto stockDto = null;
			
				try {
					stockDto = ServiceManager.marketService().getStock(symbol);
			
				//get current symbol values from nasdaq.
				Stock stock = fromDto(stockDto);
				
				//first thing, add it to portfolio.
				
				try {
					portfolio.addStock(stock);
				} 
				catch (StockAlreadyExistsException e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
					e1.printStackTrace();
					try {
						throw e1;
					} catch (StockAlreadyExistsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				catch (PortfolioFullException e2) {		
					e2.getMessage();
					e2.printStackTrace();
					try {
						throw e2;
					} catch (PortfolioFullException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//or:
				//portfolio.addStock(stock);   

				datastoreService.saveStock(toDto(portfolio.findStock(symbol)));
				
				flush(portfolio);
				}
				catch (SymbolNotFoundInNasdaq e5) {
					e5.getMessage();
					e5.printStackTrace();
					try {
						throw e5;
					} catch (SymbolNotFoundInNasdaq e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
			}
		
			
		/**
		 * Buy stock
		 */
		@Override
		public void buyStock(String symbol, int quantity) throws PortfolioException{
			
				Portfolio portfolio = (Portfolio) getPortfolio();
				
				Stock stock = (Stock) portfolio.findStock(symbol);
				if(stock == null) {
					try {
						stock = fromDto(ServiceManager.marketService().getStock(symbol));
					} catch (SymbolNotFoundInNasdaq e) {
						// TODO Auto-generated catch block
						e.getMessage();
						e.printStackTrace();
						//throw e;
					}				
				}
				
				try {
					portfolio.buyStock(stock, quantity);
				} catch (BalanceIsNotEnoughException e) {
					
					e.getMessage();
					e.printStackTrace();
					throw e;
				} catch (StockAlreadyExistsException e) {
					e.getMessage();
					e.printStackTrace();
					throw e;
				} catch (PortfolioFullException e) {
					e.getMessage();
					e.printStackTrace();
					throw e;
				} catch (BalanceException e) {
					e.getMessage();
					e.printStackTrace();
					throw e;
				}
				flush(portfolio);
			}

		/**
		 * update database with new portfolio's data
		 * @param portfolio
		 */
		private void flush(Portfolio portfolio) {
			datastoreService.updatePortfolio(toDto(portfolio));
		}

		/**
		 * fromDto - get stock from Data Transfer Object
		 * @param stockDto
		 * @return Stock
		 */
		private Stock fromDto(StockDto stockDto) {
			Stock newStock = new Stock(null,0 ,0, null, 0);

			newStock.setSymbol(stockDto.getSymbol());
			newStock.setAsk(stockDto.getAsk());
			newStock.setBid(stockDto.getBid());
			newStock.setDate(stockDto.getDate().getTime());
			newStock.setStockQuantity(stockDto.getQuantity());
			System.out.println(stockDto.getQuantity());
			if(stockDto.getRecommendation() != null) newStock.setRecommendation(ALGO_RECOMMENDATION.valueOf(stockDto.getRecommendation()));
			else 
				{
				System.out.println("nn");
				newStock.setRecommendation(ALGO_RECOMMENDATION.valueOf("HOLD"));
				}
				
			return newStock;
		}
				

		/**
		 * toDto - covert Stock to Stock DTO
		 * @param inStock
		 * @return
		 */
		private StockDto toDto(StockInterface inStock) {
			if (inStock == null) {
				return null;
			}
			
			Stock stock = (Stock) inStock;
			
			return new StockDto(stock.getSymbol(), stock.getAsk(), stock.getBid(), 
					stock.getDate(), stock.getStockQuantity(), stock.getRecommendation().name());
		}

		/**
		 * toDto - converts Portfolio to Portfolio DTO
		 * @param portfolio
		 * @return
		 */
		private PortfolioDto toDto(Portfolio portfolio) {
			StockDto[] array = null;
			StockInterface[] stocks = portfolio.getStocks();
			if(stocks != null) {
				array = new StockDto[stocks.length];
				for (int i = 0; i < stocks.length; i++) {
					array[i] = toDto(stocks[i]);
				}
			}
			return new PortfolioDto(portfolio.getTitle(), portfolio.getBalance(), array);
		}

		/**
		 * fromDto - converts portfolioDto to Portfolio
		 * @param dto
		 * @return portfolio
		 */
		private Portfolio fromDto(PortfolioDto dto) {
			StockDto[] stocks = dto.getStocks();
			Portfolio ret;
			if(stocks == null) {
				ret = new Portfolio();			
			}else {
				List<Stock> stockList = new ArrayList<Stock>();
				for (StockDto stockDto : stocks) {
					stockList.add(fromDto(stockDto));
					//System.out.println("qqq" + stockDto.getQuantity());
				}

				Stock[] stockArray = stockList.toArray(new Stock[stockList.size()]);
				ret = new Portfolio(stockArray);
			}

			ret.setTitle(dto.getTitle());
			try {
				ret.updateBalance(dto.getBalance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return ret;
		}	


		/**
		 * toDtoList - convert List of Stocks to list of Stock DTO
		 * @param stocks
		 * @return stockDto
		 */
		private List<StockDto> toDtoList(List<Stock> stocks) {

			List<StockDto> ret = new ArrayList<StockDto>();

			for (Stock stockStatus : stocks) {
				ret.add(toDto(stockStatus));
			}

			return ret;
		}	
	
		/**
		 * A method that returns a new instance of Portfolio copied from another instance.
		 * @param portfolio		Portfolio to copy.
		 * @return a new Portfolio object with the same values as the one given.
		 */
		public Portfolio duplicatePortfolio(Portfolio portfolio) {
			Portfolio copyPortfolio = new Portfolio(portfolio);
			return copyPortfolio;
		}
		
		/**
		 * Set portfolio title
		 */
		@Override
		public void setTitle(String title) {
			//System.out.println(title);
			Portfolio portfolio = (Portfolio) getPortfolio();
			
			portfolio.setTitle(title);
		//	System.out.println(portfolio.getTitle());

			flush(portfolio);
		}

		/**
		 * update portfolio balance
		 */
		public void updateBalance(float value) throws PortfolioException{ 
			Portfolio portfolio = (Portfolio) getPortfolio();
			try {
				portfolio.updateBalance(value);
			} catch (BalanceException e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			}
			flush(portfolio);
		}

		
		/**
		 * Sell stock
		 */
		@Override
		public void sellStock(String symbol, int quantity) throws PortfolioException {
			Portfolio portfolio = (Portfolio) getPortfolio();
			try {
				portfolio.sellStock(symbol, quantity);
			} catch (StockQuantityNotEnoughException | StockQuantityException| BalanceException e) {
				// TODO Auto-generated catch block
				
			}
			flush(portfolio);
		}

		/**
		 * Remove stock
		 */
		@Override
		public void removeStock(String symbol) { 
			Portfolio portfolio = (Portfolio) getPortfolio();
			try {
				portfolio.removeStock(symbol);
			} catch (StockNotExistException | StockQuantityNotEnoughException| StockQuantityException | BalanceException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flush(portfolio);
		}
}





