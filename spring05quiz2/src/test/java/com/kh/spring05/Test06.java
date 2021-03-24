package com.kh.spring05;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

//[5] 특정 목록에 포함되는 항목을 검색(in 연산)
public class Test06 {

	@Test
	public void test() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		//목록을 준비해서 전달한 뒤 구문 실행
		// = 참이슬, 처음처럼, 테라, ... = String[] = List<String>
		
		List<String> require = Arrays.asList("참이슬", "처음처럼", "테라"); //자바 8
//		List<String> require = List.of("참이슬", "처음처럼", "테라"); //자바 9 이상
		
		List<Product> list = sqlSession.selectList("product.list4", require);
		for(Product product : list) {
			System.out.println(product);
		}
	}
	
}
