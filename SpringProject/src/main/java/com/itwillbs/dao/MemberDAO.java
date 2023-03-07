package com.itwillbs.dao;

import com.itwillbs.domain.MemberDTO;

public interface MemberDAO {
	// 추상 메서드 틀 정의
	public void insertMember(MemberDTO memberDTO);
	
	public MemberDTO userCheck(MemberDTO memberDTO);
}
