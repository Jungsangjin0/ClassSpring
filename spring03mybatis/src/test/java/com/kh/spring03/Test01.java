package com.kh.spring03;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

//스프링에 의지하지 않고 등록한 mybatis의존성을 가지고 직접 도구를 생성
// -> 독립 테스트로 진행
public class Test01 {

	//mybatis 에서 사용하는 도구
	//SqlSessionFactory : 명령 실행을 위한 환경을 구축해주는 역할(설정을 불러와야 함 : xml )
	//SqlSession : 실제 명령을 실행하는 도구
	
	
	@Test
	public void test() throws IOException {
		//설정 로딩		//mybatis 클래스
		InputStream in = Resources.getResourceAsStream("mybatis/please.xml");
		//파일을 불러오는 입력 스트림
		
		//도구 생성
		//인터페이스
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		System.out.println(factory);
		SqlSession sqlSession = factory.openSession();
		System.out.println(sqlSession);
	}
}
