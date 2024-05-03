package com.exam.myapp;
// 웹 애플리케이션 서버(톰캣)을 통해서 실행되는 객체 (클래스)를 만들 경우에는 -servlet 인터페이스를 구현하여 정의소로 
// 일반적으로 기본적인 서블릿 기능을 구현해 놓은 HttpServlet 클래스를 상속(확장)하여 정의 
//어떤 주소로 요청이 왔을때 현재 서블릿을 실행할지 설정
//(1) web.xml에 <servlet>요소를 사용하여 설정.
//(2) @webServlet 애노테이션을 서블릿클래스에 적용하여 설정  

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/read.do") /*hello.do로 요청이 오면 현재 서블릿 실행  */
public class ReadServlet extends HttpServlet  { /*servlet에 정의된 메소드,*/ 


	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object reqUser = (String) req.getAttribute("user"); 

		HttpSession session = req.getSession();//세션객체에 저장을 하려면 세션을 받아와야함. 현재 요청을 보낸 사용작의 세션객체 가져오기.
		String sessionuser = (String) session.getAttribute("user");//idVal값을 "user"라는 이름으로 세션객체어 저장 
		
		ServletContext context = getServletContext(); //현재 웹앱의 서블릿컨텍스트 가져오기 
		String contextUser= (String)context.getAttribute("user");//idVal값을 "user"라는 이름으로 서블릿객체에 저장 
		
		String cookUser = null;
		Cookie[] cookies = req.getCookies(); //요청메시지(헤더)에 포함된 쿠키정보 읽기. 
		for (Cookie c : cookies) {
			if ("user".equals(c.getName() ) ) { 
			cookUser = URLDecoder.decode(c.getValue(),"UTF-8");
			
		}
		
		}
		
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
		out.println("	<h1>사용자정보</h1> ");	
		out.println("	<h1>요청객체" + reqUser + "님 .</h1> ");	//save꺼 idval 갖고오고 싶음. 
		out.println("	<h1>세션객체" + sessionuser + "님 .</h1> ");	//save꺼 idval 갖고오고 싶음. 
		out.println("	<h1>서블릿컨텍스트객체" + contextUser + "님 .</h1> ");	//save꺼 idval 갖고오고 싶음. 
		out.println("	<h1>쿠키: " + cookUser + "님 .</h1> ");	//save꺼 idval 갖고오고 싶음. 
		out.println("</body>                            ");
		out.println("</html>                            ");
		                                                
	}
	
}
