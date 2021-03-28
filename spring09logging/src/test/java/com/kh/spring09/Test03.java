package com.kh.spring09;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

//로깅 도구 또 생성?
//- log4j에는 지원하지 않지만 lombok에서 지원함
// -사용하고 싶은 클래스 위에 @Slf4j 작성
@Slf4j
public class Test03 {

	//private static final Logger log = LoggerFactory.getLogger(~);
	
	@Test
	public void test() {
		log.debug("이 메세지는 디버그 수준입니다.");
		log.info("이 메세지는 정보 수준입니다.");
		log.warn("이 메세지는 경고 수준입니다.");
		log.error("이 메세지는 오류 수준입니다.");
		
		log.debug("출력 : {}, {}, {}", 1 , 2, 3);
	}
	
}
