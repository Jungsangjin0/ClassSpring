package com.kh.spring05;
import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.transaction.annotation.Transactional;

import com.kh.spring05.entity.Product;

// [1] 독립 테스트로 상품 등록 구현
@Transactional
public class Test01 {

		
	
	@Test
	public void test() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		//데이터를 준비해서 insert를 수행
//		Product product = new Product();
//		product.setName("허니버터칩");
//		product.setPrice(2000);
//		sqlSession.insert("product.add", product);
		
		//빌더 패턴 사용(@Builder)
		Product product = Product.builder().name("참이슬").price(3000).build();
		sqlSession.insert("product.add", product);
		
	}
}
