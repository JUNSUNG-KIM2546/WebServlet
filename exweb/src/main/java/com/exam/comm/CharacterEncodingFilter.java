package com.exam.comm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//필터 : 서블릿의 실행 전후에 끼어들어가서 실행
//	-다수의 서블릿들이 수행하는 공통작업을 실행할때 사용
//	-Filter 인터페이스를 구현하여 필터 클래스 정의
//web.xml 에 <filter> 태그로 등록하거나, 클래스에 @WebFilter 적용

public class CharacterEncodingFilter implements Filter {
	
	private String enc;	//필드 (전역변수)

	// 필터가 처음 생성됐을 때 1번 실행 (초기화 메소드)
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		System.out.println("CharEncFilter init() 실행");
		
		enc = filterConfig.getInitParameter("encoding");	// web.xml에서 설정한 초기화 변수명
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 필터의 경로에 맞는 요청이 올때마다 한번씩 실행
		System.out.println("CharacterEncodingFilter() 확인!");
		
		//서블릿 실행 전(인코딩 설정)
		request.setCharacterEncoding(enc); //web.xml에서 설정한 필터 초기화 값
		
		//이후 실행될 필터 또는 서블릿들을 실행 -----여기 기준으로 서블릿 실행전과 실행후로 나뉨-----
		chain.doFilter(request, response);
		
		//서블릿 실행 후
		
	}
	
	// 필터 객체가 소멸(삭제)되기 전에 1번 실행
	@Override
	public void destroy() {
		
		System.out.println("CharEncFilter destroy() 실행");
	}
	
}
