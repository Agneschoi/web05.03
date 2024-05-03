package com.exam.myapp;
// 웹 애플리케이션 서버(톰캣)을 통해서 실행되는 객체 (클래스)를 만들 경우에는 -servlet 인터페이스를 구현하여 정의소로 

// 일반적으로 기본적인 서블릿 기능을 구현해 놓은 HttpServlet 클래스를 상속(확장)하여 정의 
//어떤 주소로 요청이 왔을때 현재 서블릿을 실행할지 설정
//(1) web.xml에 <servlet>요소를 사용하여 설정.
//(2) @webServlet 애노테이션을 서블릿클래스에 적용하여 설정  

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/param.do") /* param.do로 요청이 오면 현재 서블릿 실행 */
public class ParamServlet extends HttpServlet { /* servlet에 정의된 메소드, */

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();

		String imgPath = "/exweb/img/drug.jpg";
		String avalue = req.getParameter("medicine");

		switch (avalue) {
		case "drug":
			imgPath = "/exweb/img/drug.jpg";
			break;

		case "drug1":
			imgPath = "/exweb/img/drug1.jpg";
			break;

		case "drug2":
			imgPath = "/exweb/img/drug2.jpg";
			break;

		}

		out.println("<!DOCTYPE html>                    ");
		out.println("<html>                             ");
		out.println("<head>                             ");
		out.println("<meta charset='UTF-8'>             ");
		out.println("<title>받은 파라미터 값</title>               ");
		out.println("<img src='" + imgPath + "'>");

		// 동일한 이름의 파라미터값이 여러개 전송된 경우
		// 요청객체.getParameterValues("파라미터명")
		// 명령문으로 다수의 파라미터값들을 읽기 가능

		String[] fov = req.getParameterValues("food");
		// fov = [[ "pz, "sw"]
		if (fov != null) {
			for (String s : fov) {

				switch (s) {
				case "bb":
					out.println("<img src='/exweb/img/bibimbap.png'>");
					break;
				case "pz":
					out.println("<img src='/exweb/img/pizza.png'>");
					break;
				case "sw":
					out.println("<img src='/exweb/img/sandwich.png'>");
					break;

				}

			}

		}

		out.println("</head>                            ");
		out.println("<body>                             ");

		out.println("	<h1>받은 파라미터 값</h1> ");
		out.println("	<h1>  </h1> ");

		out.println("</body>                            ");
		out.println("</html>                            ");

	}

}
