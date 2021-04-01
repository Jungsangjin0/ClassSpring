package com.kh.spring14.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring14.entity.Member;

import lombok.extern.slf4j.Slf4j;

/*
 * 인터셉터(interceptor)
 * - 요청 중간중간을 가로채서 간섭하거나 감시하는 도구
 * - 총 세 가지 시점에 대해 간섭이 가능
 * 		1. 컨트롤러 실행 전(prehandle)
 * 			-request : 사용자 요청 정보
 * 			-response : 서버 응답 정보
 * 			-handler : 실행될 대상 정보(일반적으로 컨트롤러)
 * 		2. 컨트롤러 실행 후(postHandle)
 *			 -request : 사용자 요청 정보
 * 			-response : 서버 응답 정보
 * 			-handler : 실행될 대상 정보(일반적으로 컨트롤러)
 * 			-modelAndView : view페이지 정보와 전달되는 model 정보
 * 		3. 뷰 렌더링 후(afterCompletion)
 * 			 -request : 사용자 요청 정보
 * 			-response : 서버 응답 정보
 * 			-handler : 실행될 대상 정보(일반적으로 컨트롤러)
 * 			-ex : 실행 중에 DispatcherServlet에서 발생하는 예외 정보가 담긴다. 예외 중 처리되지 않은 예외 정보가 담긴다.
 * - DispatcherServlet을 간섭하는 것이므로 servlet-context.xml에 등록
 * - 인터셉터 설정도 servlet-context.xml에 수행
 * */
@Slf4j
//public class SpringLoginInterceptor extends HandlerInterceptorAdapter
public class SpringLoginInterceptor implements HandlerInterceptor{

	//interceptor는 srping bean 이므로 자동으로 스프링의 모든 기능 사용이 가능하다.
	
	long start;
	long finish;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//시작시간 측정
		start = System.currentTimeMillis();
		
		log.info("preHandle 실행!");
		log.info("handler = {}, {}", handler, handler.getClass());
		//여기서 통과/거절은 어떻게 시키는가? ::boolean return true or false
		// - 통과 : return true
		// - 거절 : return false(리 다이렉트 or 에러 송출 등의 추가작업이 필요)
		//response.sendError(404);
		//response.sendRedirect(request.getContextPath());
		log.info("무엇일까 이건 = {}",  HandlerInterceptor.super.preHandle(request, response, handler));
		Member user = (Member)request.getSession().getAttribute("user");
		if(user != null) {
			return true;
		}else {
			response.sendRedirect(request.getContextPath());
		}
		return HandlerInterceptor.super.preHandle(request, response, handler); //이 값은 return 이 true네요 불러낸다면 무조건 true
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("postHandle 실행!");
		log.info("handler = {}", handler);
		log.info("mdelAndView = {}", modelAndView);
//		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("afterCompletion 실행!");
		log.info("handler = {}", handler);
		log.info("ex = {}", ex); //예외
		
		//종료 시각 측정
		finish = System.currentTimeMillis();
		
		log.info("소요 시간 = {}ms", finish - start);
//		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
		//로그인 관리자 내글접근가능 암호화된 글
	}


}
