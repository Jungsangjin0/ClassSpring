package com.kh.spring08.service;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

	@Transactional //아래의 메소드를 하나의 트랜잭션으로 생각해주세요
	@Override
	public void save(Menu menu, MultipartFile file) throws IllegalStateException, IOException {
		int no = menuDao.add(menu);

		//일부러 오류발생
//		int number = 10 / 0;
		

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

	@Override
	public ResponseEntity<ByteArrayResource> download(int no) throws IOException {
		//no를 이용해서 MenuImage 정보를 불러온다
				MenuImage image = menuImageDao.find(no);
				
				//주의 : image 정보가 없다면 무슨뜻일까?
				// :: 등록되지 않았다는 의미
				// -500번을 사용자에게 보내기엔 이런 경우가 너무 자주 있다.
				// - 500번은 오류로 간주되기 때문에 사용자가 보기에 부담된다(자주 있는 일인데 오류 처리?)
				// - 못찾은거니까 404번으로 보내주는게 의미상 합리적이다.
				if(image == null) {
					//이미지가 없다면 
					return ResponseEntity.status(404).build();
					//not found
				}
				
				//2. image의 정보를 이용해서 실제 파일을 정보를 불러옴
//				File target = new File("E:\\spring\\upload\\menu", String.valueOf(image.getFile_no()));
//				byte[] data = FileUtils.readFileToByteArray(target); //common-io의 명령
				byte[] data = imageFileDao.load(image.getFile_no());
						
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