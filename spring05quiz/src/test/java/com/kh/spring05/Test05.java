package com.kh.spring05;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

//번호를 이용한 삭제
public class Test05 {

	@Test
	public void test() throws IOException {
		SqlSession sqlSession = MySqlSessionFactroy.factory().openSession(true);
		sqlSession.delete("product.remove");
	}
}
