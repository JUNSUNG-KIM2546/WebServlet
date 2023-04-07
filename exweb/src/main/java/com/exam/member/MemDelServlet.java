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
	
	{
		// 서블릿이 만들어질때 한번만 실행되는
		// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
				try {
					Class.forName("oracle.jdbc.OracleDriver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	}
	String url ="jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소	//@자신의 주소(호스트):포트번호:서비스이름
	String user ="web";		//데이터베이스 접속 아이디
	String password ="web01";		//데이터베이스 접속 비밀번호
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		req.setCharacterEncoding("UTF-8");
		
		resp.setContentType("text/html"); // 응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정) //resp.setCon 치고 자동 완성
		resp.setCharacterEncoding("UTF-8"); // 응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공

		PrintWriter out = resp.getWriter();
		
		String memId = req.getParameter("memId");		//파라미터 값으로
		
		//SQL 문
		String sql = "DELETE FROM MEMBER WHERE mem_id = ? ";		//*중요* 안에 세미콜론은 없어도 됨	
					
		
		int n = 0;	//반환값은 SQL문 실행으로 영향받은 레코드(row) 수
		
		// try () 내부에 선언된 변수의 값은
		// try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try (	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);		//SQL 문 넣음
			) 
			{ 
			//pstmt 명령문 객체에 담겨 있는 SQL문의 ?에 값을 채워넣기
			//채워넣는 값의 타입에 따라서 set타입명() 메서드 사용
			pstmt.setString(1, memId);		//1번째 ?에 memId 값을 넣기
			
			//SQL문 실행 (INSERT, UPDATE, DELETE 문 실행은 executeUpdate()메서드 사용
			n = pstmt.executeUpdate();                                             
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		resp.sendRedirect(req.getContextPath() + "/member/list2.do");	// 삭제 후 바로 리스트 창으로 가라
		
		System.out.println( n + "명의 회원 삭제 성공");
		
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
