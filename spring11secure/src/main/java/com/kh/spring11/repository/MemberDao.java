package com.kh.spring11.repository;

import com.kh.spring11.entity.Member;

public interface MemberDao {

	//회원가입
	void join(Member member);
	//로그인
	Member login(Member member);
}
