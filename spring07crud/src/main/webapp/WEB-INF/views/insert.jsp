<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>insert.jsp(상품등록)</h1>
	
	<%-- <form action="${pageContext.request.contextPath}/insert" method="post"> --%>
	<form action="insert" method="post">
		이름 : <input type="text" name="name" required>
		<br><br>
		가격 : <input type="number" name="price"  required>
		<br><br>
		<input type="submit" value="등록">
	</form>
</body>
</html>