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
@WebServlet("/member/login.do")	//수정페이지
public class MemLoginServlet extends HttpServlet {
	private MemberDao memberDao = new MemberDaoBatis();	//클래스 객체를 만들어줌 (참조할수 있게)
	
	// MemAddForm, MemAddServlet 하나로 합쳐서 하는 방법
	@Override	//겟방식
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String memId = req.getParameter("memId");
//		MemberVo vo = memberDao.selectMember(memId);
//		req.setAttribute("mbvo", vo);
		
		req.getRequestDispatcher("/WEB-INF/views/member/memLogin.jsp").forward(req, resp);
	}
	
	@Override	//포스트방식
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");	//필터로 이동
		
		//데이터베이스에 입력한 아이디와 비번이 있는지 확인 후 로그인
		MemberVo vo = new MemberVo();
		//파라미터 값으로
		vo.setMemId(req.getParameter("memId"));
		vo.setMemPass(req.getParameter("memPass"));
		
		MemberVo lo = memberDao.selectLogin(vo);
		
		// 로그인 실패
		if (lo == null) {
			// 로그인 실패시 다시 로그인창으로 이동
			resp.sendRedirect(req.getContextPath() + "/member/login.do");
			
			System.out.println(lo + " 회원 로그인 실패");
		}
		// 로그인 성공
		else {
			// 세션객체(일정시간까지 유지, 사용자별로 구별가능), 서블릿컨텍스트객체(서버 종료시까지 유지, 한사람이 접속하면 모든사람이 사용가능)
			// 세션객체
			HttpSession session = req.getSession();
			session.setAttribute("loginUser", lo); //세션에 로그인 저장
			// 로그인 후 목록창으로 이동
			resp.sendRedirect(req.getContextPath() + "/member/list2.do");
			
			System.out.println(lo + " 회원 로그인 성공");
		}
				
	}

}
