package com.kh.spring05;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.kh.spring05.entity.Product;

//[3] 다양한 항목에 대한 검색
public class Test04 {
	
	@Test
	public void test() throws IOException {
		SqlSession sqlSession = JdbcTemplate.getSqlSessionFactory().openSession(true);
		
		String name = "";
		int price = 3000;
		
		//이름x 가격x : 목록
		//이름o 가격x : 검색
		//이름x 가격o : 검색
		//이름o 가격o : 검색
		Map<String, Object> map = new HashMap<>();
		map.put("name", name);
		map.put("price", price);
		map.put("start", 500);
		map.put("end", 3000);
		
		List<Product> list = sqlSession.selectList("product.list2", map);
		for(Product product : list) {
			System.out.println(product);
		}
	
	}
}
