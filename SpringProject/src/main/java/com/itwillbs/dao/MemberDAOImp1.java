package com.itwillbs.dao;

import com.itwillbs.domain.MemberDTO;

public class MemberDAOImp1 implements MemberDAO{
	// 디비 작업
	// 부모 인터페이스 틀 상속
	
	@Override
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberDAOImp1 insertMember()");
		
	}
	
	
	
}
