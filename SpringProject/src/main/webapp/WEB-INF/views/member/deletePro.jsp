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
<title>member/deletePro.jsp</title>
</head>
<body>
<h1>member/deletePro.jsp</h1>
<%
//request 태그이름에 해당하는 값을 가져오기 => 변수에 저장(id, pass)
String id=request.getParameter("id");
String pass=request.getParameter("pass");

// MemberDAO 객체생성
MemberDAO dao=new MemberDAO();
// MemberDTO dto = userCheck(id,pass) 메서드 호출
MemberDTO dto=dao.userCheck(id, pass);

if(dto != null){
	// id,pass 일치
	// 리턴값없음 deleteMember(String id) 메서드 정의 
	// dao.deleteMember(id) 메서드 호출
	dao.deleteMember(id);
	// => 세션값 초기화
	session.invalidate();		
	// => main.jsp 이동
	response.sendRedirect("main.jsp");
}else{
	// id,pass 틀림
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




