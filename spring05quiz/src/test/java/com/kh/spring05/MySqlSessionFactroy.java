package com.kh.spring05;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MySqlSessionFactroy {

	public static SqlSessionFactory factory() throws IOException {
		
		InputStream in = Resources.getResourceAsStream("mybatis/please.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
		
		return factory;
	}
}
