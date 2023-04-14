package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//다수의 서블릿들이 공유하는 데이터를 저장하는 공간
//1.서버(톰캣)에 저장
// 1) 요청객체
//		- 요청 1개마다 1개의 요청객체 생성, 요청처리가 끝나면 소멸
//		- 하나의 요청을 처리하기 위해 사용되는 서블릿들간의 데이터 공유 (forward, include) (다른 서블릿에서 포워딩이나 인크우딩 해야 제한적으로 사용 가능)
// 2) 세션객체
//		- 클라이언트(웹브라우저) 1개당 1개의 세션 객체 생성 
//		- 클라이언트 종료시, 서버 종료시, 일정시간동안 요청이 없을때 세션객체 소멸
//		- 클라이언트(사용자, 웹브라우저)별로 각각 유지해야하는 데이터 공유
// 3) 서블릿컨텍스트 객체
//		- 웹 애플리케이션 전체에서 1개의 서블릿컨텍스트 객체만 생성
//		- 서버(톰캣) 시작될 때 생성, 서버가 종료될 때 소멸
//		- 모든 사용자와 모든 서블릿들이 데이터 공유
// * 요청객체, 서블릿컨텍스트객체 모두 도일한 메서드로 메서드로 데이터 저장 및 조회
//	- 객체.setAttribute("속성명", 속성값);	= 속성값 저장
//	- 객체.setAttribute("속성명");			= 속성값 읽기
//	- 객체.removeAttribute("속성명");		= 속성 삭제

//2.클라이언트(웹브라우저)에 저장
// 1) 쿠키
//		- 웹브라루저에 데이터를 이름-값 쌍으로 저장
//		- 기본적으로 쿠키를 저장한 웹사이트(도메인)와 동일한 웹사이트로 요청을 보낼때 요청헤더에 쿠키를 자동으로 포함(변경 가능)
//		- 만료기산을 설정하면, 웹브라우저가 종료되더라도 쿠키 값 유지 가능
//		- 웹브라우저에서 접근하여 사용 가능하기 때문에 보안상 위험 존재
//		- 쿠키의 이름과 값은 쉽표, 세미콜론, 공백 등 특수문자와 한글 등 비영어권 문자 사용 불가
//		- 일반적으로 쿠키이름은 영문자와 숫자만 사용, 쿠키값은 인코딩/디코딩하여 사용
// 2) HTML5에서는 sessionStorage, localStorage, indexedDB도 사용 가능

@WebServlet("/save") //"/save" 파일을 달라는 요청이 오면 이 서블릿 클래스를 실행하라
public class SaveServlet extends HttpServlet {		//상속 : extends HttpServlet
	
	@Override	//상속받은 것을 덮어씌우라는 명령		//컴퓨터한테 적용하라는 주석
	protected void service(HttpServletRequest req	/*요청*/, HttpServletResponse resp	/*응답*/) throws ServletException, IOException {
		System.out.println("SaveServlet 실행! 얍얍얍!");
		String nval = req.getParameter("nn");
		String rval = req.getParameter("rem");
		
		//현재 요청을 보낸 사용자의 세션객체 가져오기 (없으면 생성)
		HttpSession session = req.getSession();			//req.getS 자동완성
		session.setAttribute("nick", nval);				//세션객체에 "nick"라는 이름으로 nval 변수값을 저장		//session.set 자동완성
		
		//현재 웹 애플리케이션의 서블릿컨텍스트객체 가져오기 (없으면 생성)
		ServletContext Context = getServletContext();	//SC 자동완성 
		Context.setAttribute("nick", nval);				//서블릿컨텍스트객체에 "nick"라는 이름으로 nval 변수값을 저장	//Context.set 자동완성
	
		//쿠키 가져오기 (없으면 생성)
		if("on".equals(rval)) {
			String enval = URLEncoder.encode(nval, "UTF-8");	// 한글, 특수문자 포함시 인코딩 필수(쿠키 값을 인코딩)
			Cookie c = new Cookie("nick", enval);				// new cook 자동 완성
			c.setMaxAge(60*5);									// 쿠키 유효기간(초) 설정 (0은 즉시 삭제, 음수는 웹브라우저 종료시 삭제)
//			c.setDomain("도메인");								//지정한 도메인과 하위 도메인으로 요청을 전송할 때만 쿠키 포함 ("도메인" 주소값)
//			c.setPath("경로");									//지정한 경로의 하위 경로로 요청을 전송할 때만 쿠키 포함
//			c.setHttpOnly(true);								//true로 설정하면 자바스크립트로 쿠키 접근 불가
//			c.setSecure(true);									//true로 설정하면, https:// 와 같은 보안프로토콜 사용시에만 쿠키 포함
			resp.addCookie(c);									// 웹브라우저가 쿠키를 저장하도록 응답에 포함	//resp.add 자동 완성
		}
		
		resp.setContentType("text/html");	//응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정)	//resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8");	//응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		PrintWriter out = resp.getWriter();	//응답객체에 무언가를 쓸수있는 객체를 가져오는거, 응답객체에 내용을 쓸 수 있는 Writer 가져오기
		out.print("<!DOCTYPE html>                     ");
		out.print("<html>                              ");
		out.print("<head>                              ");
		out.print("<meta charset='UTF-8'>              ");
		out.print("<title>SAVE</title>                 ");
		out.print("</head>                             ");
		out.print("<body>                              ");
		out.print("	<h1> 저장 완료 (안녕!! SAVE) </h1>	   ");
		out.print("</body>                             ");
		out.print("</html>                             ");
                                                    	
	}
}
