package com.itwillbs.service;

import javax.inject.Inject;

import com.itwillbs.dao.MemberDAO;
import com.itwillbs.dao.MemberDAOImpl;
import com.itwillbs.domain.MemberDTO;

public class MemberServiceImpl implements MemberService{
	
	// 처리 작업
	// 부모 인터페이스 틀 상속
	
	// 멤버변수 부모 = 자식 객체 생성
//	MemberDAO memberDAO = new MemberDAOImpl();
	
	// 멤버변수 데이터 은닉
	private MemberDAO memberDAO;
	// 생성자
	//	public MemberServiceImpl(MemberDAO memberDAO) {
	//		this.memberDAO = memberDAO;
	//	}
	
	// set 메서드 생성
	@Inject
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}

	@Override
	public void insertMember(MemberDTO memberDTO) {
		System.out.println("MemberServiceImp1 insertMember()");
		
		// MemberDAO 부모 = MemberDAOImp1 자식 객체 생성
//		MemberDAO memberDAO = new MemberDAOImp1();
		// 메서드 호출
		memberDAO.insertMember(memberDTO);
	}

	@Override
	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberServiceImp1 userCheck()");
		
		// MemberDAO 부모 = MemberDAOImp1 자식 객체 생성
//		MemberDAO memberDAO = new MemberDAOImp1();
		// 메서드 호출
		MemberDTO memberDTO2 = memberDAO.userCheck(memberDTO);
		
		return null;
	}
	
	
}
