package com.kh.spring16.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.BinaryWebSocketHandler;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

/*
 * Spring에서 WebSocket관련 처리를 수행하는 서버
 * - Controller과 별개로 작동한다.
 * - 사용자 요청처리이므로 serlvet-context.xml에 등록한다.
 * 
 * 구현방법
 * 1. 상속을 받는다.(interface WebsocketHandler, class TextWebsockethandler...)
 * 2. 필요한 메소드를 재정의
 * 		- afterConnectionEstablished : 연결이 수립된 직후 실행되는 메소드
 * 		-handleTextMessage : 클라이언트의 메세지를 수신한 직후 실행되는 메소드
 * 		-afterConnectionClosed : 연결이 종료된 직후 실행되는 메소드
 * 3. 서버를 servlet-context.xml에 등록
 * */
//public class WebsocketBasicServer implements WebSocketHandler{
//public class WebsocketBasicServer extends BinaryWebSocketHandler{ //2진데이터 주고받기
@Slf4j
public class WebsocketBasicServer extends TextWebSocketHandler {
	//채팅 알람
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		//websocketSession 웹소켓 접속자 정보
		log.info("afterConnectionEstablished 실행!");
		log.info("session = {}", session);
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//websocketSession 웹소켓 접속자 정보
		//text : 수신 메세지 정ㅇ보
		// - payload : 실제 전송 텍스트
		// - byteCount : 실제 전송 데이터 크기
		// - last : 마지막 데이터임을 표시(분할 전송 시)  
		log.info("handleTextMessage 실행");
		log.info("session = {}", session);
		log.info("message = {}", message);
		//보낸 사용자에게만 회신(에코, echo)
		session.sendMessage(message);
		
		super.handleTextMessage(session, message);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		//websocketSession 웹소켓 접속자 정보
		// 사용자 종료 상태 정보
		log.info("afterConnectionClosed 실행!");
		log.info("session = {}", session);
		log.info("status = {}", status);
		super.afterConnectionClosed(session, status);
	} 
	
	
}
