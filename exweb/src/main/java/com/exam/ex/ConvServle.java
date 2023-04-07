package com.exam.ex;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dollar")
public class ConvServle extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공

		PrintWriter out = resp.getWriter();

		String numval = req.getParameter("num");
		String unitval = req.getParameter("unit");

		// op 파라미터값에 맞는 사칙연산을 수행
		// 문자열 값을 동등비교하는 경우, == 연산자가 아닌 .equals() 메소드 사용
		// "문자열1"=="문자열2" X
		// "문자열1".equals("문자열2") O

		// 숫자타입클래스명.parse숫자타입명("숫자문자열")
		// Integer.parseInt("123") == 123
		// Float.parseFloat("123.456") == 123.456

		double numvals = Double.parseDouble(numval);
		
		double result = 0; // 이 변수를 전체에 반영하고 싶으면 갈호 밖에서 변수선언! // 변수 생존범위 변수와 가장 가까운 중갈호까지
		
		String fromunit = "";
		String toUnit = "";

		switch (unitval) {
		case "won":
			result = numvals / 1287;		// 원 -> 달러
			fromunit = "원";
			toUnit = "달러";
			break;

		case "dol":
			result = numvals * 1287;		// 달러 -> 원
			fromunit = "달러";
			toUnit = "원";
			break;

//		default:
//			break;
		}

//		double result = 0;

		out.print("<!DOCTYPE html>                     ");
		out.print("<html>                              ");
		out.print("<head>                              ");
		out.print("<meta charset='UTF-8'>              ");
		out.print("<title>HELLO</title>                ");
		out.print("</head>                             ");
		out.print("<body>                              ");

		if(unitval.equals("won")) {
		out.print("<h2>" + numval + fromunit + "=" + result + toUnit + "</h2>");
		out.printf("<h2>%.3f%s = %f%s</h2>",numval,fromunit,result,toUnit);}
		
		else {
		out.print("<h2>" + numval + fromunit + "=" + result + toUnit + "</h2>");
		out.printf("<h2>%f%s = %f%s</h2>",numval,fromunit,result,toUnit);}
//		if (op.equals("plus")) {
//			result = xnum + ynum;
//			out.print("<h2>" + xx + "+" + yy + "=" + result + "</h2>");
//		} else if (op.equals("min")) {
//			result = xnum - ynum;
//			out.print("<h2>" + xx + "-" + yy + "=" + result + "</h2>");
//		} else if (op.equals("mul")) {
//			result = xnum * ynum;
//			out.print("<h2>" + xx + "*" + yy + "=" + result + "</h2>");
//		} else {
//			result = xnum / ynum;
//			out.print("<h2>" + xx + "/" + yy + "=" + result + "</h2>");
//		}

		out.print("</body>                             ");
		out.print("</html>                             ");

	}
}
