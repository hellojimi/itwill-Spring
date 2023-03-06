package com.itwillbs.service;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.dao.MemberDAOImp1;
import com.itwillbs.domain.MemberDTO;

public class MemberServiceImp1 implements MemberService{
	
	// 처리 작업
	// 부모 인터페이스 틀 상속
	
	@Override
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberServiceImp1 insertMember()");
		
		// MemberDAO 부모 = MemberDAOImp1 자식 객체 생성
		MemberDAO memberDAO = new MemberDAOImp1();
		// 메서드 호출
		memberDAO.insertMember(memberDTO);
	}
	
}
