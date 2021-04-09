package com.kh.spring16.vo;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.socket.WebSocketSession;

/*
 * 채널 클래스
 *  - 방을 번호로 관리하는 클래스
 * 
 * */
public class Channel {
	
	//방 저장소
	private Map<Integer, Room> storage = new HashMap<>();
	
	
	
	//방 접속 : 방 번호 + 사용자 정보
	public void enter(int roomNumber, WebSocketSession session) {
		
		if(!storage.containsKey(roomNumber)) {
			//방번호가 없다면
			storage.put(roomNumber, new Room()); //방 생성
		}
		//사용자를 방에 추가
		storage.get(roomNumber).enter(session); //채팅방을 꺼내는 코드
		//해당하는 방에 메세지를 전송(선택)
	}
	
	//방 나가기 : 방 번호 + 사용자 정보
	public void leave(int roomNumber, WebSocketSession session ) {
		//type이 leave라면
		//사용자를 방에서 제거
		storage.get(roomNumber).leave(session);
		
		if(storage.get(roomNumber).isEmpty()) {
			//storage.get(m.getRoom).size() == 0
			//사용자가 한명도 없다면
			storage.remove(roomNumber); //방삭제
		}
	}
	
	//방에 메세지 전송 : 방 번호 +  메세지(String)
	public void sendRoom(int roomNumber, String json) throws IOException {

		Room room = storage.get(roomNumber);//방 정보를 꺼낸다
		//방이없으면 중지 or 에러
		
//		room.broadcast(JSON형태 :: 메세지);
		if(room != null) {
			room.broadcast(json);
		}
		
	}
	//전체 메세지 전송 :  메세지(String)
	// - sendRoom을 전체 방 개수만큼 반복
	public void sendAll(String json) throws IOException {
		for(int roomNumber : storage.keySet()) {
			sendRoom(roomNumber, json);
		}
	}
}
