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
@WebServlet("/member/add2.do")
public class MemAddServlet2 extends HttpServlet {
	
//	{
//		// 서블릿이 만들어질때 한번만 실행되는
//		// 애플리케이션에 JDBC 사용 전에 최초 1번은 JDBC 드라이버 클래스를 메모리에 로드 필요
//				try {
//					Class.forName("oracle.jdbc.OracleDriver");
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//	}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		
		req.setCharacterEncoding("UTF-8");
		
		String memId = req.getParameter("memId");		//파라미터 값으로
		String memPass = req.getParameter("memPass");
		String memName = req.getParameter("memName");
		String memPoint = req.getParameter("memPoint");
		int memPoints = Integer.parseInt(req.getParameter("memPoint"));
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소	//@자신의 주소(호스트):포트번호:서비스이름
		String user ="web";		//데이터베이스 접속 아이디
		String password ="web01";		//데이터베이스 접속 비밀번호
		
		String sql = "INSERT INTO MEMBER ( mem_id ,MEM_PASS, MEM_NAME, MEM_POINT )"		//SQL 문
					+ "VALUES ( ?, ?, ?, ? )";							//*중요* 안에 세미콜론은 없어도 됨
		
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
			pstmt.setString(2, memPass);	//2번째 ?에 memPass 값을 넣기
			pstmt.setString(3, memName);	//3번째 ?에 memName 값을 넣기
			pstmt.setInt(4, memPoints);		//4번째 ?에 memPoint 값을 넣기
			
			//SQL문 실행 (INSERT, UPDATE, DELETE 문 실행은 executeUpdate()메서드 사용
			n = pstmt.executeUpdate();                                             
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
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
		
		System.out.println( n + "명의 회원 추가 성공");
		
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
