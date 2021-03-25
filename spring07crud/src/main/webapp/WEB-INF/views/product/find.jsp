<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>${param.no}번 상품</h1>
	
	${requestScope.product}
	<hr>
	${product}
	
</body>
</html>