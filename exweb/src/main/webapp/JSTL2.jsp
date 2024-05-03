<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL</title>
</head>
<body>
	<h1>JSTL</h1>
	<h2>국제화/포매팅</h2>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	<h3> 자바 날짜시간 객체와 문자열 상호변환</h3>
	<% pageContext.setAttribute("d",new Date()); %>
	
	현재시간: ${d}
	<fmt:formatDate value="${d}" pattern="yyyy-MM-dd HH:mm:ss"/>
<!-- 	value에 지정한 date 객체를 pattern에 지정한 형식의 문자열로 변환 -->
<!-- 		pattern 값은 자바의 SimpleDateFormat 클래스의 패턴과 동일 -->


	<fmt:parseDate value="2024/01/01 13:24:56" pattern= "yyyy/MM/dd HH:mm:ss" var="d2"/>
	${d2} <br>
	
	<fmt:formatDate value="${d}" dateStyle="full" timeStyle="full" type="both"/>
	<!-- 	type 속성에 date, time, both 사용가능 -->
	<!-- 	dateStyle, timeStyle 속성에 default, short, medium,long,full 사용가능  -->
	
	<fmt:setTimeZone value="Asia/Seoul"/>
	< 이후의 모든 formatDate, parseDate 태그들이 사용할 기본시간대 지정 -
	<!-- 	시간대는 자바의 TimeZone 클래스가 지원하는 "대륙/도시" 문자열 또는  --> 
	<fmt:formatDate value="${d}" pattern= "yyyy/MM/dd HH:mm:ss" var="d2"/>
	<!-- 	GMT+9와 같은 커스텀 문자열 사용가능 -->
	
		<fmt:setTimeZone value="GMT+5" var="z" scope="page"/>
		<fmt:formatDate value="${d}" pattern="yyyy/MM/dd HH:MM:SS"/><br>		
		<fmt:formatDate timeZone="${z}" value="${d}" pattern="yyyy/MM/dd HH"/>
		
		<fmt:timeZone value="GMT+0">
<!-- 			timezone 태그 내부에만 적용할 시간대 설정 -->
		<fmt:formatDate value="${d}" pattern = "yyyy/MM/dd HH:mm:ss"/> <br>
	</fmt:timeZone>
	
	<h3> 숫자와 문자열의 상호변환</h3>
	<% pageContext.setAttribute("n", 12345.67); %>
	${n} <br>
	<fmt:formatNumber value="${n}" pattern="###,###.###"/> <br>
	<fmt:formatNumber value="${n}" pattern="000,000.000"/> <br>
	<fmt:formatNumber value="${n}" pattern="#,###.#"/> <br>
	<fmt:formatNumber value="${n}" type="number"/> <br>
	<fmt:formatNumber value="${n}" type="percent"/> <br>
	<fmt:formatNumber value="${n}" type="currency"/> <br>
	<!-- 	문자열을 숫잘 변환 -->
	<fmt:parseNumber value ="12,345.67" pattern="###,###.###" var = "n2"/>
	${n2}<br>
	
	<h3>JSTL 국제화 태그들이 사용할 로케일 지정</h3>
<%-- 	<fmt:setLocale value="ko_KR"/>  --%>
<!-- 	로케일은 "언어_국가", "언어-국가", "언어", "국가" 형태로 지정  -->
<!-- 	언어는 ISO 639-1, 국가는 ISO 3166-1 alpha-2 에 지정된 코드 사용  -->
<!-- 	자바의 Locale 클래스와 설정방식 동일  -->
<!-- 	로케일 미지정시, Accept-Language 요청헤더 값 사용 -->
	<fmt:formatNumber value="${n}" type="currency"/> <br>
	
	<h3>프로퍼티 파일에 저장되어 있는 값을 출력</h3>
	<fmt:setBundle basename="msg"/>
<!-- 	이후에 나오는 message 태그들이 사용할 메시지 번들을 설정.  -->
<!-- 	프로퍼티 파일이 "클래스패스/폴더명/번들명_언어_국가.properties"이면 " -->
<!-- 	basename에 "폴더명. 번들명"라고 지정  -->
	<fmt:message key="str"/>
	
	
	<fmt:setBundle basename="msg" scope="page" var="mb"/>
	<!--  메시지 번들을 지정한 스코프에 속성으로 저장 -->
	<fmt:message key="str" bundle="${mb}"/>
	<!-- 특정 메시지 번들에 존재하는 메시지를 찾아서 출력 -->
	
		<fmt:bundle basename="msg" prefix="s">
			<!--fmt:bundle 태그 내부의 fmt:Bundle 태그들만이 사용하는 메시지 번들-->
			<fmt:message key="tr"/>
		</fmt:bundle>
	<!-- fmt:param 태그를 사용하여 메시지의 {0},{1}...룰 대체할값 저장 -->
		
		<fmt:message key="str">
			<fmt:param value="JSP" />
			<fmt:param value="!!!" />
		</fmt:message>
		<h2>함수</h2>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<br> ${fn:length("aBcD")} <%="aBcD".length()%> ${"aBcD".length()} 
<br> ${fn:contains("aBcD","Bc")} <%="aBcD".contains("Bc")%> ${"aBcD".contains("Bc")}   
<br> ${fn:containsIgnoreCase("aBcD","bC")}  <%="aBcD".toLowerCase().contains("bC".toLowerCase())%> ${"aBcD".toLowerCase().contains("bC".toLowerCase())}
<br> ${fn:startsWith("aBcD","aB")} <%="aBcD".startsWith("aB")%> ${"aBcD".startsWith("aB")}
<br> ${fn:endsWith("aBcD","cD")} <%="aBcD".endsWith("cD")%> ${"aBcD".endsWith("cD")}
<br> ${fn:escapeXml("<h1>제목</h1>")} <c:out value="<h1>제목</h1>" /> 
<br> ${fn:indexOf("aBcD","Bc")} <%="aBcD".indexOf("Bc")%> ${"aBcD".indexOf("Bc")} 
<%
	String[] arr = {"A","B","C"}; 
	pageContext.setAttribute("pa", arr);
	pageContext.setAttribute("pl", new java.util.ArrayList<String>(java.util.Arrays.asList(arr))); 
%>
<br> ${fn:join(pa,"::")} <%=String.join("::", arr)%> ${String.join("::", pl)} <br> ${(fn:split("a,B:c,D",",:"))[2]} <%="a,B:c,D".split("[,:]")[2]%> ${"a,B:c,D".split("[,:]")[2]} 
<br> ${fn:replace("aBcDBc","Bc","efg")} <%="aBcDBc".replace("Bc","efg") %> ${"aBcDBc".replace("Bc","efg")}
<br> ${fn:substring("aBcD", 1, 2)} <%="aBcD".substring(1,2)%> ${"aBcD".substring(1,2)}
<br> ${fn:substringAfter("aBcD", "Bc")}  <%="aBcD".substring( "aBcD".indexOf("Bc") + "Bc".length() )%>  ${"aBcD".substring("aBcD".indexOf("Bc")+"Bc".length())} 
<br> ${fn:substringBefore("aBcD", "Bc")} <%="aBcD".substring(0, "aBcD".indexOf("Bc") )%> ${"aBcD".substring(0,"aBcD".indexOf("Bc"))}
<br> ${fn:toLowerCase("aBcD")} <%="aBcD".toLowerCase()%> ${"aBcD".toLowerCase()}
<br> ${fn:toUpperCase("aBcD")} <%="aBcD".toUpperCase()%> ${"aBcD".toUpperCase()}
<br> [${fn:trim("   aB cD  ")}] [<%="   aB cD  ".trim()%>] [${"   aB cD  ".trim()}]
		
	
		
</body>
</html>