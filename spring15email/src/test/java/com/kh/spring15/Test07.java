package com.kh.spring15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kh.spring15.service.EmailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
public class Test07 {

	//목표 : GmailSerive의 sendCertification() 테스트
	@Autowired
	private EmailService emailService;
	
	@Test
	public void test() {
		emailService.sendCertification("loky13@daum.net");
	}
	
	
}
