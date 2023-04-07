package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 일반적으로 HttpServlet 클래스를 상속(확장)하여 구현
//서블릿 맵핑 : 어떤 경로(주소)로 요청이 왔을때 서블릿을 실행시키고 싶은지 요청경로와 서블릿을 연결
//서블릿 맵핑 방법 2가지
//(1) : web.xml 파일에 <servlet><servlet-mapping>태그를 사용하여 설정
//(2) : 서블릿 클래스에 @WebServlet("요청주소") 를 적용

//(1) 방법
public class ByServlet extends HttpServlet{		//상속 : extends HttpServlet
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ByeServlet 실행!");
		
		PrintWriter out = resp.getWriter();	//resp.getw 자동문장으로 불러온다.
		out.print("Bye Bye");
	}
}
//1.	웹브라우저에서 http://localhost:8000/exweb/bye.do 로 접속하면,
//		웹브라우저 화면에 "Bye Bye" 라고 출력되도록 ByeServlet 클래스를 변경하세요.

//2.	웹브라우저에서 http://localhost:8000/exweb/foo/bar.do 로 접속하면,
//		웹브라우저 화면에 "Welcome" 이라고 출력되도록 HiServlet 클래스를 새로 추가하시요.