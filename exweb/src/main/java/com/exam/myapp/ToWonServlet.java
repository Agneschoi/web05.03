package com.exam.myapp;
// 웹 애플리케이션 서버(톰캣)을 통해서 실행되는 객체 (클래스)를 만들 경우에는 -servlet 인터페이스를 구현하여 정의소로 
// 일반적으로 기본적인 서블릿 기능을 구현해 놓은 HttpServlet 클래스를 상속(확장)하여 정의 
//어떤 주소로 요청이 왔을때 현재 서블릿을 실행할지 설정
//(1) web.xml에 <servlet>요소를 사용하여 설정.
//(2) @webServlet 애노테이션을 서블릿클래스에 적용하여 설정  

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/d2w.do") /*hello.do로 요청이 오면 현재 서블릿 실행  */
public class ToWonServlet extends HttpServlet  { /*servlet에 정의된 메소드,*/ 


	//hello servlet서블릿.service (요청객체,응답객체)
	//요청객채: 웹브라우저가 보낸 요청의 모든 정보를 담고 있는  ( 변수와 메소드 (명령어)가 뭉쳐져 있는게 객체:그 객체 안에 요청보낸 사람의 모든 정보가 들어 있음.  크롬안에서 다양한 정보를 담아서 보냄 , 모든 정보가 요청객체에 들어있음
	//응답객체: 웹브라우저에게 응답으로 보낼 정보를 담는 객체  
	

	
	//요청이 어떻게 왔는지 궁금하면 req , 크롬에서 무언가 보고 싶으면 resp에 저장하기 
	//응답객체 사용하는 방법: 
	
	//resp. 명령어 (); 기능을 이용해야함. 
	//.은 의를 의미함 소유한다는 의미 
	


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("ToWon Servlet");
		// 현재 서블릿에 연결된 주소로 요청이 올때마다 1번씩 실행 . 
		
		//실행시 요청객체와 응답객체를 인자로 전달받는다. 
		System.out.println("ToWon Servlet");
		
		//요청 메시지 본문을 읽기전에 요청객체의 문자인코딩 설정.
		//post 방식으로 전송된 한글 파라미터 값을 읽기위해 필요 
		req.setCharacterEncoding("UTF-8");
		String number = req.getParameter("usd"); //파라미터 이름이 "id"인 파라미터의 값 읽기.
		System.out.println(number);
		//int one=Integer.parseInt(number); 
		
	double one = Double.parseDouble(number); //실행하면 123,456이 나옴.
		
		//브라우저 화면에 "안녕 서블릿" 아래쪽에 
		//"id파라미터값님 환영합니다. '라고 
		
		resp.setContentType("text/html"); // 응답내용의 형식을 MIME 타입으로 설정. 그림파일로 보낸다. image/png 로 설정가능 
		resp.setCharacterEncoding("UTF-8"); //응답내용 출력에 사용할 문자인코딩 방식설정.
		PrintWriter out= resp.getWriter(); //응답객체에 응답 내용을 쓸수 있는 객
		//out.println("Hello"); //응답객체에 응답내용 쓰기 (응답내용은 웹브라우저에게 전송) 
		
		out.println("<!DOCTYPE html>                    ");
		out.println("<html>                             ");
		out.println("<head>                             ");
		out.println("<meta charset='UTF-8'>             ");
		out.println("<title>hello</title>               ");
		out.println("</head>                            ");
		out.println("<body>                             ");
		out.println("	<h1>Hello 화면에 나올 내용</h1> ");	
		out.println("	<h1>" + one + "달러</h1> ");	
		out.println("	<h1>" + one *1330 + "원 </h1> ");	
		out.println("</body>                            ");
		out.println("</html>                            ");
		                                                
	}
	
}
