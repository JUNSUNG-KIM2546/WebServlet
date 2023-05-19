<%@page import="com.exam.member.MemberVo"%>
<%@page import="com.exam.member.MemberDaoBatis"%>
<%@page import="com.exam.member.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
private MemberDao memberDao = new MemberDaoBatis();	//클래스 객체를 만들어줌 (참조할수 있게)
%>

<%
//request.setCharacterEncoding("UTF-8");	//필터로 이동

MemberVo vo = new MemberVo();

//파라미터 값으로
vo.setMemId(request.getParameter("memId"));	
vo.setMemPass(request.getParameter("memPass"));
vo.setMemName(request.getParameter("memName"));
vo.setMemPoint(Integer.parseInt(request.getParameter("memPoint")));

int n = memberDao.insertMember(vo);	//클래스 참조하라

System.out.println(n + "명의 회원 추가");

response.sendRedirect(request.getContextPath() + "/member/memList.jsp");
%>