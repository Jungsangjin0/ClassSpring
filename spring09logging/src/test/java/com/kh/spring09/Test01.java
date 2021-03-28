package com.kh.spring09;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test01 {

	//Logger(로깅 도구)생성
	// - import는 무조건 slf4j를 선택
	// loggerFacotry 또한 slf4j를 import
	Logger log = LoggerFactory.getLogger(Test01.class);
	
	@Test
	public void test() {
		//표준 출력
		// - 어디서 출력했는지 알 수가 없음
		// - 언제 출력했는지 알 수가 없음
		// - 언젠가는 지워야 함
		// - 출력 위치를 정할 수가 없음(무조건 콘솔에 나옴)
		System.out.println("Hello World");
		
		//Logger를 이용한 출력
		log.info("Hello world!");
	}
	
}
