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
 * 		- afterConnectionEstablished
 * 		-handleTextMessage
 * 		-afterConnectionClosed
 * 3. 서버를 servlet-context.xml에 등록
 * */
//public class WebsocketBasicServer implements WebSocketHandler{
//public class WebsocketBasicServer extends BinaryWebSocketHandler{ //2진데이터 주고받기
@Slf4j
public class WebsocketBasicServer extends TextWebSocketHandler {
	//채팅 알람
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("afterConnectionEstablished 실행!");
		super.afterConnectionEstablished(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.info("handleTextMessage 실행");
		super.handleTextMessage(session, message);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info("afterConnectionClosed 실행!");
		super.afterConnectionClosed(session, status);
	} 
	
	
}
