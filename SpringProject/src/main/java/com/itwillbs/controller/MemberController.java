package com.itwillbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberController {
	//주의 :파일이 틀려도 같은 @Controller 에 있는 가상주소 모두 다르게 부여
	
//	가상주소 http://localhost:8080/myweb/member/insert
//	       주소매핑 ->  member/insertForm.jsp 
	@RequestMapping(value = "/member/insert", method = RequestMethod.GET)
	public String write() {
		// 처리작업
		
		// 가상주소에서 주소변경 없이 이동(member/insertForm.jsp)
//		RequestDispatcher dispatcher=
//				request.getRequestDispatcher(forward.getPath());
//		        dispatcher.forward(request, response);

		// /WEB-INF/views/member/insertForm.jsp 
		return "member/insertForm";
	}
	
	// 가상주소 http://localhost:8080/myweb/member/insertPro
	//        전송방식 POST
	@RequestMapping(value = "/member/insertPro", method = RequestMethod.POST)
	public String insertPro() {
		System.out.println("MemberController insertPro()");
		// 처리작업
		
		// 가상주소에서 주소변경 하면서 이동(가상주소 /member/login)
//		response.sendRedirect(forward.getPath());
		return "redirect:/member/login";
	}
	
	
	
//	가상주소 http://localhost:8080/myweb/member/login
//    주소매핑 ->  member/loginForm.jsp 
	@RequestMapping(value = "/member/login", method = RequestMethod.GET)
	public String login() {
		// 처리작업
		
		// /WEB-INF/views/member/loginForm.jsp 
		return "member/loginForm";
	}
	
	// 가상주소 http://localhost:8080/myweb/member/loginPro
	//        전송방식 POST
	@RequestMapping(value = "/member/loginPro", method = RequestMethod.POST)
	public String loginPro() {
		System.out.println("MemberController loginPro()");
		// 처리작업
		
		// 가상주소에서 주소변경 하면서 이동(가상주소 /member/main)
//		response.sendRedirect(forward.getPath());
		return "redirect:/member/main";
	}
	
	
//	가상주소 http://localhost:8080/myweb/member/main
//  주소매핑 ->  member/main.jsp 
	@RequestMapping(value = "/member/main", method = RequestMethod.GET)
	public String main() {
		// 처리작업
		
		// /WEB-INF/views/member/main.jsp 
		return "member/main";
	}
	
//	가상주소 http://localhost:8080/myweb/member/info
//  주소매핑 ->  member/info.jsp 
	@RequestMapping(value = "/member/info", method = RequestMethod.GET)
	public String info() {
		// 처리작업
		
		// /WEB-INF/views/member/info.jsp 
		return "member/info";
	}
	
//	가상주소 http://localhost:8080/myweb/member/update
//  주소매핑 ->  member/updateForm.jsp
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String update() {
		// 처리작업
		
		// /WEB-INF/views/member/updateForm.jsp 
		return "member/updateForm";
	}
	
	
//	가상주소 http://localhost:8080/myweb/member/delete
//  주소매핑 ->  member/deleteForm.jsp
	@RequestMapping(value = "/member/delete", method = RequestMethod.GET)
	public String delete() {
		// 처리작업
		
		// /WEB-INF/views/member/deleteForm.jsp 
		return "member/deleteForm";
	}

//	가상주소 http://localhost:8080/myweb/member/list
//  주소매핑 ->  member/list.jsp
	@RequestMapping(value = "/member/list", method = RequestMethod.GET)
	public String list() {
		// 처리작업
		
		// /WEB-INF/views/member/list.jsp 
		return "member/list";
	}
}
