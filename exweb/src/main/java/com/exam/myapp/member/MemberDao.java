package com.exam.myapp.member;

import java.util.List;

public interface MemberDao {
	

	List<MemberVo> findAll();
	
	MemberVo findById(String id );

	int add(MemberVo vo);

	int edit(MemberVo vo);

}