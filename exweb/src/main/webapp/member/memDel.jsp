<%@page import="com.exam.member.MemberDaoBatis"%>
<%@page import="com.exam.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
private MemberDao memberDao = new MemberDaoBatis();	//클래스 객체를 만들어줌 (참조할수 있게)
%>

<%
String memId = request.getParameter("memId");		//파라미터 값으로

int n = memberDao.deleteMember(memId);	//클래스 참조하라

System.out.println( n + "명의 회원 삭제 성공");

response.sendRedirect(request.getContextPath() + "/member/memList.jsp");	// 삭제 후 바로 리스트 창으로 가라
%>