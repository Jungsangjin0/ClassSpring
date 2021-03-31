package com.kh.spring14.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

/*
 * JSP/Servlet에서 쓰던 필터
 *  - web.xml에 <filter> 를등록
 *  - @WebFilter를 사용
 * - Spring에서 관리가 안되기 때문에 @Autowired 등 스프링 기능 모두 사용 불가
 * 
 * [1] * 가 마지막에 배치되는 경우(특정 영역을 검사)
 * [2] * 가 첫번째에 배치되는 경우(확장자 검사)
 * [3] * 사용 안하는 경우(일일이 페이지 적용)
 * 스프링에서 관리가 안됨
 * */
@Slf4j
//@WebFilter(urlPatterns = "/member")
//servlet 4.0.1 version 부터는 iniy, destory method overide 필수아님
//3.1.0은 필수
public class ClassicLoginFilter implements Filter{

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//Q sqlsession이 Autowired가 될까?
		log.info("sqlSession = {}", sqlSession);
		
		//검사 : session에 user가 있는지 없는지 검사
		//- ServletRequest는 HttpServletRequest의 상위 형태
		//- filter는 Http에만 적용할 수 있는게 아니기 때문
		//사용하기 위해 다운캐스팅 하여 사용
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		
		String user = (String) session.getAttribute("user");
		if(user != null) {
			//로그인 상태 : 통과
			log.info("통과!");
			chain.doFilter(request, response);
		}
		else {//로그아웃 상태 : 거절(redirect, error)
			log.info("거절!");
			
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.sendRedirect(req.getContextPath());//루트(/)
		}
	}

	


}
