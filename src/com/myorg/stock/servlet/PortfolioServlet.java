package com.myorg.stock.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myorg.netaproject.model.Portfolio;
import com.myorg.netaproject.sevice.PortfolioManager;

/** PortfolioServlet  is a program that can extends to HttpServlet
 * by doGet method.
 */

public class PortfolioServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			resp.setContentType("text/html");
			
			/**************portfolioManager******************
			 * creates portfolio 1 & 2
			 */
			PortfolioManager portfolioManager = new PortfolioManager();
			Portfolio portfolio1 = portfolioManager.getPortfolio("portfolio 1");	
			Portfolio portfolio2 = portfolioManager.getPortfolio(portfolio1);

			/**print the portfolios*/
			resp.getWriter().println(portfolio1.getHtmlString());
			resp.getWriter().println(portfolio2.getHtmlString());

			/**remove first stock from potfolio1 */
			portfolio1.removeStock(portfolioManager.getPortfolio(portfolio1).getStocks(),0);
			
			/**print the portfolios*/
			resp.getWriter().println(portfolio1.getHtmlString());
			resp.getWriter().println(portfolio2.getHtmlString());
			
			/**change third stock from portfolio2*/
			portfolio2.changeStock(55.55f, 2);

			/**print the portfolios*/
			resp.getWriter().println(portfolio1.getHtmlString());
			resp.getWriter().println(portfolio2.getHtmlString());
			
			
				}
}
