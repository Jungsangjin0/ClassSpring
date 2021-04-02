package com.kh.spring15.repository;

import com.kh.spring15.entity.Member;

public interface MemberDao {

	//회원가입
	void join(Member member);
	//로그인
	Member login(Member member);
}
