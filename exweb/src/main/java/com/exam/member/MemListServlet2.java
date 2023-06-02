package com.exam.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//회원목록 화면에 "회원추가" 링크를 추가하고,
// 그 링크를 클릭하면, 회원정보를 입력하는 폼 화면으로 이동하도록
// MemListServlet2 클래스를 변경하세요.

//회원정보 추가 후 화면에 "회원목록으로 이동" 링크를 추가하고
// 그 링크를 클릭하면, 회원목록  화면으로 이동하도록
//MemListServlet2 클래스를 변경하세요.

//회원목록의 각 회원정보 옆에 "삭제" 링크를 출력하고,
// 링크를 클릭하면 해당 회원이 삭제되도록
// MemListServlet 클래스를 변경하세요.

// 05.26
// 로그인하지 않은 상태에서 회원목록 페이지에 접속하면, 로그인 화면으로 이동하도록 구현

@WebServlet("/member/list2.do")	//목록페이지
public class MemListServlet2 extends HttpServlet {
	private MemberService memberService = MemberServiceImpl.getInstacne();	//클래스 객체를 만들어줌 (참조할수 있게)
	
//		// 서블릿이 만들어질때 한번만 실행되는
//		// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
//				try {
//					Class.forName("oracle.jdbc.OracleDriver");
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		List<MemberVo> List = memberService.selectMemberList();	
		// 서블릿파일 + JSP파일
		
		req.setAttribute("memberList", List);
		
		req.getRequestDispatcher("/WEB-INF/views/member/memList.jsp").forward(req, resp);
		
		System.out.println( "접속 완료");
		
//		req.setCharacterEncoding("UTF-8");
//		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
//		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
//		PrintWriter out = resp.getWriter();
//		out.print("<!DOCTYPE html>                     ");
//		out.print("<html>                              ");
//		out.print("<head>                              ");
//		out.print("<meta charset='UTF-8'>              ");
//		out.print("<title>회원 목록</title>               ");
//		out.print("</head>                             ");
//		out.print("<body>                              ");
//		out.print("<h1> 회원 목록 </h1>");
//				
//				for (MemberVo vo : List) {	
//					System.out.println(vo.getMemId() + ":" + vo.getMemPass() + ":" + vo.getMemName() + ":" + vo.getMemPoint());				
//					out.print("	<h2> 회원정보 = " + vo.getMemId() + " : " + vo.getMemPass() + " : " + vo.getMemName() + " : " + vo.getMemPoint());
//					out.print("<a href='" + req.getContextPath() + "/member/del.do?memId=" + vo.getMemId() + "'><button type='button'> 삭제 </button></a>");
//				}
//			
//		out.print("</h2>");
//		out.print("<h2>==================================== 회원 추가 ====================================</h2>");
//		out.print("<a href='" + req.getContextPath() + "/member/addform.do' method='post'>");
//		out.print("		<button type='button'> 회원 추가하기 </button>");
//		out.print("</a>");
//		out.print("<h2>==================================== 회원 삭제 ====================================</h2>");
//		out.print("<a href='" + req.getContextPath() + "/member/delform.do' method='post'>");
//		out.print("		<button type='button'> 회원 삭제하기 </button>");
//		out.print("</a>");
//		out.print("</body>                             ");
//		out.print("</html>                             ");		
	}


}

