package com.kh.spring05;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

//상품명을 이용한 검색
public class Test03 {

	@Test
	public void test() throws IOException {
		SqlSession sqlSession = MySqlSessionFactroy.factory().openSession(true);
		List<Product> list = sqlSession.selectList("product.searchProductByName");
		for(Product product : list) {
			System.out.println(product);
		}
	}
}
