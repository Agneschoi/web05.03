package com.exam.myapp;
// 웹 애플리케이션 서버(톰캣)을 통해서 실행되는 객체 (클래스)를 만들 경우에는 -servlet 인터페이스를 구현하여 정의소로 
// 일반적으로 기본적인 서블릿 기능을 구현해 놓은 HttpServlet 클래스를 상속(확장)하여 정의 
//어떤 주소로 요청이 왔을때 현재 서블릿을 실행할지 설정
//(1) web.xml에 <servlet>요소를 사용하여 설정.
//(2) @webServlet 애노테이션을 서블릿클래스에 적용하여 설정  

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

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
@WebServlet("/save.do") /*hello.do로 요청이 오면 현재 서블릿 실행  */
public class SaveServlet extends HttpServlet  { /*servlet에 정의된 메소드,*/ 
	
	/*다수의 서블릿들이 공유할 데이터를 저장하는 장소 
	1.서버
	(1)요청객체: 요청당 1개의 요청객체와 응답객체 생성 및 소멸 
	(2)세션객체: 클라이언트(웹브라우저)당 1개의 세션객체 생성
		-새로운 클라이언트가 처음 요청을 전송(서블릿실행)했을때 생성
		-일정시간동안 접속이 없거나 웹애플라케이션이 종료될때 소멸
	(3)서블릿컨텍스트객체: 웹애플리케이션 전체에 1개의 서블릿컨텍스트 생성
		-웹 애플리케이션이 시작될떄 생성, 종료될떄 소멸
	2.클라이언트(웹브라우져)
	(1)쿠키
	(@)로컬스토리지,세션스토리지
*/
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		req.setCharacterEncoding("UTF-8");//post방식으로 전송하면 한글이깨지기 떄문에 이 문장을 사용함. 
		String idval = req.getParameter("id"); //파라미터 이름이 "id"인 파라미터의 값 읽기.
		
		req.setAttribute("user", idval);//idVal값을 "user"라는 이름으로 요청객체에 저장. 
		HttpSession session = req.getSession();//세션객체에 저장을 하려면 세션을 받아와야함. 현재 요청을 보낸 사용작의 세션객체 가져오기.
		session.setAttribute("user", idval);//idVal값을 "user"라는 이름으로 세션객체어 저장 
		
		ServletContext context = getServletContext(); //현재 웹앱의 서블릿컨텍스트 가져오기 
		context.setAttribute("user", idval);//idVal값을 "user"라는 이름으로 서블릿객체에 저장 
		
		Cookie c = new Cookie("user", URLEncoder.encode(idval, "UTF-8")); // 쿠키이름 , 값을 담은 쿠키 객체 생성
		/*
		 * c.setComment("쿠키에대한설명"); c.setDomain("도메인"); //네이버, 구글(도미인임) , 지정한 도메인 또는 그
		 * 서브도메인으로 요청을 보낼떄만 쿠키를 포함. c.setPath("경로");//지정한 경로의 하위 경로로 요청을 보낼때만
		 */		c.setMaxAge(60);; //브라우저가 쿠키를 저장하고 유지하는 시간 (초)
		//0 :은 쿠키를 즉시 삭제, 음수는 브라우저 종료시 쿠키삭제 
		c.setVersion(0); //0은 Netsacpe 호환쿠키, 1은 RFC2109호환쿠키
		c.setSecure(false);//true설정시, HTTPS 보안 프로토콜 사용시에만 쿠키포함. 
		resp.addCookie(c ); //웹브라우저에 정장할 쿠키 정보를 응답에 추가 
		
		
		//현재 요청객체와 응답객체를 전달하면서,
		//"/read.do"주소와 연결된 서블릿을 실행 
		//req.getRequestDispatcher("/read.do").forward(req, resp);
		
		System.out.println(idval);
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
		out.println("	<h1>" + idval + "을 저장했습니다. .</h1> ");	
		out.println("</body>                            ");
		out.println("</html>                            ");
		                                                
	}
	
}
