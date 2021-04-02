package com.kh.spring15;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.spring15.entity.Cert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class Test06 {

	@Autowired
	private SqlSession sqlSession;
	
	//목표 : 데이터베이스에 인증번호를 넣어서 추가 or 갱신이 잘 되는지 확인
	@Test
	public void test() {
		//인증번호 객체 생성 : who(회원아이디), what(인증번호), when(설정x)
		 Cert cert = Cert.builder().what("18451").who("hello").build();
		 
		 //등록
		 sqlSession.insert("cert.add", cert);
	}
}
