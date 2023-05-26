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


//회원정보 추가 후 화면에 "회원목록으로 이동" 링크를 추가하고
//그 링크를 클릭하면, 회원목록  화면으로 이동하도록
//MemAddServlet2 클래스를 변경하세요.
@WebServlet("/member/edit.do")	//수정페이지
public class MemEditServlet extends HttpServlet {
	private MemberDao memberDao = new MemberDaoBatis();	//클래스 객체를 만들어줌 (참조할수 있게)
	
	// MemAddForm, MemAddServlet 하나로 합쳐서 하는 방법
	@Override	//겟방식
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String memId = req.getParameter("memId");
		MemberVo vo = memberDao.selectMember(memId);
		req.setAttribute("mbvo", vo);
		
		req.getRequestDispatcher("/WEB-INF/views/member/memEdit.jsp").forward(req, resp);
	}
	
	@Override	//포스트방식
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");	//필터로 이동
		
		MemberVo vo = new MemberVo();
		//파라미터 값으로
		vo.setMemId(req.getParameter("memId"));
		vo.setMemPass(req.getParameter("memPass"));
		vo.setMemName(req.getParameter("memName"));
		vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint")));
		
		int n = memberDao.updateMember(vo);	//클래스 참조하라
		
		System.out.println(n + "명의 회원 수정");

		// 수정 후 목록창으로 이동
		resp.sendRedirect(req.getContextPath() + "/member/list2.do");
				
	}

}
