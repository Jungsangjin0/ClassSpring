package com.kh.spring05;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

//번호로 이름, 가격을 수정
public class Test04 {
	@Test
	public void test() throws IOException {
		SqlSession sqlSession = MySqlSessionFactroy.factory().openSession(true);
		List<Product> list = sqlSession.selectList("product.searchProductByName");
	}

}
