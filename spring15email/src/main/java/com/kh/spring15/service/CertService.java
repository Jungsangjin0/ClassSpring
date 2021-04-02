package com.kh.spring15.service;

import com.kh.spring15.entity.Cert;

public interface CertService {
	//인증번호 검사 기능
//	boolean check(이메일, 번호);
	boolean check(Cert cert);
}
