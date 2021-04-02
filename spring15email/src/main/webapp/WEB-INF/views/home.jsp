<%@ page contentType="text/html; charset=UTF-8"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	로그인 정보 : ${user}
</h1>
<h1><a href="login">로그인</a></h1>
<h1><a href="logout">로그아웃</a></h1>

<!--  로그인 상태일 때 인증번호 보내기 버튼과 인증번호 입력창을 표시 -->
<c:if test="${not empty user}">
	<script src="<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>"></script>
	<script>
		//[1] send버튼을 누르면 이메일을 보내달라고 비동기 요청을 수행
		$(function(){
			$("#send").click(function(){
				$.ajax({
					url:"${pageContext.request.contextPath}/cert/send",
					<%-- <%=request.getContextPath()%> --%>
					type:"get",
					success : function(data){
						alert("이메일로 인증번호가 발송되었습니다.");
					},
					error : function(){
						alert("서버에서 오류가 발생했습니다.");
					}
				})
				
			})
			
		})
	</script>

	<button id="send">인증번호 보내기</button>
	<br>
	<input type="text">
	<button>확인</button>
</c:if>
</body>
</html>
