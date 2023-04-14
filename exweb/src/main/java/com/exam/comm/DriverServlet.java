package com.exam.comm;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

public class DriverServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
			System.out.println("DriverServlet init() 출발!");
			
			String cname = getInitParameter("driver");		//현재 서블릿의 "Driver" 초기화파라미터 값 읽기
			
			// 서블릿이 만들어질때 한번만 실행되는
			// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
			try {
				Class.forName(cname);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		
	}

}
