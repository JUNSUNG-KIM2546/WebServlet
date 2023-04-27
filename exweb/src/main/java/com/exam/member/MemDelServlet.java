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


//웹브라우저에서 
//http://localhost:8000/exweb/member/del.do?memId=삭제할회원아이디
//로 요청을 보내면, 지정한 회원 정보를 데이터베이스에서 삭제하고
//"명의 회원 삭제 성공" 메시지와 "회원목록으로 이동" 링크를 화면에 출력
//회원 삭제 SQL문
//DELETE 
//FROM MEMBER  
//WHERE MEM_ID = '삭제할회원아이디';

@WebServlet("/member/del.do")
public class MemDelServlet extends HttpServlet {
	private MemberDao memberDao = new MemberDaoBatis();	//클래스 객체를 만들어줌 (참조할수 있게)
	
//		// 서블릿이 만들어질때 한번만 실행되는
//		// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
//				try {
//					Class.forName("oracle.jdbc.OracleDriver");
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
//		req.setCharacterEncoding("UTF-8");
		
//		PrintWriter out = resp.getWriter();
		
		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
	
		String memId = req.getParameter("memId");		//파라미터 값으로
		
		int n = memberDao.deleteMember(memId);	//클래스 참조하라
		
		System.out.println( n + "명의 회원 삭제 성공");
		
		resp.sendRedirect(req.getContextPath() + "/member/list2.do");	// 삭제 후 바로 리스트 창으로 가라
			
//		out.print("<!DOCTYPE html>                     ");
//		out.print("<html>                              ");
//		out.print("<head>                              ");
//		out.print("<meta charset='UTF-8'>              ");
//		out.print("<title>회원 삭제</title>                ");
//		out.print("</head>                             ");
//		out.print("<body>                              ");
//		out.print("	<h1>" + n + "명의 회원 삭제" + "</h1> ");
//		out.print("<a href='" + req.getContextPath() + "/member/list2.do'>회원 목록</a>");
//		out.print("</body>                             ");
//		out.print("</html>                             ");
	}



}
