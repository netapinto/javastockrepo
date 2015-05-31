package com.myorg.stock.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.algo.service.ServiceManager;
import com.myorg.netaproject.sevice.PortfolioManager;


public class InitServlet extends HttpServlet {
	 @Override
	public void init() throws ServletException {
			
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
	 }
}
