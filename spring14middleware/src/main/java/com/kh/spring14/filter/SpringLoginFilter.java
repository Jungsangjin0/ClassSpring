package com.kh.spring14.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/*
 * Spring에서 제공하는 필터 사용법
 * - Filter는 Servlet/JSP 규약 상 무조건 web.xml에 등록하도록 되어 있다.
 * - Spring에 등록해봤자 필터로서의 효과가 발휘되지 않는다.
 * 
 *  - 상속은 동일하게 받지만 @WebFilter가 아니라 Spring bean으로 등록해야 한다.
 * 
 * */
@Component //도구
@Slf4j
public class SpringLoginFilter implements Filter{

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
