package com.kh.spring08.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;
import com.kh.spring08.entity.MenuImage;
import com.kh.spring08.repository.ImageFileDao;
import com.kh.spring08.repository.MenuDao;
import com.kh.spring08.repository.MenuImageDao;

@Service
public class MenuServiceImpl implements MenuService{

	private final MenuDao menuDao;

	private final MenuImageDao menuImageDao;

	private final ImageFileDao imageFileDao;

	@Autowired
	public MenuServiceImpl(MenuDao menuDao, MenuImageDao menuImageDao, ImageFileDao imageFileDao) {
		this.menuDao = menuDao;
		this.menuImageDao = menuImageDao;
		this.imageFileDao = imageFileDao;
	}

	@Override
	public void save(Menu menu, MultipartFile file) throws IllegalStateException, IOException {
		int no = menuDao.add(menu);


		//no를 이용해서 파일 테이블에 정보 저장 및 실제 파일을 하드디스크에 저장
		// - table menu_image
		// - 저장위치 : path(E:/spring/upload/menu)


		if(!file.isEmpty()) { //파일이 존재하는 경우
			//테이블 저장코드
			//1. 번호 먼저 생성
			//			int file_no = sqlSession.selectOne("menu_image.seq");
			//2. 데이터 저장
			MenuImage image = MenuImage.builder()
					.file_name(file.getOriginalFilename())
					.file_type(file.getContentType())
					.file_size(file.getSize())
					.menu_no(no)
					.build();
			int file_no = menuImageDao.add(image);

			//			sqlSession.insert("menu_image.add", image);

			//			//실제 저장 코드
			//			//1. 저장될 파일의 객체를 생성(파일명은 시퀀스번호)
			//			File target = new File("E:\\spring\\upload\\menu", String.valueOf(file_no));
			//			//2. 저장
			//			im.transferTo(target);
			imageFileDao.save(file, file_no);
		}

	}
}