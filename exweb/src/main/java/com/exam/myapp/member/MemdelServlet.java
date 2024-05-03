package com.exam.myapp.member; 

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;



@WebServlet("/member/del.do")  
public class MemdelServlet extends HttpServlet{
	private MemberDao memberDao = new MemberDaoBatis();
	//HttpServlet의 service() 메서드는
	//요청방식에 따라서 do요청방식() 메서드를 실행하도록 구현되어 있음. 
	
	@Override

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		MemberVo vo = memberDao.findById(req.getParameter("memId"));
		req.setAttribute("memVo", vo);
		req.getRequestDispatcher("/WEB-INF/views/member/edit.jsp").forward(req,resp);
	}
	
	
	@Override

	protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		req.setCharacterEncoding("UTF-8");
		MemberVo vo = new MemberVo();
		vo.setMemId(req.getParameter("memId"));
		vo.setMemName(req.getParameter("memName"));
		vo.setMemPoint(Integer.parseInt( req.getParameter("memPoint"))); 
				
		int num = memberDao.edit(vo); 
		System.out.println(num + "명의 회원정보 qusrud");	
		
		//다른 서블릿을 실행하는 방법
		//(1) 리디렉션
		
		//웹브라우저에게 지정한 주소로 이동 (요청전송)하라는 
		//명령을 담은 응답 메시지를 전송 
		resp.sendRedirect(req.getContextPath()+ "/member/list.do"); 
		
		//(2) 포워드,인클루드
		//서블릿에서 서버(톰캣)에게 요청을 보내서 다른 서블릿을 실행 
		//(서버 내에서 이동하므로, 클라이언트 (브라우저)는 알지 못함) 
		/*
		 * RequestDispatcher rd = req.getRequestDispatcher("/member/list.do");
		 * rd.forward(req, resp); //다른 서블릿을 실행하여 응답을 전송을 완료하도록. rd.include(req, resp);
		 * //다른 서블릿을 실행하여 응답을 출력한 후 //현재 서블릿으로 돌아와서 응답 출력을 계속하도록
		 */		

		
		
	}
	
	



}
