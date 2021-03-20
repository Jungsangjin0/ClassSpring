package com.kh.spring05;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

//필요한 정보를 준비하여 등록처리
public class Test01 {

	@Test
	public void test() throws IOException {
//		InputStream in = Resources.getResourceAsStream("mybatis/please.xml");
//		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		SqlSession sqlSession = MySqlSessionFactroy.factory().openSession(true);
				
	}
}
