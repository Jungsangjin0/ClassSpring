package com.kh.spring07.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring07.entity.Product;

@Controller
public class ProductController {

	
	private SqlSession sqlSession;
	
	@Autowired
	public ProductController(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
//	@RequestMapping(value="/insert", method=RequestMethod.GET)
	@GetMapping("/insert")
	public String insert() {
		
//		return 등록 페이지(jsp);
//		return "/WEB-INF/views/insert.jsp"
		return "insert";
		
	}
	
// @RequestParam : 파라미터 1개를 받는 설정
//	@ModelAttribute : 주어진 객체에 알아서 잘 받는 설정
	
//	@RequestMapping(value="/insert", method=  RequestMethod.POST)
	@PostMapping("/insert")
	public String insert(
//						@RequestParam(value="name") String name, 
//						@RequestParam(value="price")int price
						@ModelAttribute Product product
						) {
		
		System.out.println(product.getName());
		System.out.println(product.getPrice());
		
//		mybatis를 이용한 등록
//		return "사용자에게 보여질 내용";
		
		sqlSession.insert("product.add", product);
		
		return "insert2";
	}
	
}
