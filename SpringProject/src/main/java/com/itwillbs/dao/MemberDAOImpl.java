package com.itwillbs.dao;

import java.sql.Timestamp;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.itwillbs.domain.MemberDTO;

//@Inject
//private MemberDAO memberDAO;
// => 자동으로 @Repository 정의된 자식클래스 찾아서 객체생성 

@Repository
public class MemberDAOImpl implements MemberDAO{
	
	// 디비 작업
	// 부모 인터페이스 틀 상속
	
	// MyBatis 디비 연결 객체 생성
	@Inject
	private SqlSession sqlSession;
	
	// 디비 연결 객체 생성
	// 은닉 멤버변수
	// private DataSource dataSource;
	// 스프링 내장 디비
//	private SimpleJdbcTemplate template;
	
//	@Inject
//	public void setDataSource(DataSource dataSource) {
//		// this.dataSource = dataSource;
//		template = new SimpleJdbcTemplate(dataSource);
//	}

	//	String sql="insert into members(id,pass,name,date) values(?,?,?,?)";
	private static final String namespace = "com.itwillbs.mappers.memberMapper";
	
	@Override
	public void insertMember(MemberDTO memberDTO) {
		// 디비작업 
		System.out.println("MemberDAOImpl insertMember()");
		
		memberDTO.setDate(new Timestamp(System.currentTimeMillis()));
		
		// MyBatis sql 구문은 *Mapper.xml 안의 namespace 호출해서 사용함
		// (sql 구문의 이름, 입력될 값)
		sqlSession.insert(namespace + ".insertMember", memberDTO);
		
//		template.update(sql, memberDTO.getId(), memberDTO.getPass(),
//							memberDTO.getName(), memberDTO.getDate());
	}

	@Override
	public MemberDTO userCheck(MemberDTO memberDTO) {
		System.out.println("MemberDAOImpl userCheck()");
		
		// selectOne 리턴값이 MemberDTO 하나일때 사용
	    return sqlSession.selectOne(namespace + ".userCheck", memberDTO);
	}

	@Override
	public MemberDTO getMember(String id) {
		System.out.println("MemberDAOImpl getMember()");
		
		// selectOne 리턴값이 MemberDTO 하나일때 사용
	    return sqlSession.selectOne(namespace + ".getMember", id);
	}

	@Override
	public void updateMember(MemberDTO memberDTO) {
		System.out.println("MemberDAOImpl updateMember()");
		
		sqlSession.update(namespace + ".updateMember", memberDTO);
	}

	@Override
	public void deleteMember(MemberDTO memberDTO) {
		System.out.println("MemberDAOImpl deleteMember()");
		
		sqlSession.delete(namespace + ".deleteMember", memberDTO);
	}

}
