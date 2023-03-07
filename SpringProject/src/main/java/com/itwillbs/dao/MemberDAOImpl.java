package com.itwillbs.dao;

import com.itwillbs.domain.MemberDTO;

public class MemberDAOImpl implements MemberDAO{
	// 디비 작업
	// 부모 인터페이스 틀 상속
	
	@Override
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAOImp1 insertMember()");
		
	}

	@Override
	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberDAOImp1 userCheck()");
		
		return null;
	}
	
	
	
}
