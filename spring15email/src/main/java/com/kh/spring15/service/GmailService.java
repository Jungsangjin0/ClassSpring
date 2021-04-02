package com.kh.spring15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;
import com.kh.spring15.repository.CertDao;

@Service
public class GmailService implements EmailService{

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private CertDao certDao;

	@Override
	public void sendCertification(String id) {
		//1. 아이디가 들어온 후 인증번호 객체를 생성
		String number = "112233";//랜덤
		Cert cert = Cert.builder().who(id).what("112233").build();
		
		//2. DB등록
		certDao.add(cert);
		
		//3. 이메일 발송
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("loky0112@gmail.com");
		message.setTo(id);
		message.setSubject("KH정보교원 인증번호 테스트");
		message.setText("인증번호 : " +cert.getWhat());
		
		sender.send(message);
		
	}
	
	
}
