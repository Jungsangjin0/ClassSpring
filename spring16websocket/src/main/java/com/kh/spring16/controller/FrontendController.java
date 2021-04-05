package com.kh.spring16.controller;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//웹소켓 화면으로 연결해주는 컨트롤러
@Controller
public class FrontendController {

	@GetMapping("/example1")
	public String basic() {
		return "websocket/example1";
	}
}
