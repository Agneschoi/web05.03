package com.exam.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/req/*")
public class ReqServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


	
		req.setCharacterEncoding("UTF-8");
	//  String idval = req.getParameter("id"); 
		String reqUri = req.getRequestURI();
		StringBuffer reqUrl = req.getRequestURL();//요청주소(전체
		String ctxPath= req.getContextPath();//웹앱 고유경로(컨텍)
		
		String  ua = req.getHeader("User-Agent"); //지정한 요청헤더값 읽기
		
		
		resp.setContentType("text/html"); 
		resp.setCharacterEncoding("UTF-8"); 
		PrintWriter out= resp.getWriter(); 
		//out.println("Hello"); 
		out.println("<!DOCTYPE html>                    ");
		out.println("<html>                             ");
		out.println("<head>                             ");
		out.println("<meta charset='UTF-8'>             ");
		out.println("<title>hello</title>               ");
		out.println("</head>                            ");
		out.println("<body>                             ");
		out.println("	<h1>Hello 화면에 나올 내용</h1> ");	
		out.println("	<h3>요청URI :" + reqUri + "</h3> ");	
		out.println("	<h3>요청URI :" + reqUrl + "</h3> ");	
		out.println("	<h3>컨텍스트패스 :" + ctxPath + "</h3> ");	
		out.println("	<h3>User-Agentn 헤더값 :" + ua + "</h3>");	
		if (ua.contains("Edg/")) {
			out.println("<h3>당신이 사용중인 브라우져  : MS Edge </h3>");
					
		}else if (ua.contains("OPR/")) {
			out.println("<h3>당신이 사용중인 브라우져: Opera</h3>");
		}else if (ua.contains("Firefox/")) {
			out.println("<h3> 당신이 사용중인 브라우져 :Firefox</h3>");
		}else if (ua.contains("Safari/")&&!ua.contains("Chrome/")) {
			out.println("<h3>당신이 사용중인 브라우져 :Safari</h3> ");
			
	}
		else if (ua.contains("Chrome/")) {
		out.println("<h3>당신이 사용중인 브라우져 :Chrome</h3> ");	
}
		
		else {
		out.println("<h3>당신이 사용중인 브라우져 :알수없는 브라우져</h3> ");

	}

	out.println("</body>                            ");out.println("</html>                            ");

}

}
