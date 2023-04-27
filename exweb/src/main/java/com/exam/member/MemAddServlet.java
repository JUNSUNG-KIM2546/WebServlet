package com.exam.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/add.do")
public class MemAddServlet extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	
		String memId = "a004";
		String memPass = "1234";
		String memName = "호랭이";
		int memPoint = 90;
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소	//@자신의 주소(호스트):포트번호:서비스이름
		String user ="WEB";		//데이터베이스 접속 아이디
		String password ="WEB01";		//데이터베이스 접속 비밀번호
		
		String sql = "INSERT INTO MEMBER ( mem_id ,MEM_PASS, MEM_NAME, MEM_POINT )"		//SQL 문
					+ "VALUES ( ?, ?, ?, ? )";							//*중요* 안에 세미콜론은 없어도 됨
		
		// try () 내부에 선언된 변수의 값은
		// try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try (	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);		//SQL 문 넣음
			) { 
			//pstmt 명령문 객체에 담겨 있는 SQL문의 ?에 값을 채워넣기
			//채워넣는 값의 타입에 따라서 set타입명() 메서드 사용
			pstmt.setString(1, memId);		//1번째 ?에 memId 값을 넣기
			pstmt.setString(2, memPass);	//2번째 ?에 memPass 값을 넣기
			pstmt.setString(3, memName);	//3번째 ?에 memName 값을 넣기
			pstmt.setInt(4, memPoint);		//4번째 ?에 memPoint 값을 넣기
			
			//SQL문 실행 (INSERT, UPDATE, DELETE 문 실행은 executeUpdate()메서드 사용
			int n = pstmt.executeUpdate();	//반환값은 SQL문 실행으로 영향받은 레코드(row) 수
			System.out.println( n + "명의 회원 추가 성공");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		//finally {	//자바7이상부터 안쓰는 방법
						//pstmt.close();	//명령문 객체가 사용하던 자원 반납
						//conn.close();		//로그아웃
					 //}
	}

}
