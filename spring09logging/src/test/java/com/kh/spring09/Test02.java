package com.kh.spring09;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test02 {

	//로깅 도구(Logger) 생성
	//class를 알려주는 이유 : 어디서 출력했는지 알려주기 위해 :: 위치
	Logger log = LoggerFactory.getLogger(Test02.class);
	
	@Test
	public void test() {
		log.debug("이 메세지는 디버그 수준입니다.");
		log.info("이 메세지는 정보 수준입니다.");
		log.warn("이 메세지는 경고 수준입니다.");
		log.error("이 메세지는 오류 수준입니다.");
	}
}
