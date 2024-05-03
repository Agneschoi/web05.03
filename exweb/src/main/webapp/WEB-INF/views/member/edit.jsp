<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
	<h1>회원 정보 변경</h1> 
		
<form action='${pageContext.request.contextPath}/member/edit.do' method='post'>
아이디 : <input type='text' name='memId' value="${memVo.memId}"> <br>
<!--  <비밀번호 : <input type='password' name='memPass' > <br>-->
이름 : <input type='text' name='memName' value="${memVo.memName}"> <br>
포인트 : <input type='number' name='memPoint' value="${memVo.memPoint}" > <br>
	<input type='submit'>
</form>
<button type="button">삭제</button>
		
</body>
</html>