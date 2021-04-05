<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 접속버튼 -->
	<h1>그룹 웹소켓 예제</h1>
	<button id="connect">접속버튼</button>
	<button id="disconnect">종료버튼</button>
	<hr>
	<!-- 입력창 -->
	<input text="text" id="user-input" placeholder=""메세지 입력">
	<button id="send">전송</button>
	<!-- 출력창 -->
	<div class="text-wrapper"></div>
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script>
		//웹소켓 관련 처리
		// - javascript에도 websocket api가 존재
		$(function(){
			var socket;
				//연결 이벤트
				$("#connect").click(function(){
					//주소 정하고
					var uri = "ws://localhost:8001/spring16/basic"
					
					//웹소켓 연결(실제로는 소켓을 생성했다라고 표현)
					 socket = new WebSocket(uri);
					//연결된 다음 연결정보가 들어간다.
					
					//연결 이후에 수행할 작업들을 예약(콜백, callback) 설정
					//console.log(socket);
					socket.onopen = function() {
						console.log("연결 완료");
						$("<p>").text("서버에 접속했습니다.").appendTo(".text-wrapper");
					}
					socket.onclose = function() {
						console.log("연결 종료");
						$("<p>").text("서버와의 연결이 종료되었습니다..").appendTo(".text-wrapper");
					}
					socket.onerror = function() {
						console.log("연결 오류 발생")
					}
					
					socket.onmessage= function(message){
						console.log("메세지 수신");
						//console.log(arguments);//함수(메소드)에서 사용 가능한 매개변수 정보 객체
						//console.log(arguments[0]);
						console.log(message);
						console.log(messag.data);
						$("<p>").text(message.data).appendTo(".text-wrapper");
					}
					
					//전송 이벤트
					$("#send").click(function(){
						//입력값을 불러온다
						var input = $("#user-input").val();
						if(!input || input.length == 0) return;
						
						//전송
						socket.send(input);
						
						//입력창 지우기
						$("#user-input").val("");
					})
				});
				
				
				//종료 이벤트
				$("#disconnect").click(function(){
					//웹소켓 연결 종료(실제로는 소켓을 종료했다고 표현)
					socket.close();
				});
			
		})
		
	</script>
</body>
</html>