package com.kh.spring15;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.DecimalFormat;
import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

//목표 : 독립 테스트로 랜덤 인증번호(String) 생성
@Slf4j
public class Test08 {

	
	@Test
	public void test() {
		Random r = new Random();
		//6자리니까 0 부터 999999까지 추첨 = 0부터 100000개
		int number = r.nextInt(1000000);
		log.info("number = {}", number);
		
		//DecimalFormat을 이용한 자리잡기
		// - 0은 숫자가 없어도 자리를 잡는 역할
		// -#은 숫자가 없으면 자리를 비우는 역할
		DecimalFormat f = new DecimalFormat("######");
		String cert = f.format(number);
		log.info("cert = {}", cert);
		
	}
}
