<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>목록</h1>
	<c:forEach var="menu" items="${list}">
		<h3>
			<!-- <img src="http://localhost:8081/spring08/menu/download?no=xxx"> -->
			<!-- <img src="/spring08/menu/download?no=xxx"> -->
			<%-- <%= request.getContextPath() %> --%>
			<%-- <img src="${pageContext.request.contextPath}/menu/download?no=xxx"> --%>
			<!-- 같은 메뉴에 있는 파일이기때문에 상대경로-->
			<img  src="download?no=${menu.no}" width="100px" height="100px">
			<a href="download?no=${menu.no }">다운로드</a>
		${menu.name} / 
		${menu.name }/
		${menu.price }원
		</h3>
	</c:forEach>
	
</body>
</html>