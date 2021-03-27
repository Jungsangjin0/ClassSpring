package com.kh.spring08.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.spring08.entity.MenuImage;

@Repository
public class MenuImageImpl implements MenuImageDao{

	private final SqlSession sqlSession;
	
	@Autowired
	public MenuImageImpl(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public int add(MenuImage menuImage) {
		int file_no = sqlSession.selectOne("menu_image.seq");
		menuImage.setFile_no(file_no);
		sqlSession.insert("menu_image.add", menuImage);
		return file_no;
	}

	@Override
	public MenuImage find(int menu_no) {
		
		return sqlSession.selectOne("menu_image.find", menu_no);
	}

}
