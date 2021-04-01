package com.kh.spring15;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


//목표 : 독립 테스트로 이메일 발송
public class Test01 {

	private JavaMailSenderImpl sender;
	
	@Before
	public void before() {
		//sender 생성
		sender = new JavaMailSenderImpl();
		
		//이메일 서버 정보 설정
		sender.setHost("smtp.gmail.com");//이메일 서버 호스트
		sender.setPort(587);//이메일 서버 포트
		
		//이메일 계정 인증 정보
		//id pw
		sender.setUsername("loky0112@gmail.com");//사용자 이메일 주소
		sender.setPassword("123456789");// 사용자 비밀번호
		
		//옵션 설정
		Properties prop = new Properties();
		//구글에서 원하는 key 값
		prop.setProperty("mail.smtp.auth", "true");//인증설정
		prop.setProperty("mail.smtp.starttls.enable", "true");//tls 사용 설정
		prop.setProperty("mail.smtp.debug", "true");//디버그 설정(옵션)
		//암호화설정
		//공개키 암호화를 통해서 좀더 안전하게 데이터를 보내는 방식
		sender.setJavaMailProperties(prop);
	}
	
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
	
	//after 스트림 닫기
}
