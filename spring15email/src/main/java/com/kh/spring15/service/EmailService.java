package com.kh.spring15.service;

//이메일 서비스에 대한 요구사항
public interface EmailService {
	//인증번호 발송
	// - 준비물 : 보낼 사람의 이메일(받을 사람) 어디서 얻을 것인가?
	// - 아이디를 이용하여 이메일을 조회하여 얻어내거나
	// - 이메일이 아이디인 경우 <---- 이 경우라고 가정
	void sendCertification(String id);
		
}
