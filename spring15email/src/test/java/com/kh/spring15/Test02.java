package com.kh.spring15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml"
})
@Slf4j
public class Test02 {

	@Autowired
	private JavaMailSender sender;
	
	@Test
	public void test() {
		//메일 전송
		//1.간단한 단문 메세지 : SimpleMailMessage
		SimpleMailMessage message = new SimpleMailMessage();
		
		//제목
		message.setSubject("이번주 로또는 대박입니다.");
		
//		//발신인
//		message.setFrom("~~");
		
		//수신인
		String to = "loky13@daum.net";
		String to2 = "loky0807@gmail.com";
		message.setTo(to,to2);
		
		//참조(cc, carbon copy) ::먹지
		String[] cc = {"loky0807@gmail.com"};
		message.setCc(cc);
		
		//숨은참조(bcc, blind carbon copy)
		String[] bcc = {"zztkdwls@naver.com"};
		message.setBcc(bcc);
		
		//내용 
		String text = "구독좋아요~";
		message.setText(text);
		//전송
		sender.send(message);
	}
}
