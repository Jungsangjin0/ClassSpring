package com.kh.spring15;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
public class Test04 {

	@Autowired
	private JavaMailSender sender;
	
	//목표 : 미리 만들어둔 템플릿(template)을 불러와서 전송
	// - 비밀번호 변경 링크
	// - 회원가입 환영 메세지
	// - etc
	@Test
	public void test() throws MessagingException, IOException {
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
		
		//도구를 만들어 템플릿 파일을 불러온다.
		//classpathresource를 불러오겠다.
		ClassPathResource resource = new ClassPathResource("email/template.html");
		//리스트를만들었으니 파일의 스트림을 만들어 불러오기
		File file = resource.getFile(); //java File
		
		//자바 1.8부는 try~with 구문으로 자동 claose가 가능
		
//		try(도구 생성코드(스트림생성코드)) {
//			
//		}
		StringBuffer buffer = new StringBuffer(); //문자열 합성기
		try(BufferedReader reader = new BufferedReader(new FileReader(file));
				){
//			BufferedReader reader = new BufferedReader(new FileReader(file));//문자를 읽어올 때
			//bufferedReader는 한줄 씩 읽음
			
			//reader에서 읽은 한 줄을 buffer에 계속 더한다
			while(true) {
				String line = reader.readLine();//1중 읽고
				if(line == null) break; //읽은게 없으면 탈출!
				buffer.append(line); //buffer에 line을 더해라
			}
//			reader.close();
		}
		
		helper.setText(buffer.toString(), true);//html모드 설정
		//전송
		sender.send(message);
				
	}
}
