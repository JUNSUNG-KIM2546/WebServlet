package com.exam.ex;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//	서블릿 URL패턴(주소,경로) 지정 규칙
//	반드시 "/"(주소) 또는 "*."(확장자) 으로 시작
//	"*"은 0개 이상의 모든 문자열과 일치
@WebServlet("/hi.do")
public class HiServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//요청주소 뒤에 "?파라미터명=파라미터값&파라미터명=파라미터값&......"
		//형태로 추가로 전달할 파라미터들을 지정 가능
		//서블릿에서는 요청객체.getParameter("파라미터명") 명령문으로
		//원하는 파라미터의 값을 사용 가능
		// ?, =, & 등 기호는 못바꾼다.
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");	//응답내용의 데이터타입을 설정 (문자, 데이터 타입형식을 무엇인지 알려주고 지정)
			//응답내용을 쓸 때 사용할 문자인코딩 방식 지정(파이프가 객체를 가져오기 전에 인코딩 해야함) 웹브라우저에세 정보제공
		
		
		PrintWriter out = resp.getWriter();	//응답객체에 무언가를 쓸수있는 객체를 가져오는거, 응답객체에 내용을 쓸 수 있는 Writer 가져오기
		//out.print("Hello SERVLET");	//응답객체에 문자열을 출력
		//응답객체에 출력한 내용은 클라이언트(웹브라우저)로 전송된다.
		//문자를 프린트하면 텍스트형식 문자만 출력된다, 웹을 꾸밀려면 html를 사용해서 해야한다.
		String x = req.getParameter("user");
		
		out.print("<!DOCTYPE html>                     ");
		out.print("<html>                              ");
		out.print("<head>                              ");
		out.print("<meta charset='UTF-8'>              ");
		out.print("<title>HELLO</title>                ");
		out.print("</head>                             ");
		out.print("<body>                              ");
		out.print("	<h1>" + x + "님 환영합니다 </h1> ");
		out.print("</body>                             ");
		out.print("</html>                             ");
                                                    
	}
}

// "/hi.do?user=둘리" 로 요청을 보내면, 화면에 "둘리님 환영합니다" 라고 출력되고,
// "/hi.do?user=고길동" 으로 요청을 보내면, 화면에 "고길동님 환영합니다"라고 출력하시오.
// "HiServle의 내용을 변경하세요.

//	이클립스의 다이나믹웹프로젝트(톰캣)가 실행 중인 상태에서 
//	*.java 파일을 변경하면, 이클립스가 톰캣을 자동 재시작

//	src/main/webapp 폴더의 정적 리소스(*.html, *.css 등) 파일들을 변경하면
//	즉시 톰캣에 반영되므로 톰캣 재시작 없이 웹브라우저에서 새로고침만 하면 된다.

//	web.xml 등 설치파일 변경시에는, 수동으로 톰캣 재시작 필요
