package com.kh.spring15.service;

import com.kh.spring15.entity.Cert;

public interface CertService {
	//인증번호 생성 및 등록 기능
	String create(String who);
	
	//인증번호 검사 기능
//	boolean check(이메일, 번호);
	boolean check(Cert cert);
	
	//(스케쥴러) 인증번호 주기적으로 청소하는 기능
	void clear();
}
