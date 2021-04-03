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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	
	<script>
		//[1] send버튼을 누르면 이메일을 보내달라고 비동기 요청을 수행
		// - 버튼을 누르면 끝나기 전까지 비활성화(disabled)
		//[2] confirm버튼을 누르면 입력된 인증번호를 검사하여 결과 알림
		//압력이 안되어있을때는 검사를 하지 않는다.
		$(function(){
			$("#send").click(function(){
				//버튼 비활성화
				$.ajax({
					url:"${pageContext.request.contextPath}/cert/send",
					<%-- <%=request.getContextPath()%> --%>
					type:"get",
					beforeSend : function() {
						//비활성화
						$("#send").prop("disabled", true);
					},
					success : function(data){
						alert("이메일로 인증번호가 발송되었습니다.");
					},
					error : function(){
						alert("서버에서 오류가 발생했습니다.");
					},
					complete:function() {
						//활성화
						$("#send").prop("disabled", false);
					}
				})
				
			})
			//[2]
			$("#confirm").click(function(){
				var text = $("#user-input").val(); //사용자 입력값 로딩
				var regex = /\d{6}/g;
				if(!regex.text(text))return;
				/* if(!text || text.length != 6) return; */
				$.ajax({
					url : "${pageContext.request.contextPath}/cert/check",
					type : "get",
					data : {//보낼 데이터
						number:text
					},
					dataType:"text",//기대하는 결과 ㄱ밧
					success:function(data) {
						if(data == "Y"){
							alert("인증성공");
							//이후에 처리할 내용들
						}else {
							alert("인증번호가 맞지 않습니다.");
						}
					}
				})
				
			})
			
		})
	</script>

	<button id="send">인증번호 보내기</button>
	<br>
	<input type="text" id="user-input">
	<button id="confirm">확인</button>
</c:if>
</body>
</html>
