
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리</title>
</head>
<body>
<h1>회원목록</h1>
<a href='${pageContext.request.contextPath}/member/add.do' >회원추가</a><br>
<c:forEach var="vo" items="${memList}">


<a href="${pageContext.request.contextPath}/member/edit.do?memId=${vo.memId}">   ${vo.memId}</a>
	 : ${vo.memPass} : ${vo.memName}: ${vo.memPoint}<br>
</c:forEach>

<%-- <% 	
List<MemberVo> list = request.getAttribute("memList");
for (MemberVo vo : list) {
	out.println(vo.getMemId() + ":" + vo.getMemPass() + ";" + vo.getMemName() + ":" + vo.getMemPoint()+ "<br>");
			
}
%> --%>
</body>
</html>