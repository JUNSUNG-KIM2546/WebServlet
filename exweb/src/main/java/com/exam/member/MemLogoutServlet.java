package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

// 1.
// 브라우저 주소창에 http://localhost/exweb/member/login.do 를 입력하여 접속하면, 
// LoginServlet 클래스와 Login.jsp 파일이 순차적으로 실행되어 브라우저에 로그인 화면이 출력되도록 구현

// 2.
// 로그인 화면에서 submit 버튼을 클릭하면, LoginServlet 클래스의 doPost가 실행되도록 구현
@WebServlet("/member/logout.do")	//수정페이지
public class MemLogoutServlet extends HttpServlet {
//	private MemberDao memberDao = new MemberDaoBatis();	//클래스 객체를 만들어줌 (참조할수 있게) 로그아웃 할 떄는 필요 없음
	
	// MemAddForm, MemAddServlet 하나로 합쳐서 하는 방법
	@Override	//겟방식
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그아웃 구현
		HttpSession session = req.getSession();
		
		// 3개중 하나만 사용
//		session.setAttribute("loginUser", null); //세션에 지정한 이름의 속성값을 null로
//		session.removeAttribute("loginUser");	//세션에서 지정한 이름의 속성을 삭제
		session.invalidate(); //세션객체를 제거(후 다시 생성), 모든 속성들도 함께 삭제
			
		resp.sendRedirect(req.getContextPath() + "/member/login.do");
		
		System.out.println(" 회원 로그아웃 성공 ");
				
	}

}
