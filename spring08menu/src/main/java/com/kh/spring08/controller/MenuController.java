package com.kh.spring08.controller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;

@Controller
@RequestMapping("/menu") //공용매핑 - 컨트롤러간 충돌 방지
public class MenuController {
	
	
	private final SqlSession sqlsesion;
	
	@Autowired
	public MenuController(SqlSession sqlSession) {
		this.sqlsesion = sqlSession;
	}
	//메뉴 등록 매핑
	// - GET : 입력 페이지 전송
	// - POST : 처리 후 다른 곳으로 리다이렉트
	
	@GetMapping("/add")
	public String add() {
		
		return "menu/add";
	}
	
	//파일 업로드가 이루어질 경우 컨트롤러에서는 ??? 형태로 수신한다.
	//MultipartFile 형태로 수신한다.
	@PostMapping("/add")
	public String add(
//				@RequestParam String name,
//				@RequestParam String type,
//				@RequestParam int price
			@ModelAttribute Menu menu,
			@RequestParam MultipartFile im
			) {
		System.out.println(im.getName());
		System.out.println(im.getOriginalFilename());
		System.out.println(im.getContentType());
		System.out.println(im.getSize());
		
		sqlsesion.insert("menu.add", menu);
	return "redirect:add";
	}
}
