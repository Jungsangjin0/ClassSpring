package com.kh.spring14.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect // 간섭 객체임을 설정
@Component //spring bean 등록
@Slf4j
public class LoggingAspect {

	//클래스 내부에 Advice를 만들어서 간섭을 수행
	// - 간섭(잔소리) 타이밍에 따라 구분
	// - Before, AfterReturning, AfterThrowing, After, Around
	//before 전 // 정상적으로 끝났을 때 ,문제발생 시, 일단 끝난 후, 전반적으로
	
	//Advice 메소드 :: 간섭하는 내용 = @Before //interface를 사용해도된다.
	//("target(com.kh.spring14.repository.MemberDao)") - Pointcut(목적지) 대상
	@Before("target(com.kh.spring14.repository.MemberDaoImpl)")
	public void before() {
		//간섭할 코드 작성
		log.info("before 메소드 실행");
	}
	
	@After("target(com.kh.spring14.repository.MemberDaoImpl)")
	public void after() {
		log.info("after 메소드 실행!");
	}
}
