package com.kh.spring05;

import java.io.IOException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

//전체 목록 번호순 조회
public class Test02 {

	@Test
	public void test() throws IOException {
		SqlSession sqlSession = MySqlSessionFactroy.factory().openSession(true);
		List<Product> list = sqlSession.selectList("product.searchList");
		for(Product product : list) {
			System.out.println(product);
		}
;	}
}
