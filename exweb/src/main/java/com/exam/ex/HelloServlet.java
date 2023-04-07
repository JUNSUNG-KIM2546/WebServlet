package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//서블릿은 일반적으로 HttpServlet 클래스를 상속(확장)하여 구현
//서블릿 맵핑 : 어떤 경로(주소)로 요청이 왔을때 서블릿을 실행시키고 싶은지 요청경로와 서블릿을 연결
//서블릿 맵핑 방법 2가지
//(1) : web.xml 파일에 <servlet><servlet-mapping>태그를 사용하여 설정
//(2) : 서블릿 클래스에 @WebServlet("요청주소") 를 적용

//(2)방법
//서블릿 URL패턴(주소,경로) 지정 규칙
//반드시 "/" 또는 "*." 으로 시작
//"*"은 0개 이상의 모든 문자열과 일치
@WebServlet("/hello.do") //"/hello.do" 파일을 달라는 요청이 오면 이 서블릿 클래스를 실행하라
public class HelloServlet extends HttpServlet {		//상속 : extends HttpServlet

	//클라이언트(웹브라우저)의 요청을 받아서 서블릿이 실행될 때마다 서블릿의 service() 메서드가 한번씩 실행
	//톰캣이 service() 메서드 실행시에 인자로 요청객체와 응답객체를 전달
	//요청객체 (HttpServletRequest req) : 클라이언트(웹브라우저)가 보낸 요청에 대한 모든 정보를 담고 있는 객체
	//응답객체 (HttpServletResponse resp) : 요청에 대한 응답으로 클라이언트(웹브라우저)에게 전송할 모든 정보를 담는 객체
	
	@Override	//상속받은 것을 덮어씌우라는 명령		//컴퓨터한테 적용하라는 주석
	protected void service(HttpServletRequest req	/*요청*/, HttpServletResponse resp	/*응답*/) throws ServletException, IOException {
		System.out.println("HelloServlet 실행! 얍얍얍!");
		//요청주소 뒤에 "?파라미터명=파라미터값&파라미터명=파라미터값&......"
		//형태로 추가로 전달할 파라미터들을 지정 가능
		//서블릿에서는 요청객체.getParameter("파라미터명") 명령문으로
		//원하는 파라미터의 값을 사용 가능
		// ?, =, & 등 기호는 못바꾼다.
		String aval = req.getParameter("a");
		String bval = req.getParameter("b");
		
		
		resp.setContentType("text/html");	//응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정)	//resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8");	//응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		//resp.setContentType("text/htnl; charset=UTF-8");	//문자인코딩과 데이터타입을 한번에 설정가능
		PrintWriter out = resp.getWriter();	//응답객체에 무언가를 쓸수있는 객체를 가져오는거, 응답객체에 내용을 쓸 수 있는 Writer 가져오기
		//out.print("Hello SERVLET");	//응답객체에 문자열을 출력
		//응답객체에 출력한 내용은 클라이언트(웹브라우저)로 전송된다.
		//문자를 프린트하면 텍스트형식 문자만 출력된다, 웹을 꾸밀려면 html를 사용해서 해야한다.
		
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
		out.print("	<h1>서블릿이 출력하는 HTML (안녕!! HTML)</h1> ");
		out.print("	<h2>a :" +  aval + "</h2> ");
		out.print("	<h2>b : " + bval + "</h2> ");
		out.print("</body>                             ");
		out.print("</html>                             ");
                                                    
		
	}
}
