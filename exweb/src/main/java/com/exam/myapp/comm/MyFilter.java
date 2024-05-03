package com.exam.myapp.comm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;




public class MyFilter implements Filter{
	
	
	private String charSet;
	@Override //필터(인스턴스)가 최초 생성된 후, 한번만 실행 
	public void init(FilterConfig filterConfig) throws ServletException {
		
		charSet = filterConfig.getInitParameter("cs"); //필터의 초기화파라미터값 읽기..
	}

	public static void main(String[] args) {
		

	}

	@Override //요청이 올때마다 한번씩 실행 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("MyFilter:doFilter 실행...");
		//서블릿 실행 전에 수행할 작업들 
		
		
		request.setCharacterEncoding(charSet);	
		chain.doFilter(request, response); //다음필터 또는 서블릿 실행 
		//서블릿 실행 후에 수행할 작업들
		
	}

}
