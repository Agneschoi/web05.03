<%@page import="com.exam.myapp.member.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>JSTL 연습</h1>
	<h2>CORE</h2>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!--  jstl core 태그 라이브러리의 태그들을 c: 접두어를 붙여서 사용 -->
	<h3>속성 저장 및 삭제</h3>
	<%
		//아래 회원 객체의 포인트 값이 출려고디도록 구현 
//		MemberVo v = new MemberVo();
	
//  	pageContext.setAttribute("vo", v);
		//v.getMempoint() 이건 자바에서, el에서는 속성 이름임. , 속성으로 저장해야함. 4군데 
		
	%>
	<c:set scope="page" var="vo" value="<%=new MemberVo() %>"/>
	
	
	회원의 포인트 : ${vo.memPoint}<br>
	<c:set value="${123}" property="memPoint" target="${vo}" />
<!-- 	target에 지정한 객체의 property에 지정한 이름의 속성에  -->
<!-- 	value에 지정한 값을 저장 -->
	회원의 포인트 : ${vo.memPoint}<br>
	
	<c:remove scope="page" var="vo"/>
<!-- 	scope에 지정한 xxxxscope에서  -->
<!-- 	var에 지정한 이름의 속성값을 삭제  -->
	회원의 포인트 : ${vo.memPoint}<br>
	
	<h3> 조건문, 반복문</h3>
	<%
/* 		int n =223;
		// n 값이 100보다 크면 "큰수", 작으면 "작은수 라고 출력
		if(n>100){
			out.print( "큰수");
		}
		else out.print( "작은수"); */
		
		//n 값이 300보다 크면 "최우수", 200보다 크면 "우수",
		//200이하면 "양호"라고 출력
		
/* 		if(n>300){
			out.print("최우수");
		}
		else if (n>200){
			out.print("우수");
		}
		else {
			out.print("양호");
		} */
	%>
	<c:set scope="page" var="no" value="${330}" />
	<c:if test="${no>100}">큰수</c:if>
	<!-- 	test에 지정한 값이 true인 경우에만, 태그의 내용을 출력    -->
	<c:choose>
	
		<c:when test="${no>300 }">최우수</c:when>
		<c:when test="${no>200 }">우수</c:when>
		<c:otherwise>양호</c:otherwise>
		
		
	</c:choose>
	<!-- test값이 true인 첫번째 when 태그의 내용을 출력
		test값이 true인 when 태그가 없으면 otherwise 태그의 내용을 출력 -->
		
	<%
/* 		//1부터 10까지 정수를 for문을 이용하여 출력 
		for(int i= 1; i<=10; i++)
			out.print(i);
		String[] arr = new String[]{"A","B","C"};
		//배열 arr의 요소들을 순서대로 출력 
		for(int i =0; i<arr.length; i++)
			out.print(arr[i]) ;
		//for(String s :arr){
		//	out.print(s); */
		
	%>
	<c:forEach var="i" begin="1" end="10" step="1" >[${i}]</c:forEach>
	<c:set scope="page" var="pa" value='<%= new String[]{"A","B","C"} %>' ></c:set>
	<c:forEach  var="s" items="${pa}">${s}</c:forEach>
	<ul>
		
		<c:forEach  var="s" items="${pa}" varStatus='st'>
		<li>
		
		${s} == ${st.current} 현재
		${st.current} 현재값,
		${st.index} 몇번째 반복인지(0부터),
		${st.count} 몇번째 반복인지(1부터),
		${st.first} 첫번째 반복인지여부,
		${st.last}  마지막반복인지여부,
		${st.begin} 태그의 begin속성값,
		$st.end}태그의 end속성값,
		${st.step} 태그의 step 속성값
			
		</li>
		
		</c:forEach>
		
		
	</ul>
	<c:forTokens var="t" items="a,b:c,d" delims=",:">[${t}]</c:forTokens> 
<!-- 	items에 지정한 문자열을 delims에 지정한 문자 기준으로 잘라서  -->
<!-- 	만든 문자열 배열의 요소들을 하나씩 var에 지정한 변수(속성)에  -->
<!-- 	대입하면서 반복출력  -->
		<h3>속성값출력</h3>
		<% pageContext.setAttribute("str","<h1>야채피자</h1>"); %>
		EL을 이용한 출력: ${str} <br>
		JSTL을 이용한 출력: <c:out value="${str}"></c:out> <br>
		<!-- 출력할 내영 중 HTML태그를 , 태그로 인식되지 않도록 출력 -->
		&lt;br&gt; 줄바꿈을 의미한다. 
		
		<h3>주소처리</h3>
		<a href="menu.jsp"> 메뉴 jsp 파일로 이동  </a>
		<a href="<%=request.getContextPath()%>/menu.jsp"> 메뉴 jsp 파일로 이동  </a>
		<a href="${pageContext.request.contextPath}/menu.jsp"> 메뉴 jsp 파일로 이동  </a>
		<c:set scope="application" var="cpath" value="${pageContext.request.contextPath}"></c:set>
		<a href="${cp}/menu.jsp"> 메뉴 jsp파일로 이동 </a>
		<a href= "<c:url value="/menu.jsp"/>"> 메뉴jsp 파일로 이동 </a>
		<!--  c:url 태그는 경로가 /로 시작하면 컨텍스트패스를 자동추가 -->
		
<%-- 		<% response.sendRedirect(request.getContextPath()+"memu.jsp"); %> --%>
<%-- 		<c:redirect url="/menu.jsp"/> --%>
		 	
		 	다른 jsp파일의 내용(소스코드)을 이곳에 복사한 후 하나의 jsp(서블릿) 파일로서 실행 
		 	<%@ include file="/menu.jsp" %>
		 	다른 jsp (또는 서블릿)을 실행한 후 그결과(출력내용)을 이곳에 포함. 
		 	<jsp:include page="/menu.jsp"/>
		 	jsp: include와 유사한 방식이지만, 다른 웹앱(사이트)도 포함 가능 
		 	<c:import url="/menu.jsp"/>
		 	
<%-- 		 	<c:import url="http://google.com"/> --%>

<!-- 	주소처리 태그들의 내용에  -->
<%-- 	<c:param name=""파라미터명">파라미터값</c:param> --%>
<!-- 	를 포함하여 생성되는 주소에 파라미터 추가 가능  -->
	
	<h3>예외처리</h3>
	<%
// 		try{	
// 			int x = 5/0;	
// 		}catch (Exception e) {
// 			out.print(e.getMessage());
// 		}
	%>
	<c:catch var="e">
		<% int x = 5/0; %>

	</c:catch>
	발생한 예외 : ${e.message}
	
</body>
</html>