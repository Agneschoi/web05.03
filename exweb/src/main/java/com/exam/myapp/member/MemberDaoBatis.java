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
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MemberDaoBatis implements MemberDao {

	SqlSessionFactory sqlSessionFactory = null;
	{
		
		try {
			//마이바티스 xml 설정파일 위치
			String resource = "batis/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//XML 설정파일의 내용대로 동작하는 마이바티스 SqlSessionFactory 객체 생성 
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	

	@Override
	public List<MemberVo> findAll() {
		List<MemberVo> list= new ArrayList<MemberVo>();
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//실행할 SQL문 종류와 일치하는 SqlSession 객체의  메서드를 사용 
			//첫번째 인자로, 실행할 SQL문의 이름 (namepace.id)dmf wjsekf
			 list = session.selectList("com.exam.myapp.member.MemberDao.findAll");
		
		}
		
		return list;
	}
	
	
	
	@Override
	public int add(MemberVo vo) {
		int num =0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//#{속성이름} 또는 ${속성이름}을 사용하여 
			//전달한 객체의 속성값을 sql내부에서 사용가능. #{} 선호 보안상 이유로 
			 num = session.insert("com.exam.myapp.member.MemberDao.add",vo);
			 session.commit();	
		}
		
		

		return num;
	}



	@Override
	public MemberVo findById(String id) {
		MemberVo vo = null;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			vo = session.selectOne("com.exam.myapp.member.MemberDao.findById",id);
		}
		return vo;
	}



	@Override
	public int edit(MemberVo vo) {
		int num =0;
		try (SqlSession session = sqlSessionFactory.openSession()) {
			//#{속성이름} 또는 ${속성이름}을 사용하여 
			//전달한 객체의 속성값을 sql내부에서 사용가능. #{} 선호 보안상 이유로 
			 num = session.update("com.exam.myapp.member.MemberDao.edit",vo);
			 session.commit();	
		}
		
		

		return num;
	}
	
}
