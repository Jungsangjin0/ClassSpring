package com.kh.spring15.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.spring15.entity.Cert;
import com.kh.spring15.repository.CertDao;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CertServiceImpl implements CertService{
	
	@Autowired
	private CertDao certDao;
	
	@Autowired
	private RandomService randomService;
	@Override
	public boolean check(Cert cert) {
		if(certDao.check(cert)) {//인증 성공
			certDao.remove(cert);
			return true;
		}
		return false;
	}

	//인증번호 생성
	@Override
	public String create(String who) {
		//1. 아이디가 들어온 후 인증번호 객체를 생성
//		String number = "112233";//랜덤
		String number = randomService.generateNumber();
		Cert cert = Cert.builder().who(who).what("112233").build();
		
		//2. DB등록
		certDao.add(cert);
		return number;
	}

//	@Scheduled(fixedRate = 1000)
	
//	cron 표현식 : 총 7자리로 구성되어 있다.
// - 초, 분, 시, 일, 월, 요일, (연도)
	//* * * * * *  매초 매분 매시 매일 매월 매요일마다 ::1초마다
	//* * * * * * ::2번에 한번 :: 2초
//	@Scheduled(cron="* * * * * *")	
//	@Scheduled(cron="*/2 * * * * *")//2초마다	
//	@Scheduled(cron="10-20 * * * * *")//10초부터 20초 사이만
//	@Scheduled(cron="10,20 * * * * *")//매 10초, 20초
	@Scheduled(cron="0 0 * * * *")//매 정각마다 실행
//	@Scheduled(cron="0 0 1 * * *")//매일 1시마다 실행
//	@Scheduled(cron="0 0,50 9-12 * * MON-FRI")//점심먹기전까지 사진찍는 시간마다 실행(월- 금 매시 50분, 정각)
//	@Scheduled(cron="0 0,50 9-12 * * 1-5")//점심먹기전까지 사진찍는 시간마다 실행(월- 금 매시 50분, 정각)
//	@Scheduled(cron="0 20,30 13-15 * * *")//점심먹은 후 사진찍는 시간마다 실행
	
	@Override
	public void clear() {
		certDao.clear();
	}

}
