package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/param.do")	//Web 치고 자동 완성
public class ParamServlet extends HttpServlet{	//Http 치고 자동 완성
	
	@Override	//servi 치고 자동 완성 
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청주소 뒤에 "?파라미터명=파라미터값&파라미터명=파라미터값&......"
		//형태로 추가로 전달할 파라미터들을 지정 가능
		//서블릿에서는 요청객체.getParameter("파라미터명") 명령문으로
		//원하는 파라미터의 값을 사용 가능
		// ?, =, & 등 기호는 못바꾼다.
		
		// GET 방식으로 전송된 요청 파라미터값을 읽을 때 사용할 문자 인코딩 방식은
		// 톰캣의 server.xml 파일에 있는 <Connector protocol=HTTP/1.1"> 태그에
		// URIEncoding="UTF-8" 속성을 추가하여 지정 가능
		
		req.setCharacterEncoding("UTF-8");	//POST 방식으로 전송된 요청파라미터값을 읽을 때 사용할 문자인코딩 방식
		resp.setContentType("text/html");	//응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정)	//resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8");	//응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		//응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		
		PrintWriter out = resp.getWriter();	//응답객체에 무언가를 쓸수있는 객체를 가져오는거, 응답객체에 내용을 쓸 수 있는 Writer 가져오기
		//out.print("Hello SERVLET");	//응답객체에 문자열을 출력
		//응답객체에 출력한 내용은 클라이언트(웹브라우저)로 전송된다.
		//문자를 프린트하면 텍스트형식 문자만 출력된다, 웹을 꾸밀려면 html를 사용해서 해야한다.
		
		String xval = req.getParameter("x");	//req.getPar 치고 자동 완성
		String vval = req.getParameter("v");
		String cval = req.getParameter("c");
		
		
		out.print("<!DOCTYPE html>                     ");
		out.print("<html>                              ");
		out.print("<head>                              ");
		out.print("<meta charset='UTF-8'>              ");
		out.print("<title>HELLO</title>                ");
		out.print("</head>                             ");
		out.print("<body>                              ");
		out.print("<h2> \" x 파라미터값 : " + xval + " " + "\"</h2>");
		out.print("<h2> \" v 파라미터값 : " + vval + " " + "\"</h2>");
		out.print("<h2> \" c 파라미터값 : " + cval + " " + "\"</h2>");
		out.print("</body>                             ");
		out.print("</html>                             ");
                                                    
	}
}

// "/param.do?x=포로리&y=너부리"로 요청을 보내면,
// 화면에
// "x 파라미터값: 포로리"
// "y 파라미터값: 너부리"
// 라고 출력되도록 ParamServlet을 완성 하세요.