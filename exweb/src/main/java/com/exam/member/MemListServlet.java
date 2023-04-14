package com.exam.member;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/list.do")
public class MemListServlet extends HttpServlet {
	
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
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";		//데이터베이스 서버 주소	//@자신의 주소(호스트):포트번호:서비스이름
		String user ="web";		//데이터베이스 접속 아이디
		String password ="web01";		//데이터베이스 접속 비밀번호
		
		String sql = "SELECT mem_ID,MEM_PASS,MEM_NAME,MEM_POINT FROM MEMBER";							//*중요* 안에 세미콜론은 없어도 됨
		
		// try () 내부에 선언된 변수의 값은
		// try-catch 블럭의 실행이 완료된 후 자동으로 close() 메서드 실행
		try (	
				//지정한 데이터베이스에 접속(로그인)
				Connection conn = DriverManager.getConnection(url, user, password);
				
				//해당 연결을 통해 실행할 SQL문을 담은 명령문 객체 생성
				PreparedStatement pstmt = conn.prepareStatement(sql);		//SQL 문 넣음
				
			) 
		
		{ 
			//SQL문 실행 (SELECT 문 실행은 executeQuery()메서드 사용
			ResultSet rs = pstmt.executeQuery();	//반환값은 조회 결과 레코드(row) 수
			
			// 처음 ResultSet 객체는 조회결과에서 첫 레코드(row) 이전을 가리키고 있음
			// .next() 메서드를 실행하면 다음 레코드를 가리키게 된다.
			// .next() 메서드는 다음 레코드가 있으면 true를 반환하고, 없으면 false를 반환하라
			while (rs.next())	
			{
				//컬럼값의 데이터타입에 따라서 get타입("컬럼명") 메서드를 사용하여 컬럼값 읽기
				String memId = rs.getString("mem_ID"); //현재 가리키는 레코드(row)의 "mem_id" 컬럼값 읽기
				String memPass = rs.getString("MEM_PASS"); //현재 가리키는 레코드(row)의 "MEM_PASS" 컬럼값 읽기
				String memNames = rs.getString("MEM_NAME"); //현재 가리키는 레코드(row)의 "MEM_NAME" 컬럼값 읽기
				int memPoint = rs.getInt("MEM_POINT"); //현재 가리키는 레코드(row)의 "MEM_POINT" 컬럼값 읽기	
				
				System.out.println(memId + ":" + memPass + ":" + memNames + ":" + memPoint);
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		//finally {	//자바7이상부터 안쓰는 방법
						//pstmt.close();	//명령문 객체가 사용하던 자원 반납
						//conn.close();		//로그아웃
					 //}
		
	}

}
