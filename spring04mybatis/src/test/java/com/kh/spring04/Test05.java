package com.kh.spring04;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.javassist.tools.rmi.StubGenerator;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.kh.spring04.entity.Student;

public class Test05 {

	@Test
	public void test() throws IOException {
		//설정파일 로딩
		InputStream in = Resources.getResourceAsStream("mybatis/please.xml");
		//mybatis 준비
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		//명령 실행도구 준비(true : 자동커밋, false : 수동커밋)
		SqlSession sqlSession = factory.openSession(true);
					
		//수정
		Student student = new Student();
		student.setName("테스트");
		student.setScore(60);
		
		sqlSession.update("student.edit", student);
	}
}
