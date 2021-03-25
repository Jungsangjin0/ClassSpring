<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>edit.jsp</h1>
	<h3>${product}</h3>
	
	<form action="edit" method="post">
		<input type="hidden" name = "no" value="${product.no}">
		<br><br>
		이름 : <input type="text" name ="name" value="${product.name }">
		가격 : <input type="text" name="price" value="${product.price }">
		<br><br>
		<input type="submit" value="수정" >
	</form>
	
</body>
</html>