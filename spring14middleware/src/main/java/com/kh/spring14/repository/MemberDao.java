package com.kh.spring14.repository;

import com.kh.spring14.entity.Member;

public interface MemberDao {

	//로그인
	Member login(Member member);
}
