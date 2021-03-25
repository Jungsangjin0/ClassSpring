package com.kh.spring07.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring07.entity.Product;

@Controller
public class ProductController {

	
	private SqlSession sqlSession;
/////////////////////////////////////////////////
	// 등록 처리 컨트롤러
	// - 주소를 2개로 나눠서 처리
	// - 마치면 반드시 리다이렉트 처리(새로고침 및 뒤로가기 방지)
/////////////////////////////////////////////////

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
//		response.sendRedirect(주소)
		return "redirect:insert_finish";
	}
	
	@GetMapping("/insert_finish")
	public String insertFinish() {
		return "insert2";
	}
	
	
/////////////////////////////////////////////////
// 등록 처리 컨트롤러
// - 주소를 2개로 나눠서 처리
/////////////////////////////////////////////////
//Model은 JSp와 연결되어 있고 JSP에 출력할 데이터를 전달할 때 사용
// = control과 view를 연결하는 도구
	@GetMapping("/list")
	public String list(
			Model model,
			//검색을 위한 파라미터: type(분류), keyword(검색어)
			@RequestParam(required = false) String type,
			@RequestParam(required = false) String keyword,
			//정렬을 위한 파라미터 : category(분류), order(방식)
			@RequestParam(required = false, defaultValue = "no") String category,
			@RequestParam(required = false, defaultValue = "asc") String order
			) {
		//마이바티스를 이용해서 product 테이블 목록을 불러오고
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("keyword", keyword);
		map.put("category", category);
		map.put("order", order);
		
		
		List<Product> list = sqlSession.selectList("product.list", map);
		model.addAttribute("list", list);
		
		return "product/list";
	}
}
