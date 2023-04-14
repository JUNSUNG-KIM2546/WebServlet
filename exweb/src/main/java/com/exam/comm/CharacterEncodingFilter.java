package com.exam.comm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

//필터 : 서블릿의 실행 전후에 끼어들어가서 실행
//	-다수의 서블릿들이 수행하는 공통작업을 실행할때 사용
//	-Filter 인터페이스를 구현하여 필터 클래스 정의
//web.xml 에 <filter> 태그로 등록하거나, 클래스에 @WebFilter 적용

public class CharacterEncodingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CharacterEncodingFilter() 확인!");
		
		//이후 실행될 필터 또는 서블릿들을 실행
		chain.doFilter(request, response);
		
	}

}
