<%@page import="com.exam.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>J S P</title>
</head>
<body>
	<h1>J S P Hello</h1>
	
<!-- HTML 주석 -->	
<%-- JSP 주석 --%>

<%-- JSP 구성요소 --%>
<%-- 	- 디렉티브 : page(현재JSP파일전체에 대한 설정), incude(다른JSP파일 포함), taglid(태그하이브러리)
			<%@ 디렉티브명 속성명="속성값" %>
		- 스크립트요소
			-스크립트릿 : <% 서블릿의 service메서드 내부에 들어갈 자바 코드 %>
			-표현식 : <%= 현재위치에 결과값을 출력할 자바 코드 %>
			-선언부 : <%! 서블릿의 service메서드 외부에 들어갈 자바 코드 %>
			-주석	
		- 액션태그 : 자주 사용하는 자바 코드를 대체할 수 있는 태그
		- EL
		- 커스텀태그 --%>

<%
//	변수 선언없이 사용가능한 내장객체(기본객체) :
//	request(요청객체), response(응답객체), session(세션객체), application(서블릿컨텍스트객체)
//	out(응답출력스트림), config(서블릿설정), pageContext(현재 JSP파일에 대한 모든 정보를 담은 객체)
//	page(현재 JSP 서블릿객체), exception(발생한예외, 에러페이지에서만 사용가능)
	out.println("출력할내용");
	out.println(session == request.getSession());
	out.println(application == getServletContext());
	out.println(config == getServletConfig());
//	pageContext 객체에 현재 JSP 파일에서만 사용가능한 데이터를 속성으로 저장 가능
	pageContext.setAttribute("pa", "pv");
	out.print( pageContext.getAttribute("pa") );
//	pageContext 객체에는 다른 내장객체들이 모두 저장되어 있다
	out.print( request == pageContext.getRequest() );
	out.print( response == pageContext.getResponse() );
	out.print( session == pageContext.getSession() );
	out.print( application == pageContext.getServletContext() );
%>

<%!	//JSP파일이 최초 실행될 때 한번만 실행할 자바 코드 (선언부) 
	int global = 0;
%>

<%	//JSP파일이 실행 될 때마다 한번씩 반복 실행할 자바 코드
	int local = 0;
	out.print("local : " + ++local);
	out.print("global : " + ++global);
	
%>
<hr>
표현식 : <% out.print(local); %> <%= local %>
<hr>
표현식 : <% out.print(global); %> <%= global %>
<hr>
액션태그
<%
	MemberVo vo = (MemberVo)pageContext.getAttribute("m");
	if(vo==null){
		vo = new MemberVo();
		pageContext.setAttribute("m",vo);
	}
	vo.setMemId("A003");
	out.print(vo.getMemId());
%>
<jsp:useBean id="m" class="com.exam.member.MemberVo" scope="page"></jsp:useBean>
<jsp:setProperty property="memId" name="m" value="A003"/>
<jsp:getProperty property="memId" name="m" />

<%
//	request.getRequestDispatcher("/menu.jsp").forward(request, response);
	request.getRequestDispatcher("/menu.jsp").include(request, response);
%>
<%-- <jsp:forward page="">.menu.jsp</jsp:forward>--%>
<jsp:include page="/menu.jsp"></jsp:include>

</body>
</html>