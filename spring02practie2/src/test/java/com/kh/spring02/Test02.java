package com.kh.spring02;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/*
 * 연동 테스트
 *  - 연동을 하려면 정보가 추가로 필요하다.
 *  - 어떤 환경에서 테스트 할 것인지(spring인지 어떤 버전인지)
 *  : @RunWith(대상)
 *  - 연동시킬 설정파일의 위치(root or serlvet?)
 *  : @ContextConfiguration(locations = 위치)
 *  프로젝트를 기준으로 위치를 정한다.
 * */

@RunWith(SpringJUnit4ClassRunner.class) //스프링이랑 JUnit랑 연동시켜 실행하는 것
@ContextConfiguration(
		locations = "file:src/main/webapp/WEB-INF/spring/root-context.xml"
		)
public class Test02 {
	/*
	 * 환경이 연동되어 있다면 필요한 도구들을 자동으로 연동시켜 사용할 수 있다.
	 * @Autowried
	 * */
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	@Test
	public void test() {
		System.out.println(jdbcTemplate);
		
		//insert 수행
		String sql = "insert into student values(?, ?)";
		Object[] param = {"홍길동", 100};
		jdbcTemplate.update(sql, param);
	}
	
	
}