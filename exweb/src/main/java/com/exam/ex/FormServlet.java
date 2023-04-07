package com.exam.ex;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form.do")
public class FormServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공

		PrintWriter out = resp.getWriter();

		String prods = req.getParameter("prod");
		String fruits = req.getParameter("fruit");
		String[] drinks = req.getParameterValues("drink");		//파라미터 값 여러개 선택시 문자열 배열로 선택

		// op 파라미터값에 맞는 사칙연산을 수행
		// 문자열 값을 동등비교하는 경우, == 연산자가 아닌 .equals() 메소드 사용
		// "문자열1"=="문자열2" X
		// "문자열1".equals("문자열2") O

		// 숫자타입클래스명.parse숫자타입명("숫자문자열")
		// Integer.parseInt("123") == 123
		// Float.parseFloat("123.456") == 123.456

				
		out.print("<!DOCTYPE html>                     ");
		out.print("<html>                              ");
		out.print("<head>                              ");
		out.print("<meta charset='UTF-8'>              ");
		out.print("<title>HELLO</title>                ");
		out.print("</head>                             ");
		out.print("<body>                              ");
		out.print("<h2>요청주소 : " + req.getRequestURL() + "</h2>");
		out.print("<h2>요청주소 : " + req.getRequestURI() + "</h2>");
		out.print("<h2>애플리케이션 고유경로 : " + req.getContextPath() + "</h2>");
		out.print("<h2>요청방식 : " + req.getMethod() + "</h2>");
		out.print("<h2>요청헤더 : " + req.getHeader("User-Agent") + "</h2>");
		out.print("<h2>사용자IP주소 : " + req.getRemoteAddr() + "</h2>");
		
		
		out.print("<h2>선택한 상품 : " + prods + "</h2>");
		out.print("<img src='https://api.lorem.space/image/"+prods+"?w=150&h=150'/>");
		
		
		if(fruits != null){
			out.print("<h2>선택한 상품 : " + fruits + "</h2>");
						  }
		else {
			out.print("<h2>선택한 상품이 없습니다.</h2>");
			 }
		
		
		out.print("<h2>선택한 상품 :");
		
		
		if(drinks != null) {		//파라미터가 전송되지 않은 경우 NULL
			for (int i = 0; i < drinks.length; i++) {		//파라미터 값 여러개 선택시 문자열 배열로 선택
				out.print("[" + drinks[i] + "]");
													}
						   }
		else {
			out.print("선택한 상품이 없습니다.");
			 }
		
		
		out.print("</h2>");
		out.print("</body>                             ");
		out.print("</html>                             ");

	}
}

