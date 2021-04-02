package com.kh.spring15;


import javax.activation.DataSource;
import javax.activation.FileDataSource;
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
public class Test05 {
	@Autowired
	private JavaMailSender sender;
	
	@Test
	public void test() throws MessagingException {
		//목표 : 첨부파일을 추가하는 것
		MimeMessage message = sender.createMimeMessage();
		
		//message 사용 true multipart여부 // 인코딩은 :UTF-8
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setFrom("loky0112@gmail.com");
		helper.setTo("loky13@daum.net");
		helper.setSubject("첨부파일 테스트");
		helper.setText("내용없음");
		
		//helper를 이용하여 첨부파일 추가
		// - javax.activation.DataSource : (파일 등..) 자원
		// - javax.sql.dataSource:  db연결자원
		DataSource source = new FileDataSource("D:/c.jpg");
//		helper.addAttachment("지어낸 이름", source);
		helper.addAttachment(source.getName(), source);//실제 파일 이름
		
		//전송
		sender.send(message);
	}
}
