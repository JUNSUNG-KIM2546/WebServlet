package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HiServlet2 extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { //serv 자동문장으로 서비스 불러옴
		PrintWriter writer = resp.getWriter();	//resp.wri 자동문장으로 불러옴
		writer.print("Welcome!!@@");
		
		
	}

}
