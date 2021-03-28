package com.kh.spring10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

	
	@GetMapping("/first")
	@ResponseBody//뷰 리졸버를 사용하지 않겠다는 설정
	public String first() {
		int a = 10 / 0;
		return "first";
	}
	
	@GetMapping("/second")
	@ResponseBody//뷰 리졸버를 사용하지 않겠다는 설정
	public String second() {
		int[] a = new int[3];
		a[100] = 20; //ArrayIndexOutofBoundsException
		return "second";
	}
	
}
