package com.kh.spring02;

import org.junit.Test;

/*
 *  TDD(Test Driven Development)
 * 	- 테스트 주도 개발
 * 	- 큰 규모의 서비스(ex : 웹)을 만들 때 부분별로 테스팅을 진행하면서 개발
 *  - 네거티브하게 진행할 것
 *  - 작은 단위를 확실하게 개발하기 위한 방법
 *  
 *  비연동(독립) 테스트 : Spring의 도구들을 사용하지 않는 테스트
 *  ex ) jdbcTemplate사용 안하고 root-context
 *  연동 테스트 : spring의 도구들을 사용하는 테스트
 *  - 연동을 위해서는 필요한 설정들이 존재
 */
public class Test01 {
	
	/*
	 *  독립 테스트를 위해서는 테스트 메소드가 필요
	 *  - 반드시 pulbic
	 *  - 반드시 void
	 *  - 반드시 매개변수가 없어야 한다.
	 *  - 메소드의 이름은 자유
	 *  - @Test 필요
	 * */
	@Test
	public void test() {
		System.out.println("Hello!");
	}
}