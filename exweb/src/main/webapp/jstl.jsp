<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%> 
<!-- JSTL core 태그라이브러리에서 제공하는 태그를 c 접두어를 붙여서 사용 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>JSTL</title>
</head>
<body>
	<h1>코어태그</h1>
	- c: set : pageContext, request, session, servletContext에 속성 저장
	<%-- <%pageContext.setAttribute("score", 80);%> --%>
	<c:set scope="page" var="score" value="${80}">
	</c:set>
	${pageScope.score }
	<br>
	<br> - c: remove : pageContext, request, session, servletContext에
	속성 삭제
	<%-- <c:remove scope="page" var="score"/> --%>
	${pageScope.score }
	<br>
	<br> - 조건문
	<br>
	<c:if test="${score>60}">통과</c:if>
	<c:choose>
		<c:when test="${score>=90}">최우수</c:when>
		<c:when test="${score>=80}">우수</c:when>
		<c:otherwise>보통</c:otherwise>
	</c:choose>
	<br>
	<br> - 반복문
	<br>
	<%
	for (int num = 1; num <= 10; num += 2)
		out.print(num);
	%><br>
	<c:forEach var="num" begin="1" end="10" step="2">${num}</c:forEach>
	<br>
	<%
	String[] arr = { "A", "B", "C" };
	pageContext.setAttribute("pa", arr);
	for (String s : arr) {
		out.print(s);
	}
	%>
	<c:forEach var="s" items="${pa}">${s}</c:forEach>
	<ul>
		<c:forEach var="s" items="${pa}" varStatus="stat">
			<li>
				${s} == ${stat.current} 현재값,
				${stat.index} 몇 번째 반복인지(0부터),
				${stat.count} 몇 번째 반복인지(1부터),
				${stat.first} 첫 번째 반복인지 여부,
				${stat.last} 마지막 반복인지 여부
				${stat.begin} 태그의 begin 속성값,
				${stat.end} 태그의 end 속성값,
				${stat.step} 태그의 step 속성값
			</li>
		</c:forEach>
	</ul>
	<c:forTokens var="tk" items="${'a,b:c,d'}" delims=",:">[${tk}]</c:forTokens><br><br>
	
	- 출력
	<% pageContext.setAttribute("str", "<h1>제목</h1>"); %>
	${str}
	<c:out value="${str}"/><br><br>
	
	- 주소처리<br>
	<a href="menu.jsp">메뉴로 이동</a>
	같은 디렉토리에 있기 때문에 가능(상대경로)<br>
	<a href="<%=request.getContextPath()%>">메뉴로 이동</a><br>
	<a href="${pageContext.request.contextPath}">메뉴로 이동</a><br>
	<c:set scope="page" var="cp" value="${pageContext.request.contextPath}"/><br>
	<a href="${cp }/menu.jsp">메뉴로 이동</a><br>
	
	<a href="<c:url value="/menu.jsp"/>">메뉴로 이동</a><br><br>

	- 다른 JSP 파일을 포함<br>
	<%@ include file="/menu.jsp" %>
	<jsp:include page="/menu.jsp" />
	<c:import url="/menu.jsp"/>
	<%-- <c:import url="http://google.com"/> --%>
	
	<%-- 	<% response.sendRedirect(request.getContextPath() + "/menu.jsp"); %>
 	<c:redirect url="/menu.jsp"></c:redirect> --%>
 	
 	- 주소 생성시 파라미터 추가
 	<c:url value="/hello.jsp">
 		<c:param name="x">abc</c:param>
 		<c:param name="y">def</c:param>
 	</c:url>
	<br><br> 	
 	- 예외처리
 	<c:catch var="e">
 		<%int a = 5/0; %>
 	</c:catch>
 	${e.message}
 	
 	<h1>국제화/포맷팅</h1>
 	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<% pageContext.setAttribute("d", new Date());%>
	${d} <br>
	자바 Date객체를 원하는 형태의 문자열로 변환 :
	<fmt:formatDate value="${d}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/> <br>
	날짜 시간 문자열을 자바 Date객체로 변환 : 
	<fmt:parseDate value="2023/05/04 12:34:56" pattern="yyyy/MM/dd HH:mm:ss" var="d2"/> <br>
	${d2}<br>
	
	<% pageContext.setAttribute("n", 12345.67);%>
	${n} <br>
	숫자를 원하는 형태의 문자열로 변환 :<br>
	<fmt:formatNumber value="${n}" pattern="###,###.###"/> <br>
	<fmt:formatNumber value="${n}" pattern="000,000.000"/> <br>
	<fmt:formatNumber value="${n}" pattern="#,###.#"/> <br>
	<fmt:formatNumber value="${n}" pattern="0,000.0"/> <br>
	
	숫자 문자열을 숫자로 변환 : 
	<fmt:parseNumber value="12,345.67" pattern="###,###.###" var="n2"/> <br>
	${n2} <br><br>
	
	현재 JSP파일에서 JSTL 국제화 태그가 사용할 로케일 강제 지정
	지정하지 않으면, Accept-Language 요청 헤더 값 사용)
	<fmt:setLocale value="en_US"/>
	<fmt:formatDate value="${d}" type="both"  dateStyle="full" timeStyle="full"/>
	<fmt:formatNumber value="${n}" type="currency" />
	
	
	메세지를 저장한 property파일이 "클래스패스/폴더명/번들명_언어_국가.properties"일 떄,
	basename은 "폴더명.번들명"
	<fmt:setBundle basename="msg" var="mb"/>
	<fmt:message bundle="${mb}" key="str"/>
	
	<fmt:message bundle="${mb}" key="str2">
		<fmt:param value="JSP"/>
		<fmt:param value="!!!"/>
	</fmt:message>
	
	<h1>JSTL functions</h1>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
	<br> ${fn:length("aBcD")} <%="aBcD".length()%> ${"aBcD".length()} 
	<br> ${fn:contains("aBcD","Bc")} <%="aBcD".contains("Bc")%> ${"aBcD".contains("Bc")}   
	<br> ${fn:containsIgnoreCase("aBcD","bC")}  <%="aBcD".toLowerCase().contains("bC".toLowerCase())%> ${"aBcD".toLowerCase().contains("bC".toLowerCase())}
	<br> ${fn:startsWith("aBcD","aB")} <%="aBcD".startsWith("aB")%> ${"aBcD".startsWith("aB")}
	<br> ${fn:endsWith("aBcD","cD")} <%="aBcD".endsWith("cD")%> ${"aBcD".endsWith("cD")}
	<br> ${fn:escapeXml("<h1>제목</h1>")} <c:out value="<h1>제목</h1>" /> 
	<br> ${fn:indexOf("aBcD","Bc")} <%="aBcD".indexOf("Bc")%> ${"aBcD".indexOf("Bc")} 
	<%
		String[] arr1 = {"A","B","C"}; 
		pageContext.setAttribute("pa", arr);
	// 	EL에서 String.join() 사용시, Iterable 파라미터에 배열이 타입이 맞지 않는 오류 발생 (버그인듯) 
	// 	배열이 아닌 ArrayList 객체를 사용하면 정상실행 
		pageContext.setAttribute("pl", new java.util.ArrayList<String>(java.util.Arrays.asList(arr))); 
	%>
	<br> ${fn:join(pa,"::")} <%=String.join("::", arr)%> ${String.join("::", pl)} ${String.join("::", ["A","B","C"])}       
	<br> ${(fn:split("a,B:c,D",",:"))[2]} <%="a,B:c,D".split("[,:]")[2]%> ${"a,B:c,D".split("[,:]")[2]} 
	<br> ${fn:replace("aBcDBc","Bc","efg")} <%="aBcDBc".replace("Bc","efg") %> ${"aBcDBc".replace("Bc","efg")}
	<br> ${fn:substring("aBcD", 1, 2)} <%="aBcD".substring(1,2)%> ${"aBcD".substring(1,2)}
	<br> ${fn:substringAfter("aBcD", "Bc")}  <%="aBcD".substring( "aBcD".indexOf("Bc") + "Bc".length() )%>  ${"aBcD".substring("aBcD".indexOf("Bc")+"Bc".length())} 
	<br> ${fn:substringBefore("aBcD", "Bc")} <%="aBcD".substring(0, "aBcD".indexOf("Bc") )%> ${"aBcD".substring(0,"aBcD".indexOf("Bc"))}
	<br> ${fn:toLowerCase("aBcD")} <%="aBcD".toLowerCase()%> ${"aBcD".toLowerCase()}
	<br> ${fn:toUpperCase("aBcD")} <%="aBcD".toUpperCase()%> ${"aBcD".toUpperCase()}
	<br> [${fn:trim("   aB cD  ")}] [<%="   aB cD  ".trim()%>] [${"   aB cD  ".trim()}]
	
</body>
</html>