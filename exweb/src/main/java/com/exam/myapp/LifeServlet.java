package com.exam.myapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//생명주기(LifeCycle) :
//객체의 생성부터 소멸까지 특정 시점에 자동으로 실행되는 메서드.

@WebServlet("/lifedo") //어떤 파일이 오면 실행할건지 .이런파일이 요청하면 실행해라. 
public class LifeServlet extends HttpServlet{
	@Override
	public void init() throws ServletException {
		System.out.println("init:서블릿이 처음 생성될떄 최초 한번만 실행");
		//서블릿이 요청을 처리할떄 필요한 자원들을 준비하는 작업을 수행
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service:요청이 올때마다 한번씩 실행");	

	}
	
	@Override
	public void destroy() {
		System.out.println("destroy:서블릿 객체 소멸(메모리에서 제거) 직전에 한번만 실행");
		//서블릿이 사용하던 자원(시스템 리소스)들을 정리(반환)하는 작업 수행 , 
	}


}
