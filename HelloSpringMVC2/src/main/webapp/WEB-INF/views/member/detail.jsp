<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>목록 상세 조회</title>
</head>
<body>
	<h1>목록 상세 조회</h1>
	
	<hr/>
	<span><c:out value="${member.id }"/></span>
	<p/>
	<span><c:out value="${member.name }"/></span>
	<p/>
	<span><c:out value="${member.age }"/></span>
	<p/>
	<span><c:out value="${member.gender }"/></span>
	<p/>
	<span><c:out value="${member.address }"/></span>
	<p/>

</body>
</html>