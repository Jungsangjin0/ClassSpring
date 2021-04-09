package com.kh.spring16.websocket;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.spring16.vo.Message;

import lombok.extern.slf4j.Slf4j;

/*
 * 사용자들을 방으로 분리해서 처리할 수 있는 서버
 * */
@Slf4j
public class WebsocketRoomServer2 extends TextWebSocketHandler{
	
	//사용자 저장소
	// - 현재 목표는 단순한 사용자 보관이 아니라 방번호를 이용하여 구분
	//set은 단순히 사용자를 넣는 것 :: 방하나
	// -webSocketSession과 방번호를 합쳐서 하나의 사용자로 관리
	// - users와 같은 저장소(방)을 아주 많이 많드는 방법
//	private Set<WebSocketSession> users = new HashSet<>(); 1개의 방
	private Map<Integer, Set<WebSocketSession>> storage = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
	}
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		//[1] 사용자가 보낸 메세지의 payload가 JSON이다
		// - {"room":1, "type":"message", "content":안녕하세요}
		// - 따라서, 수신한 JSON을 객체로 변홚줘야 한다(jackson-databind)
		//직접씀
		ObjectMapper mapper = new ObjectMapper();
		//원하는 데이터 변수
//		Message m = messag의 payload를 객체로 변환한 결과;
		Message m = mapper.readValue(message.getPayload(), Message.class); //string
		
		//[2]읽은데이터의 종류(type)에 따라 다르게 처리하도록 구성
		//[3] 각각의 상황에 맞는 처리를 구현
		// - enter : storage에서 방번호를 찾은 뒤 사용자를 추가
//					- 방이 없으면 방을 생성한 뒤 사용자를 추가
		//  		-방이 있으면 사용자를 추가
		// - leave : storage에서 방번호를 찾은뒤 사용자를 제거
		// 			- 사용자를 제거
		//			- 사용자가 다 사라졌다면 방을 제거
		
		// - message : storage에서 방번호를 찾은 뒤 해당 방 사용자에게 전송
		// 			- 방번호가 0이면 모두에게 전송
		log.info("payload = {}", message.getPayload());
		log.info("m = {}", m);
		if(m.getType().equals("enter")) {//type이 enter라면
			if(!storage.containsKey(m.getRoom())) {
				log.info("사용자가 {} 번방에 접속했습니다.", m.getRoom());
				//방번호가 없다면
				storage.put(m.getRoom(), new HashSet<>()); //방 생성
			}
			//사용자를 방에 추가
			storage.get(m.getRoom()).add(session); //채팅방을 꺼내는 코드
			//해당하는 방에 메세지를 전송(선택)
			
		}else if(m.getType().equals("leave")) {
			log.info("사용자가 {} 번방에 접속종료 했습니다..", m.getRoom());
			//type이 leave라면
			//사용자를 방에서 제거
			storage.get(m.getRoom()).remove(session);
			
			if(storage.get(m.getRoom()).isEmpty()) {
				//storage.get(m.getRoom).size() == 0
				//사용자가 한명도 없다면
				storage.remove(m.getRoom()); //방삭제
			}
		}else if(m.getType().equals("message")) {
			//type이 message라면
			
			if(m.getRoom() == 0) {
				//방번호가 0이라면 전체에게 메세지를 전송
				
			} else {
				// 해당 방에만 메세지를 전송
				// 전달된 메세지를 그냥 보내는 것이 아니라 필요한 처리(시간, 아이디) 수행 후 전송
				// 변환된 메세지 객체인 m에 필요한 처리를 한 뒤 JSON으로 변환하여 사용자에게 전송
				
				// 보낼 메세지에 시간 추가
				Date date = new Date();
				Format f = new SimpleDateFormat("a h:mm");
				m.setTime(f.format(date));
				
				
				String json = mapper.writeValueAsString(m);//JSON 변경 객체 -> JSON
				TextMessage response = new TextMessage(json); //신규 메세지 생성
				
				for(WebSocketSession user : storage.get(m.getRoom())) {
					user.sendMessage(response);
					
				}
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
	}
		
	
}
