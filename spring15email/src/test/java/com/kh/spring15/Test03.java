package com.kh.spring15;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Slf4j
public class Test03 {

	@Autowired
	private JavaMailSender sender;
	
	//목표 : 복잡한 메세지(MimeMessage)를 만들어 전송 ::첨부파일 html
	@Test
	public void test() throws MessagingException {
//		SimpleMailMessage message = new SimpleMailMessage(); 글자만 보낼 수 있음
		
		//메세지 생성
		MimeMessage message= sender.createMimeMessage();
		
		//헬퍼 설정
		//mimemessage 설정에 대한 설정 도우미
		MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
		
		//메세지 내용 설정
		helper.setFrom("loky0112@gmail.com");
		helper.setSubject("누르면 복이와요 제목");
		String[] to = {"loky13@daum.net", "zztkdwls@naver.com"};
		helper.setTo(to);
		//참조(cc), 숨은참조(bcc)설정
		helper.setText("<h1>뻥이요요<h1>", true);
		
		
		//전송
		sender.send(message);
				
	}
}
