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
			 * creates myPortfolio 
			 */
			
			PortfolioManager portfolioManager = new PortfolioManager();
			Portfolio myPortfolio = portfolioManager.getPortfolio("myPortfolio");	
			
			/**print the myPortfolio*/
			resp.getWriter().println(myPortfolio.getHtmlString());
	
		}
}
