package com.kh.spring05;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class Test10 {

	@Test
	public void test() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		//삭제 : 매우 위험한 작업(조건 없이 벌어지면 안됨. Primary key를 이용)
		// = 앞부분에 select등을 추가하여 안정하게 삭제가 이루어지도록 검사해야함
		int no = 1;
		int result = sqlSession.delete("product.remove", no);
		System.out.println(result);
		
	}
}
