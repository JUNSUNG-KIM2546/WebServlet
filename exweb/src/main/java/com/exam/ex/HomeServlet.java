package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

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
//1) 요청객체
//		- 요청 1개마다 1개의 요청객체 생성, 요청처리가 끝나면 소멸
//		- 하나의 요청을 처리하기 위해 사용되는 서블릿들간의 데이터 공유 (forward, include) (다른 서블릿에서 포워딩이나 인크우딩 해야 제한적으로 사용 가능)
//2) 세션객체
//		- 클라이언트(웹브라우저) 1개당 1개의 세션 객체 생성 
//		- 클라이언트 종료시, 서버 종료시, 일정시간동안 요청이 없을때 세션객체 소멸
//		- 클라이언트(사용자, 웹브라우저)별로 각각 유지해야하는 데이터 공유
//3) 서블릿컨텍스트 객체
//		- 웹 애플리케이션 전체에서 1개의 서블릿컨텍스트 객체만 생성
//		- 서버(톰캣) 시작될 때 생성, 서버가 종료될 때 소멸
//		- 모든 사용자와 모든 서블릿들이 데이터 공유
//* 요청객체, 서블릿컨텍스트객체 모두 도일한 메서드로 메서드로 데이터 저장 및 조회
//	- 객체.setAttribute("속성명", 속성값);	= 속성값 저장
//	- 객체.setAttribute("속성명");			= 속성값 읽기
//	- 객체.removeAttribute("속성명");		= 속성 삭제

//2.클라이언트(웹브라우저)에 저장
//1) 쿠키
//		- 웹브라루저에 데이터를 이름-값 쌍으로 저장
//		- 기본적으로 쿠키를 저장한 웹사이트(도메인)와 동일한 웹사이트로 요청을 보낼때 요청헤더에 쿠키를 자동으로 포함(변경 가능)
//		- 만료기산을 설정하면, 웹브라우저가 종료되더라도 쿠키 값 유지 가능
//		- 웹브라우저에서 접근하여 사용 가능하기 때문에 보안상 위험 존재
//		- 쿠키의 이름과 값은 쉽표, 세미콜론, 공백 등 특수문자와 한글 등 비영어권 문자 사용 불가
//		- 일반적으로 쿠키이름은 영문자와 숫자만 사용, 쿠키값은 인코딩/디코딩하여 사용
//2) HTML5에서는 sessionStorage, localStorage, indexedDB도 사용 가능

@WebServlet("/home") //"/home" 파일을 달라는 요청이 오면 이 서블릿 클래스를 실행하라
public class HomeServlet extends HttpServlet {		//상속 : extends HttpServlet

	@Override	//상속받은 것을 덮어씌우라는 명령		//컴퓨터한테 적용하라는 주석
	protected void service(HttpServletRequest req	/*요청*/, HttpServletResponse resp	/*응답*/) throws ServletException, IOException {
		System.out.println("HomeServlet 실행! 얍얍얍!");
		resp.setContentType("text/html");	//응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정)	//resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8");	//응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		PrintWriter out = resp.getWriter();	//응답객체에 무언가를 쓸수있는 객체를 가져오는거, 응답객체에 내용을 쓸 수 있는 Writer 가져오기	
		out.print("<!DOCTYPE html>                     ");
		out.print("<html>                              ");
		out.print("<head>                              ");
		out.print("<meta charset='UTF-8'>              ");
		out.print("<title>HOME</title>                 ");
		out.print("</head>                             ");
		out.print("<body>                              ");
		out.print("	<h1> HOME (안녕!! HOME) </h1> "		);
		
		//SaveServlet에서 저장한 데이터를 읽어서 출력
		HttpSession session = req.getSession();
		String nickName = (String) session.getAttribute("nick");	//세션객체에 스트링타입"nick" 라는 이름으로 저장된 데이터 읽기
		out.print("	<h3> 세션에 저장된 닉네임 = " + nickName + "</h3> ");
		
		//SaveServlet에서 저장한 데이터를 읽어서 출력
		ServletContext context = getServletContext();
		String contextNick = (String) context.getAttribute("nick");	//서블릿컨텍스트객체에 스트링타입"contextNick" 라는 이름으로 저장된 데이터 읽기
		out.print("	<h3> 서블릿컨텍스트에 저장된 닉네임 = " + contextNick + "</h3> ");
		
		//요청헤더에 포함된 쿠키 값들을 읽기
		Cookie[] cookies = req.getCookies();						//쿠키 헤더값을 가져온다
		//foreach문 사용으로 뽑아온다
		for (Cookie c : cookies) {
			if("nick" .equals(c.getName())) {						//쿠키이름이 "nick"인 경우 출력
				String v = URLDecoder.decode(c.getValue(),"UTF-8");	//인코디 한 값을 디코딩으로 바꿔주는거
				out.print("	<h3> 쿠키에 저장된 닉네임 = " + v + "</h3> ");
			}
		}

		out.print("</body>                             ");
		out.print("</html>                             ");
                                                    
		
	}
}
