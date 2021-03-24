package com.kh.spring05;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

//[6] 카운트 구하기
public class Test07 {
	
	@Test
	public void test() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		int count = sqlSession.selectOne("product.count");
		System.out.println(count);
	}

}
