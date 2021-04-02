package com.kh.spring15;

import static org.junit.Assert.assertEquals;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.spring15.entity.Cert;

import lombok.extern.slf4j.Slf4j;

//목표 : 이메일과 인증번호가 주어졌을 때 인증번호가 유효한지 검사
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Slf4j
public class Test09 {

	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void test() {
		//데이터 준비 : 이메일, 인증번호
		String email = "loky13@daum.net";
		String number = "112233";
		
		//검사 ::일치하는 값이 있는가
		Cert cert = Cert.builder().who(email).what(number).build();
		int count = sqlSession.selectOne("cert.checkWithTimeLimit", cert);
		//count : 0, 1

		assertEquals(count, 1); //단정문 : 성공을 기대하는 값(count == 1)
		
		if(count == 1) {
			log.info("인증 성공");
			//인증 성공 시 인증정보 삭제
			sqlSession.delete("cert.remove", cert);
		}else {
			//시간초과로 인한 실패 시 남아있어도 됨
			//새로 발급받으면 update 되기때문에
			log.info("인증 실패");
		}
	}
}
