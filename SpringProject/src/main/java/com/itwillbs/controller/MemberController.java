package com.itwillbs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.MemberServiceImp1;

@Controller
public class MemberController {
	//주의 :파일이 틀려도 같은 @Controller 에 있는 가상주소 모두 다르게 부여
	
	//	서블릿이 동작하기 전에 웹 애플리케이션 서버에서 request, response 서버 내장객체 생성 
	//	 => 서버 기억장소 할당
	//	 => request 기억장소 안에 사용자가 입력한 파라미터 정보, 서버/클라이언트/세션/쿠키 정보 등 저장
	
	//	가상주소 http://localhost:8080/myweb/member/insert
	//	       주소매핑 ->  member/insertForm.jsp 
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		// 가상주소에서 주소변경 없이 이동(member/insertForm.jsp)
//		RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
//		dispatcher.forward(request, response);

		// /WEB-INF/views/member/insertForm.jsp 
		return "member/insertForm";
	}
	
	// 가상주소 http://localhost:8080/myweb/member/insertPro
	//        전송방식 POST
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro(MemberDTO memberDTO) {
		//	HttpServletRequest request 수동으로 request 값 받기
		// 사용자가 입력한 내용 => request 저장 => request 가져오기
		// 스프링 한글 처리
		// web.xml에서 한글 설정을 한 번 하면 모든 곳에서 한글처리됨
		System.out.println("MemberController insertPro()");
		// 패키지 com.itwillbs.domain MemberDTO에 저장
//		MemberDTO memberDTO = new MemberDTO();
//		memberdto.setId(request.getParameter("id"));
//		memberdto.setPass(request.getParameter("pass"));
//		memberdto.setName(request.getParameter("name"));
		// 스프링에서 insertForm.jsp id, pass, name 태그 이름 입력된 값이 서버에 전달되면
		// request에 저장 => MemberDTO 객체 생성 => 멤버변수 이름이 동일하면 자동으로 setID() 메서드 호풀, request.getParameter("id") 동작해서 값 저장됨
		
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());
		
		// MemberFrontController 주소매핑 호출 -> MemberInsertPro.java.execute() 호출 -> MemberDAO insertMember()
		
		// 자바파일 메서드 호출 후 회원가입 처리 => MemberService 처리 => MemberDAO 디비
		// 주소매핑 MemberController
		// -> 처리 패키지 com.itwillbs.service
		//    MemberService interface, MemberServiceImp1 클래스 insertMember()
		// -> 디비 패키지 com.itwillbs.dao
		//    MemberDAO interface, MemberDAOImp 클래스 insertMember()
		
		// MemberService 부모 = MemberServiceImp1 자식 객체 생성
		MemberService memberService = new MemberServiceImp1();
		// 메서드 호출
		memberService.insertMember(memberDTO);
		
		// 로그인 페이지로 이동
		//	response.sendRedirect(forward.getPath());
		return "redirect:/member/login";
	}
	
	//  주소매핑 ->  member/loginForm.jsp 
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {

		// /WEB-INF/views/member/loginForm.jsp 
		return "member/loginForm";
	}
	
	// 가상주소 http://localhost:8080/myweb/member/loginPro
	//        전송방식 POST
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO) {
		System.out.println("MemberController loginPro()");
		// 디비 로그인 처리 => 처리 => 디비 자바 메서드 호출
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());

		// 가상주소에서 주소변경 하면서 이동 (가상주소 /member/main)
		// response.sendRedirect(forward.getPath());
		return "redirect:/member/main";
	}
	
	//  주소매핑 ->  member/main.jsp 
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		
		// 주소 변경없이 이동
		// /WEB-INF/views/member/main.jsp 
		return "member/main";
	}
	
	// 주소매핑 ->  /member/logout
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout() {
		// 세션 초기화
		System.out.println("MemberController logout()");
		// 주소 변경되면서 로그인 페이지 이동
		// response.sendRedirect(forward.getPath());
		return "redirect:/member/main";
	}
	
	// 주소매핑 ->  member/info.jsp 
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info() {

		// /WEB-INF/views/member/info.jsp 
		return "member/info";
	}
	
	//  주소매핑 ->  member/updateForm.jsp
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update() {

		// /WEB-INF/views/member/updateForm.jsp 
		return "member/updateForm";
	}
	
	// 자동으로 가상주소 뽑아옴 /member/updatePro
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro() {
		System.out.println("MemberController updatePro()");
		// 디비 로그인 처리 => 처리 => 디비 자바 메서드 호출

		// 주소 변경되면서 메인 페이지 이동
		// response.sendRedirect("/member/main");
		return "redirect:/member/main";
	}
	
	// 주소매핑 ->  member/deleteForm.jsp
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete() {

		// /WEB-INF/views/member/deleteForm.jsp 
		return "member/deleteForm";
	}
	
	// 자동으로 가상주소 뽑아옴 /member/deletePro 전송방식 POST
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)
	public String deletePro(MemberDTO memberDTO) {
		System.out.println("MemberController deletePro()");
		// 디비 로그인 처리 => 처리 => 디비 자바 메서드 호출
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());
	
		// 가상주소에서 주소변경 하면서 이동 (가상주소 /member/main)
		//	response.sendRedirect(forward.getPath());
		return "redirect:/member/main";
	}

	// 주소매핑 ->  member/list.jsp
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list() {

		// /WEB-INF/views/member/list.jsp 
		return "member/list";
	}
}
