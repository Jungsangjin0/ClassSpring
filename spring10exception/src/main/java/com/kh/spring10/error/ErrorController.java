package com.kh.spring10.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring10.controller.TestController;

import lombok.extern.slf4j.Slf4j;

/*
 * 오류 처리 전담 컨트롤러
 * - 사용자가 요청할 수 없고 컨트롤러에서 오류가 발생하면 자동으로 호출되어야 함
 * - 컨트롤러라고 볼 수는 없지만 비슷한 기능을 가져야 한다.
 * - 컨트롤러에 간섭(Advice)를 수행하는 도구
 * 
 * @ControllerAdvice(옵션)
 * basePackages -> 패키지 전체를advice함
 * 
 * */
//@ControllerAdvice(basePackages = "com.kh.spring10.controller")
//@ControllerAdvice(basePackageClasses = {TestController.class}) //클래스
@ControllerAdvice(annotations = {Controller.class, RestController.class})
@Slf4j
public class ErrorController {
	
	/*
	 * 실제로 오류가 발생하면 수행될 매핑과 유사한 형태의 메소드= ExceptionHandler
	 * - 예외의 종류를 지정해야 한다.
	 * - 모아서 처리하고 싶다면 Excpertion 또는 Throwable을 사용한다.
	 * throwable 쓴다면 코드 오류까지 처리해주겠다.
	 * 내가 만들어서 내가 실행시키면 Exception 남의 코드를 실행하면 Throwable
	 * - ExceptionHandler는 존재의 이유가 예외 처리기 때문에 예외 객체를 사용할 수 있다.
	 * - 그 외 @RequestMapping 에서 사용하는 모든 도구들이 사용 가능(ex : 세션)
	 * */
	@ExceptionHandler(ArithmeticException.class) //오류가 나면 실행됨.
	public String first() {
		//logger를 이용하여 오류 기록을 남김
		log.error("오류 발생");
		
		return "error/first";
	}
	
	@ExceptionHandler(Exception.class)
	public String second(Exception e) {
		log.error("오류 발생", e);//e.printStackTrace(); 
		
		return "error/second";
	}
}
