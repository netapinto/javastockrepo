package com.myorg.netaproject;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Exercise3 extends HttpServlet{	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
			resp.setContentType("text/html");
			
			double radius = 50;
			double area = Math.PI * Math.pow(radius, 2);
			resp.getWriter().println("Area of circle with radius " +radius+ "is:" +area+ "square-cm");	
			
			double angleB= 30;
			double hypotenuseLengh=50;
			double opposite = Math.sin(angleB)*hypotenuseLengh;
			resp.getWriter().println("Length of opposite where angle B is 30 degrees and Hypotenuse length is 50 cm is:"+opposite+"cm");

			
			double base = 20;
			double exp=13;
			double result = Math.pow(base, exp);
			resp.getWriter().println("power of 20 with exp is "+result);	
		
			String line1 = new String("calculation 1: Area of circle with radius " +radius+ "is"  +area+ "square-cm");
			String line2 = new String("calculation 2: Length of opposite where angle B is" +angleB+ " is "+opposite+ " cm");
			String line3 = new String("calculation 3: Power of "+base+ " with exp of "+exp+" is "+result);

			String printResult = line1 + "<br>" + line2 + "<br>" + line3;
			resp.getWriter().println(printResult);			
	}
}
