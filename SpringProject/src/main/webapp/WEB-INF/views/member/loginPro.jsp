<%@page import="member.MemberDTO"%>
<%@page import="member.MemberDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>member/loginPro.jsp</title>
</head>
<body>
<h1>member/loginPro.jsp</h1>
<%
//폼에서 입력한 내용이 서버에 전달 => request 내장객체 저장
//request 태그이름에 해당하는 값을 가져오기 => 변수에 저장
String id=request.getParameter("id");
String pass=request.getParameter("pass");

// MemberDAO 객체생성=> 기억장소 할당
MemberDAO dao=new MemberDAO();
// 리턴할형(MemberDTO) userCheck(String id, String pass) 메서드 정의
// id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 주소 가져오기
// id, pass 틀리면 MemberDTO 빈(null) 바구니 주소 가져오기
// MemberDTO  dto  = 주소.userCheck(id,pass)메서드 호출
MemberDTO dto=dao.userCheck(id, pass);
if(dto !=null){
	// id, pass 일치하면 MemberDTO 바구니에 데이터 담아서 가져오기
	// => 세션값 생성 "id",id(페이지 상관없이 값을 유지) , main.jsp 이동
	session.setAttribute("id", id);
    response.sendRedirect("main.jsp");
}else{
	// id, pass 틀리면 MemberDTO 빈(null) 바구니 가져오기
	// => script   "아이디 비밀번호 틀림" 뒤로이동
    %>
    <script type="text/javascript">
		alert("아이디 비밀번호 틀림");
		history.back();
    </script>
    <%
}
%>
</body>
</html>



