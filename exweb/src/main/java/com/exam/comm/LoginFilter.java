package com.exam.comm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.exam.member.MemberVo;

//필터 : 서블릿의 실행 전후에 끼어들어가서 실행
//	-다수의 서블릿들이 수행하는 공통작업을 실행할때 사용
//	-Filter 인터페이스를 구현하여 필터 클래스 정의
//web.xml 에 <filter> 태그로 등록하거나, 클래스에 @WebFilter 적용

//05.26
// 로그인 하지 않은 상태는 로그인창으로 가지게 하는 필터
public class LoginFilter implements Filter {
	private List<String> whiteList = new ArrayList<String>();
	
	// 필터가 처음 생성됐을 때 1번 실행 (초기화 메소드)
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		whiteList.add("/member/login.do");
		whiteList.add("/member/add2.do");
	}
	
	@Override	//ServletRequest 상위 , HttpServletRequest 하위
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 필터의 경로에 맞는 요청이 올때마다 한번씩 실행
		HttpServletRequest req = (HttpServletRequest) request; // 형 변환
		HttpServletResponse resp = (HttpServletResponse) response; // 형 변환
		
		System.out.println("URI : " + req.getRequestURI());
		System.out.println("URL : " + req.getRequestURL());
		String reqPath = req.getRequestURI().substring( req.getContextPath().length() );
		System.out.println("reqPath: " + reqPath);
		
		if (whiteList.contains( reqPath ) == false) { //false 일때 실행 해야함
			// 요청보낸 사용자의 세션을 가져와서
			HttpSession session = req.getSession();
			//세션에 로드인정보를 꺼내와서
			MemberVo vo = (MemberVo) session.getAttribute("loginUser"); //세션에 로그인 저장
			//로그인 정보가 없다면, 로그인 페이지로 이동
			if (vo == null) { // 로그인 실패
				// 로그인 실패시 다시 로그인창으로 이동
				resp.sendRedirect(req.getContextPath() + "/member/login.do");
				System.out.println("로그인을 해주세요");
				return;
			}
		}
		
		//이후 실행될 필터 또는 서블릿들을 실행 -----여기 기준으로 서블릿 실행전과 실행후로 나뉨-----
		chain.doFilter(request, response);
		
	}
	
//	// 필터가 처음 생성됐을 때 1번 실행 (초기화 메소드)
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		
//		System.out.println("CharEncFilter init() 실행");
//		
//		enc = filterConfig.getInitParameter("encoding");	// web.xml에서 설정한 초기화 변수명
//		
//	}
	
	//서블릿 실행 후
//	// 필터 객체가 소멸(삭제)되기 전에 1번 실행
//	@Override
//	public void destroy() {
//		
//		System.out.println("CharEncFilter destroy() 실행");
//	}
	
}
