package com.exam.myapp.member; 

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;



@WebServlet("/member/list.do")  
public class MemListServlet extends HttpServlet{
	private MemberDao memberDao = new MemberDaoBatis();
		
	@Override

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<MemberVo> list = memberDao.findAll(); 
		//JSP파일에서 사용할 데이터를 요청객체에 속성으로 저장
		
		req.setAttribute("memList", list); //JSP 파일에서 ${속성명으로 사용가능
		//JSP 파일로 forward(실행, 이동)
		req.getRequestDispatcher("/WEB-INF/views/member/list.jsp").forward(req,resp);
	}


	
	
}
