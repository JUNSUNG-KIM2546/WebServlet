<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>회원 정보 변경</title>
</head>
<body>
<!-- 회원목록에서 아이디를 클릭하면, MemEditServlet 과 memEdit.jsp 가 순차적으로 실행되어 회원정보변경 화면이 브라우저에 출력되도록 구현 -->
<h1>회원정보수정</h1>
<form action='${pageContext.request.contextPath}/member/edit.do' method='post'>
		아이디 : 	 <input type='text' name='memId' value='${mbvo.memId}'><br>
		비밀번호 : <input type='password' name='memPass' value='${mbvo.memPass}' /><br>
		이름 : 	 <input type='text' name='memName' value='${mbvo.memName}' /><br>
 		뽀인트 : 	 <input type='number' name='memPoint' value='${mbvo.memPoint}' /><br>
		<input type='submit'/>
</form>

	<h2>=================================================================</h2> 
	<h2>회원목록으로 가기</h2>
<a href='${pageContext.request.contextPath}/member/list2.do' method='post'>
		<button type='button'> 회원 목록 </button>
</a>
		
</body>
</html>                 	