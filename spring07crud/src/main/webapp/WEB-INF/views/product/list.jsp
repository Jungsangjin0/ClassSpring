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
	<h1>목록 페이지</h1>
	<c:forEach var="product" items="${requestScope.list}"> <!-- list -->
		<h2>
			${product.no}
			/
			${product.name}
			/
			${product.price}
			/
			${product.reg}
		</h2>
	</c:forEach>
</body>
</html>