package com.kh.spring15.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.kh.spring15.entity.Member;

@Repository
public class MemberDaoImpl implements MemberDao{

	
	private final SqlSession sqlSession;
	private final BCryptPasswordEncoder encoder;
	
	@Autowired
	public MemberDaoImpl(SqlSession sqlSession, BCryptPasswordEncoder encoder) {
		this.sqlSession = sqlSession;
		this.encoder = encoder;
	}


	@Override
	public void join(Member member) {
		//암호화
		String password = member.getPw();
		String result = encoder.encode(password);
		member.setPw(result);
		
		//등록
		sqlSession.insert("member.join", member);
	}


	@Override
	public Member login(Member member) {
		//member에는 id와 pw가 존재
		// -> 비교를 할 때 데이터베이스나 자바를 이용해서 직접 비교는 불가능
		// -> select * from where id = ? and pw = ? //사용 불가
		// encoder.matches()를 써야 하기 때문
		Member find = sqlSession.selectOne("member.login", member);
		if(encoder.matches(member.getPw(), find.getPw())) {
			//로그인 성공
			return find;
			
		} else { //로그인 실패
			return null;
		}
	}

}
