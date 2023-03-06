package com.itwillbs.myweb;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
//1) 자바파일 => @Controller 찾아서 주소매핑 역할 부여 => 파일 동작
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	// 2) 메서드 => @RequestMapping 하나씩 주소매핑 => 가상주소 자동으로 뽑아와서 value = "/" 비교
//	=>  전송방식 method = RequestMethod.GET 확인 => 메서드 자동으로 동작
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//로고 출력
		logger.info("Welcome home! The client locale is {}.", locale);
		
		//날짜객체생성 => 날짜 모양설정
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		// request => 스프링 model 데이터 담기 
		model.addAttribute("serverTime", formattedDate );
		
		// /WEB-INF/views/파일이름.jsp
		// /WEB-INF/views/home.jsp
		return "home";
	}
	
	//가상주소 http://localhost:8080/myweb/insert.me   
	//       주소매핑 ->  insert.jsp 
	@RequestMapping(value = "/insert.me", method = RequestMethod.GET)
	public String insert() {
		// 처리작업
		
		// /WEB-INF/views/insert.jsp
		return "insert";
	}
	
	//가상주소 http://localhost:8080/myweb/write
	//       주소매핑 ->  write.jsp 
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		// 처리작업
		
		// /WEB-INF/views/write.jsp
		return "write";
	}
	
}
