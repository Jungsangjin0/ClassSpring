package com.kh.spring08.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;
import com.kh.spring08.entity.MenuImage;
import com.kh.spring08.repository.ImageFileDao;
import com.kh.spring08.repository.MenuDao;
import com.kh.spring08.repository.MenuImageDao;

@Controller
@RequestMapping("/menu") //공용매핑 - 컨트롤러간 충돌 방지
public class MenuController {
	
	
//	private final SqlSession sqlSession;
	//MenuDao 하위 계층에서 등록된 도구를 가져와라!
	private final MenuDao menuDao; 
	
	private final MenuImageDao menuImageDao;
	
	private final ImageFileDao imageFileDao;
	
	@Autowired
	public MenuController(MenuDao menuDao, MenuImageDao menuImageDao, ImageFileDao imageFileDao) {
		this.menuDao = menuDao;
		this.menuImageDao = menuImageDao;
		this.imageFileDao = imageFileDao;
	}
	//메뉴 등록 매핑
	// - GET : 입력 페이지 전송
	// - POST : 처리 후 다른 곳으로 리다이렉트
	
	@GetMapping("/add")
	public String add() {
		
		return "menu/add";
	}
	
	//파일 업로드가 이루어질 경우 컨트롤러에서는 ??? 형태로 수신한다.
	//MultipartFile 형태로 수신한다.
	// - getName() : 파라미터 명
	// - getOriginalFilename() : 파일명
	// - getContentType() : MIME-TYPE(검사/분류...)
	// - getSize() : 파일의 크기(byte)
	@PostMapping("/add")
	public String add(
//				@RequestParam String name,
//				@RequestParam String type,
//				@RequestParam int price
			@ModelAttribute Menu menu,
			@RequestParam MultipartFile im
			) throws IllegalStateException, IOException {
//		System.out.println(im.getName());
//		System.out.println(im.getOriginalFilename());
//		System.out.println(im.getContentType());
//		System.out.println(im.getSize());
		
		//등록
//		int no = sqlSession.selectOne("menu.seq");//번호생성
//		menu.setNo(no);
//		sqlSession.insert("menu.add", menu);
		int no = menuDao.add(menu);
		
		
		//no를 이용해서 파일 테이블에 정보 저장 및 실제 파일을 하드디스크에 저장
		// - table menu_image
		// - 저장위치 : path(E:/spring/upload/menu)
		
		
		if(!im.isEmpty()) { //파일이 존재하는 경우
			//테이블 저장코드
			//1. 번호 먼저 생성
//			int file_no = sqlSession.selectOne("menu_image.seq");
			//2. 데이터 저장
			MenuImage image = MenuImage.builder()
					.file_name(im.getOriginalFilename())
					.file_type(im.getContentType())
					.file_size(im.getSize())
					.menu_no(no)
					.build();
			int file_no = menuImageDao.add(image);
			
//			sqlSession.insert("menu_image.add", image);
			
//			//실제 저장 코드
//			//1. 저장될 파일의 객체를 생성(파일명은 시퀀스번호)
//			File target = new File("E:\\spring\\upload\\menu", String.valueOf(file_no));
//			//2. 저장
//			im.transferTo(target);
			imageFileDao.save(im, file_no);
		}
	return "redirect:add";
	}
}
