<%@ page contentType="text/html; charset=UTF-8"%>

<!-- 
	아이디 중복검사 화면 구성
	- form과는 별개로 창 하나만 가지고 구현
	- ajax를 이용해서 서버에 검사를 요청
	- 결과에 따라 메세지를 출력하거나 기타 처리를 수행
	- 검사 시점 : 사용자가 입력하는 순간(oninput, onkeyup, onkeydown, onkeypress) vs 사용자 입력이 종료된 순간(onblur)
 -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<%-- $(document).on("ready", function(){})
 	${document}.ready(function(){});
 	$().ready(function(){})
 		/* $("input[name=id]").blur(function(){}) */
	 		/* $.get(주소,{옵션})
			$.post(주소, 데이터, {옵션}) */
			/* /[a-zA-Z0-9] \w->word*/
			/* url : "http://localhos:8001/spring13/rest/id", */
			//text/xml/json/html -> 받고싶은 데이터
			//비동기 통신은 vanillaJS, jquery, axios 등으로 보낼 수 있다(선택)
 		--%>
<script>
 
 		var base ="${pageContext.request.contextPath}";
 	$(function(){
 		//자바스크립트에서는 절대경로를 알 수 있는 경우가 없다.
 		//이 페이지에서 사용할 수 있는 el이라는 명령어를 이용	
 		//아이디 입력창에 blur 이벤트를 설정
 		$("input[name=id]").on("blur", function(){
 		var regex = /\w{4,20}/g; 
 		var id = $(this).val();
 		if(!regex.test(id))return;
 		
 		$.ajax({
 			url : base+"/rest/id",
 			type : "get",
 			data : {
 				id:id
 			},
 			dataType : "json", 
 			success : function(data) {
 				console.log(data)
 				if(json.count == 0) {
 					console.log("아이디 사용 가능");
 				}else {
 					console.log("이미 사용중인 아이디");
 				}
 			}
 			
 		})
 		
 		})
 		
 	})
 </script>
<input type="text" name="id" placeholder="아이디" />