<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/insertPro.jsp</title>
</head>
<body>
<h1>member/insertPro.jsp</h1>
<%
//폼에서 입력한 내용이 서버에 전달 => request 내장객체 저장
//request 한글처리
request.setCharacterEncoding("utf-8");
// request 태그이름에 해당하는 값을 가져오기 => 변수에 저장
String id=request.getParameter("id");
String pass=request.getParameter("pass");
String name=request.getParameter("name");
//가입날짜 => 시스템 날짜 
// java.sql.Timestamp 자바내장객체 => 날짜 
Timestamp date=new Timestamp(System.currentTimeMillis());

// id, pass, name, date,age,gender,email,phone,mobile,address,postcode,...
// => 하나의 자바파일에 담아서 전달(MemberDTO :member 데이터 전송 객체)
// => 패키지 member 파일이름 MemberDTO
// => id, pass, name, date 멤버변수, set get메서드()
// => MemberDTO 객체생성 => 기억장소 할당
MemberDTO dto=new MemberDTO();
System.out.println("MemberDTO 바구니 주소 : "+dto);
// => 기억장소에 id, pass, name, date 값을 저장
dto.setId(id);
dto.setPass(pass);
dto.setName(name);
dto.setDate(date);

// 1~4단계 자바파일(MemberDAO:member 디비접근객체) 에 
// => Java Resources - src/main/java - 패키지 member 파일이름 MemberDAO
// insertMember() 메서드 정의해서 
// 자바파일 객체생성하고 메서드호출
MemberDAO dao=new MemberDAO();
System.out.println("MemberDAO 주소 : "+dao);
// dao.insertMember(id, pass, name, date);
dao.insertMember(dto);


// 로그인 이동
response.sendRedirect("loginForm.jsp");
%>

</body>
</html>




