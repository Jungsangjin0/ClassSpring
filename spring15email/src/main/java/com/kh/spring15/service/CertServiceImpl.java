package com.kh.spring15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;
import com.kh.spring15.repository.CertDao;

@Service
public class CertServiceImpl implements CertService{
	
	@Autowired
	private CertDao certDao;
	
	@Override
	public boolean check(Cert cert) {
		if(certDao.check(cert)) {//인증 성공
			certDao.remove(cert);
			return true;
		}
		return false;
	}

}
