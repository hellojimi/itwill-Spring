package com.itwillbs.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwillbs.domain.MemberDTO;
import com.itwillbs.service.MemberService;
import com.itwillbs.service.MemberServiceImpl;
import com.mysql.cj.Session;

@Controller
public class MemberController {
	//주의 :파일이 틀려도 같은 @Controller 에 있는 가상주소 모두 다르게 부여
	
	//	서블릿이 동작하기 전에 웹 애플리케이션 서버에서 request, response 서버 내장객체 생성 
	//	 => 서버 기억장소 할당
	//	 => request 기억장소 안에 사용자가 입력한 파라미터 정보, 서버/클라이언트/세션/쿠키 정보 등 저장
	
//	MemberService memberService = new MemberServiceImp1();
	
	// 스프링 3버전 자동으로 객체 생성 방법
	// 데이터 은닉
	// 스프링 파일 root-context.xml에서 객체 생성
	// MemberController 파일에 멤버변수 memberService 전달
	// private MemberService memberService;
	
	// 멤버변수 값을 생성자 또는 set 메서드 통해서 전달
	// 생성자
//	public MemberController(MemberService memberService) {
//		this.memberService = memberService;
//	}
	// set 메서드
//	@Inject
//	public void setMemberService(MemberService memberService) {
//		this.memberService = memberService;
//	}
	
	// 스프링 4버전
	// 멤버변수 부모 공통적인 틀 선언 => 데이터 은닉
	// @Inject 부모를 상속받은 자식 클래스 자동으로 찾아옴
	// 데이터 은닉된 부모 인터페이스 멤버변수에 xml에서 객체를 생성하고 set메서드를 통해서 전달
	
	// 객체 생성됨
	@Inject
	private MemberService memberService;
	// MemberServiceImpl에서 @Service 찾음
		
	
	// -------------------------------------------------
		
		
	//	가상주소 http://localhost:8080/myweb/member/insert
	//	       주소매핑 ->  member/insertForm.jsp 
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String insert() {
		// 가상주소에서 주소변경 없이 이동(member/insertForm.jsp)
		//	RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
		//	dispatcher.forward(request, response);

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
//		MemberService memberService = new MemberServiceImp1();
		// 메서드 호출
		memberService.insertMember(memberDTO);
		
		// 로그인 페이지로 이동
		//	response.sendRedirect(forward.getPath());
		return "redirect:/member/login";
	}
	
	// member/loginForm.jsp 
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {

		return "member/loginForm";
	}
	
	// 전송방식 POST
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController loginPro()");
		// 디비 로그인 처리 => 처리 => 디비 자바 메서드 호출
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());
		
		// MemberLoginPro.java execute() 대신 MemberService 부모 = MemberServiceImp1 자식 객체 생성
		//	MemberService memberService = new MemberServiceImp1();
		// userCheck(MemberDTO memberDTO) 메서드 호출
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		
		if(memberDTO2 != null) {
			// 아이디 비밀번호 일치
			System.out.println("아이디 비밀번호 일치");
			
			// 회원이 일치하다
			// => 페이지 상관없이 값이 계속 유지가 되도록 설정
			// => 세션의 특징을 이용해서 => 세션 기억장소 안에 유지할 값을 저장해 놓고 어디서나 사용가능
			// 세션 객체 받아와서 세션 사용
			session.setAttribute("id", memberDTO.getId());
			
			// 가상주소에서 주소변경 하면서 이동
			// response.sendRedirect("/member/main");
			return "redirect:/member/main";
			
		} else {
			// 아이디 비밀번호 틀림
			System.out.println("아이디 비밀번호 불일치");
			
			return "member/msg";
		}
	}
	
	//  member/main.jsp 
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		
		// 주소 변경없이 이동
		return "member/main";
	}
	
	// /member/logout
	@RequestMapping(value = "/member/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// 세션 초기화
		System.out.println("MemberController logout()");
		
		session.invalidate();
		// 주소 변경되면서 로그인 페이지 이동
		// response.sendRedirect("/member/main");
		return "redirect:/member/main";
	}
	
	// member/info.jsp 
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info(HttpSession session, Model model) {
		System.out.println("MemberController info()");
		
		// 세션값
		String id = (String)session.getAttribute("id");
		MemberDTO memberDTO = memberService.getMember(id);
		
		// memberDTO를 들고 member/info.jsp로 이동
		// request.setAttribute("memberDTO", memberDTO);
		// request 대신에 Model에 담아서 이동
		model.addAttribute("memberDTO", memberDTO);
		
		// 주소 변경없이 이동
		return "member/info";
	}
	
	// member/updateForm.jsp
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		System.out.println("MemberController update()");
		
		// 세션값
		String id = (String)session.getAttribute("id");
		MemberDTO memberDTO = memberService.getMember(id);
		
		// memberDTO를 들고 member/info.jsp로 이동
		// request.setAttribute("memberDTO", memberDTO);
		// request 대신에 Model에 담아서 이동
		model.addAttribute("memberDTO", memberDTO);
		
		// 주소 변경없이 이동
		return "member/updateForm";
	}
	
	// /member/updatePro
	@RequestMapping(value = "/member/updatePro", method = RequestMethod.POST)
	public String updatePro(MemberDTO memberDTO) {
		System.out.println("MemberController updatePro()");
		// 디비 로그인 처리 => 처리 => 디비 자바 메서드 호출
		
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());
		
		// MemberLoginPro.java execute() 대신 MemberService 부모 = MemberServiceImp1 자식 객체 생성
		//	MemberService memberService = new MemberServiceImp1();
		// userCheck(MemberDTO memberDTO) 메서드 호출
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		
		if(memberDTO2 != null) {
			// 아이디 비밀번호 일치
			System.out.println("아이디 비밀번호 일치");
			
			// 수정 작업
			memberService.updateMember(memberDTO);
			
			// 가상주소에서 주소변경 하면서 이동
			// response.sendRedirect("/member/main");
			return "redirect:/member/main";
			
		} else {
			// 아이디 비밀번호 틀림
			System.out.println("아이디 비밀번호 불일치");
			
			return "member/msg";
		}
	}
	
	// member/deleteForm.jsp
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete() {
		
//		// 세션값
//		String id = (String)session.getAttribute("id");
//		MemberDTO memberDTO = memberService.getMember(id);
		
		return "member/deleteForm";
	}
	
	// /member/deletePro 전송방식 POST
	@RequestMapping(value = "/member/deletePro", method = RequestMethod.POST)
	public String deletePro(MemberDTO memberDTO, HttpSession session) {
		System.out.println("MemberController deletePro()");
		// 디비 로그인 처리 => 처리 => 디비 자바 메서드 호출
		System.out.println(memberDTO.getId());
		System.out.println(memberDTO.getPass());
		System.out.println(memberDTO.getName());
		
		MemberDTO memberDTO2 = memberService.userCheck(memberDTO);
		
		if(memberDTO2 != null) {
			// 아이디 비밀번호 일치
			System.out.println("아이디 비밀번호 일치");
			
			// 수정 작업
			memberService.deleteMember(memberDTO);
			
			session.invalidate();
			
			// 가상주소에서 주소변경 하면서 이동
			// response.sendRedirect("/member/main");
			return "redirect:/member/main";
			
		} else {
			// 아이디 비밀번호 틀림
			System.out.println("아이디 비밀번호 불일치");
			
			return "member/msg";
		}

	}

	// member/list.jsp
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list() {

		return "member/list";
	}
}
