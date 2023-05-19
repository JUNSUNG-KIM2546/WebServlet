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
	
//		// 서블릿이 만들어질때 한번만 실행되는
//		// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
//				try {
//					Class.forName("oracle.jdbc.OracleDriver");
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
		
//		req.setCharacterEncoding("UTF-8");
		
		MemberVo vo = new MemberVo();
		
		//파라미터 값으로
		vo.setMemId(req.getParameter("memId"));	
		vo.setMemPass(req.getParameter("memPass"));
		vo.setMemName(req.getParameter("memName"));
		vo.setMemPoint(Integer.parseInt(req.getParameter("memPoint")));
		
		int n = memberDao.insertMember(vo);	//클래스 참조하라
		
		System.out.println(n + "명의 회원 수정");
		
		//회원 목록 출력
		//MemListServlet 실행!
		//forward 방법: 요청객체와 응답객체를 전달하면서 다른 서블릿을 실행
		//			   현재 서블릿에서는 더 이상 응답을 출력하지 않는다(점프해서 실행하고 끝난다
//		req.getRequestDispatcher("/member/list2.do").forward(req, resp);	//실행할 주소 입력
		
		//include 방법: 요청객체와 응답객체를 전달하면서 다른 서블릿을 실행
		//			   현재 서블릿의 출력 내용 중간에 지정한 서블릿의 출력 내용을 포함(실행 후 다시 돌아와서 실행한다) 공통적인 부분을 출력할때 사용한다.
//		req.getRequestDispatcher("/member/list2.do").include(req, resp);	//실행할 주소 입력
		
		//redirect 방법: 지정한 주소로 이동하라는 명령을 담은 응답을 웹브라우저에게 전송
		resp.sendRedirect(req.getContextPath() + "/member/list2.do");
				
//		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
//		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
//		PrintWriter out = resp.getWriter();
//		out.print("<!DOCTYPE html>                     ");
//		out.print("<html>                              ");
//		out.print("<head>                              ");
//		out.print("<meta charset='UTF-8'>              ");
//		out.print("<title>회원 생성</title>                ");
//		out.print("</head>                             ");
//		out.print("<body>                              ");
//		out.print("	<h1>" + n + "명의 회원 추가" + "</h1> ");
//		out.print("	<h2></h2> ");
//		out.print("	<h2>회원목록으로 가기</h2> ");
//		out.print("<form action='" + req.getContextPath() + "/member/list2.do' method='post'>");
//		out.print("<input type='submit'/>");
//		out.print("</form>");
//		out.print("</body>                             ");
//		out.print("</html>                             ");
	}

}
