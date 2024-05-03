package com.exam.myapp.member;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDaoJdbc implements MemberDao {
	String url ="jdbc:oracle:thin:@//localhost:1521/xe"; //데이터베이스 주소
	String user ="com"; //데이터베이스 아이디
	String password ="com01";//데이터베이스 비번
	

	@Override
	public List<MemberVo> findAll() {
		
		
		List<MemberVo> list= new ArrayList<MemberVo>();
		String sql= "SELECT mem_id,mem_pass,mem_name,mem_point FROM member"; 
		
		try (	Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql); //전달한 SQL문을 담은 명령문 객체 생성				
				//반환데이터가 없는 sql 문(insert,update,delete)은 executeupdate()로 실행
				//반환데이터가 있는 sql 문(select)은 execute Query () 로 실행.
				ResultSet rs= pstmt.executeQuery();
				){
						
			while (rs.next()) {//Resultset이 다음행을 가리키도록(다음행이 있으면 true 없으면 false반환)
				//ResultSet의 커서는 처음에는 첫행 이전을 가리키고 있다. 
				MemberVo vo = new MemberVo();
				vo.setMemId (rs.getString("mem_id")); //현재 가리키는 행에서 지정한 이름의 컬럽값 읽기
				vo.setMemPass (rs.getString("mem_pass")); //현재 가리키는 행에서 지정한 이름의 컬럽값 읽기
				vo.setMemName (rs.getString("mem_name")); //현재 가리키는 행에서 지정한 이름의 컬럽값 읽기
				vo.setMemPoint (rs.getInt("mem_point")); //현재 가리키는 행에서 지정한 이름의 컬럽값 읽기
				list.add(vo);
			}			
		
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public int add(MemberVo vo) {
		int num =0;
		String sql="INSERT INTO member "
				+ " (mem_id,mem_pass,mem_name,mem_point) "
				+ " VALUES(?,?,?,?)";
		
		try (	Connection conn = DriverManager.getConnection(url, user, password);
				PreparedStatement pstmt = conn.prepareStatement(sql); //전달한 SQL문을 담은 명령문 객체 생성				
				){
			//?에 주입할 값의 타입에 따라서 .set타입명() 메서드 사용
			pstmt.setString(1, vo.getMemId()); //1번째 물음표에 memid 변수값 주입
			pstmt.setString(2, vo.getMemPass()); //1번째 물음표에 memid 변수값 주입
			pstmt.setString(3, vo.getMemName()); //1번째 물음표에 memid 변수값 주입
			pstmt.setInt(4, vo.getMemPoint()); //1번째 물음표에 memid 변수값 주입
			
			//반환데이터가 없는 sql 문(insert,update,delete)은 executeupdate()로 실행
			//반환데이터가 있는 sql 문(select)은 execute Query () 로 실행.
			num = pstmt.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
}
