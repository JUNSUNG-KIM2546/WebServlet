<!-- 에러페이지인지 아닌지 지정 isErrorPage="ture" -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>
	<!-- ERROR 처리(널포인트 인셉션) -->
	<!-- 오류 발생시 실행되는 JSP 파일에서 page 디렉티브의 isErrorPage 속성값을 true로 설정하면, 발생한 예외 객체를 저장하고 있는 exception 내장객체를 사용가능 -->
	<h2>발생한 예외 객체 : <%=exception%></h2>
	<h2>발생한 예외 객체(메세지) : <%=exception.getMessage()%></h2>
	
	<h2>발생한 예외 객체 : ${pageContext.exception}</h2>
	<h2>발생한 예외 객체(메세지) : ${pageContext.exception.message}</h2>
	
	<h1>넌 내게 null값을 줬어</h1>
	<img alt="" src="${pageContext.request.contextPath}/img/null.jpg">
	<img alt="" src="/exweb/img/null.jpg">
	<img alt="" src="./img/null.jpg">
	<img alt="" src="img/null.jpg">
	<img alt="" src="img/Wow-gif.gif">
	
	<!-- jsp파일 주소가 http://localhost/exweb/err404.jsp 이다 -->
	<!-- 이미지 파일은 http://localhost/exweb/img/이미지.jpg 이다 -->
</body>
</html>