package com.kh.spring08.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;
import com.kh.spring08.entity.MenuImage;
import com.kh.spring08.service.MenuService;

@Controller
@RequestMapping("/menu") //공용매핑 - 컨트롤러간 충돌 방지
public class MenuController {
	
	
//	private final SqlSession sqlSession;
	//MenuDao 하위 계층에서 등록된 도구를 가져와라!
//	private final MenuDao menuDao; 
//	
//	private final MenuImageDao menuImageDao;
//	
//	private final ImageFileDao imageFileDao;
	private final MenuService MenuService;
	@Autowired
	public MenuController(MenuService menuService) {
		this.MenuService = menuService;
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
//		int no = menuDao.add(menu);
//		
//		
//		//no를 이용해서 파일 테이블에 정보 저장 및 실제 파일을 하드디스크에 저장
//		// - table menu_image
//		// - 저장위치 : path(E:/spring/upload/menu)
//		
//		
//		if(!im.isEmpty()) { //파일이 존재하는 경우
//			//테이블 저장코드
//			//1. 번호 먼저 생성
////			int file_no = sqlSession.selectOne("menu_image.seq");
//			//2. 데이터 저장
//			MenuImage image = MenuImage.builder()
//					.file_name(im.getOriginalFilename())
//					.file_type(im.getContentType())
//					.file_size(im.getSize())
//					.menu_no(no)
//					.build();
//			int file_no = menuImageDao.add(image);
//			
////			sqlSession.insert("menu_image.add", image);
//			
////			//실제 저장 코드
////			//1. 저장될 파일의 객체를 생성(파일명은 시퀀스번호)
////			File target = new File("E:\\spring\\upload\\menu", String.valueOf(file_no));
////			//2. 저장
////			im.transferTo(target);
//			imageFileDao.save(im, file_no);
		MenuService.save(menu, im);
	return "redirect:add";
	}
	
	
	//메뉴 목록 매핑
	@GetMapping("/list")
	public String list(Model model) {
		//목록 구하기
		List<Menu> list = sqlSession.selectList("menu.list");
		model.addAttribute("list", list);
		return "menu/list";
	}
	
	@Autowired
	private SqlSession sqlSession;
	//이미지 다운로드
	//- 메뉴 번호를 파라미터로 받아서 해당하는 메뉴의 이미지 파일을 전송
	// - 스프링이 좋아하지 않는 방식(DispatcherServlet과 무관하게 전송됨)
	@GetMapping("/download")
	public void download(
			@RequestParam int no,
			HttpServletResponse response
			) throws IOException {
		
		System.out.println("no : " + no);
		//no를 이용해서 MenuImage 정보를 불러온다
		MenuImage image = sqlSession.selectOne("menu_image.find", no);
		
		//2. image의 정보를 이용해서 실제 파일을 정보를 불러옴
		File target = new File("E:\\spring\\upload\\menu", String.valueOf(image.getFile_no()));
		byte[] data = FileUtils.readFileToByteArray(target); //common-io의 명령
		
//		byte[] buffer = new byte[(int)target.length()];
//		FileInputStream in = new FileInputStream(target);
//		in.read(buffer);
		
//		byte[] buffer = new byte[1024]; //1kb
//		FileInputStream in = new FileInputStream(target);
//		while(true) {
//			int size = in.read(buffer);
//			if(size == -1)break;
//			response.getOutputStream().write(buffer, 0, size);;
//		}
		
		//3. 사용자에게 보낼 정보 추가(header)
		response.setHeader("Content-Length", String.valueOf(data.length));//다운로드 때 얼마나 남았는지 나옴 
		response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");//다운로드 설정 
												//글꼴 UTF-8
		response.setHeader("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(image.getFile_name(), "UTF-8")+"\"");//사용자에게 파일 이름이 뜸
		response.setHeader("파일설명(옵션)", "헤더값");
		
		//4. 사용자에게 파일을 전송(body)
		response.getOutputStream().write(data);
		
	}
	
	//- 스프링에서 권장하는 다운로드 처리방식
	//- 데이터 뿐 아니라 상태 설정도 하고 싶을 경우
	//- ResponseEntity(응답개체) 라는 데이터 타입으로 결과를 반환
	// - ResponseEntity 내부에 반환할 데이터의 형태를 정의
	// - Spring은 원시형태를 직접 사용하는 것을 싫어함
	// - 제공해주는 도구 중에서 byte[]을 담을 수 있는 형태를 반환
	//ByteArrayResource
	
	@GetMapping("/download2")
	public ResponseEntity<ByteArrayResource> download2(@RequestParam int no) throws IOException {
		
		//no를 이용해서 MenuImage 정보를 불러온다
		MenuImage image = sqlSession.selectOne("menu_image.find", no);
		
		//2. image의 정보를 이용해서 실제 파일을 정보를 불러옴
		File target = new File("E:\\spring\\upload\\menu", String.valueOf(image.getFile_no()));
		byte[] data = FileUtils.readFileToByteArray(target); //common-io의 명령
				
		//3. Wrapping
		ByteArrayResource resource = new ByteArrayResource(data);
		
		//4. 응답 개체 생성
		ResponseEntity<ByteArrayResource> entity =
				ResponseEntity.ok()
								.header("Content-Length", String.valueOf(data.length))
								.header("Content-Type", "application/octet-stream; charset=UTF-8")
								.header("Content-Disposition", "attachment; filename=\""+URLEncoder.encode(image.getFile_name(), "UTF-8")+"\"")
							.body(resource);
				
		return entity;
	}
	
}
