package com.kh.spring08.repository;

import com.kh.spring08.entity.MenuImage;

public interface MenuImageDao {
	//등록 기능
	int add(MenuImage menuImage);
	MenuImage find(int menu_no);
}
