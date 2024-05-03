package com.exam.myapp.comm;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//특정사건이 발생하면 자동으로 실행 
//싶은 사건에 따라서 그에 맞는 XxxxListener 인터페이스 

//(1)web.xml에 listener 
//(2)

//ServletContextListener: 서블릿컨텍스트의 생성과 소멸을 감시 

public class MyListener implements ServletContextListener{
	
	
	@Override //서블릿컨텍스트 생성 (웹앱 최초시작) 후 한번실행
	public void contextInitialized(ServletContextEvent sce) {
		
		System.out.println("MyLister:contextInitialized() 실행...");
		ServletContext context = sce.getServletContext(); //새로 생성된 서블릿컨텍스트 객체 가져오기. 
		String className = context.getInitParameter("cn"); //서블릿컨텍스트의 초기화파라미터값읽기 
		
		try {
			Class.forName(className);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} 
		
	}

}

