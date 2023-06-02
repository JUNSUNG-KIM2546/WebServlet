<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERROR</title>
</head>
<body>
	<!-- ERROR 처리(404에러 페이지) -->
	<h1>니가 찾는 페이지는</h1>
	<img alt="" src="${pageContext.request.contextPath}/img/no.jpg">
	<img alt="" src="${pageContext.request.contextPath}/img/Wow-gif.gif">
	<!-- <img alt="" src="/exweb/img/no.jpg">
	<img alt="" src="./img/no.jpg">
	<img alt="" src="img/no.jpg">
	<img alt="" src="img/Wow-gif.gif"> -->
	
	<!-- jsp파일 주소가 http://localhost/exweb/err404.jsp 이다 -->
	<!-- 이미지 파일은 http://localhost/exweb/img/이미지.jpg 이다 -->
</body>
</html>