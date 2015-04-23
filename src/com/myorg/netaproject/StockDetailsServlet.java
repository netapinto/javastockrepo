package com.myorg.netaproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Calendar;

public class StockDetailsServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			resp.setContentType("text/html");
			
			Calendar cal = Calendar.getInstance();
			cal.set(2014, 10, 15);
			java.util.Date date1 = cal.getTime();

			cal.set(2014, 10, 15);
			java.util.Date date2 = cal.getTime();
			
			cal.set(2014, 10, 15);
			java.util.Date date3 = cal.getTime();
			
			Stock s1 = new Stock ("PIH", 13.1f, 12.4f, date1);
			Stock s2 = new Stock ("AAL", 5.78f, 5.5f, date2);
			Stock s3 = new Stock ("CAAS", 32.2f, 31.5f, date3);
			
			String finalStr= s1.getSymbol()  +"<br>"+ s2.getHtmlDescription() +"<br>"+ s3.getHtmlDescription(); 
			
			resp.getWriter().println(finalStr);
			
	}

}

