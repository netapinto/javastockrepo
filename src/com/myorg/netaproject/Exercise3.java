package com.myorg.netaproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Exercise3 extends HttpServlet{	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			resp.setContentType("text/html");
			
			double radius = 50;
			double area = Math.PI * Math.pow(radius, 2);
			
			double angleB= 30;
			int hypotenuseLengh=50;
			double opposite = Math.sin(Math.toRadians(angleB))*hypotenuseLengh;
			
			double base = 20;
			double exp=13;
			double result = Math.pow(base, exp);
		
			String line1 = new String("calculation 1: Area of circle with radius " +radius+ "is"  +area+ "square-cm");
			String line2 = new String("calculation 2: Length of opposite where angle B is" +angleB+ " is "+opposite+ " cm");
			String line3 = new String("calculation 3: Power of "+base+ " with exp of "+exp+" is "+result);

			String printResult = line1 + "<br>" + line2 + "<br>" + line3;
			resp.getWriter().println(printResult);			
	}
}
