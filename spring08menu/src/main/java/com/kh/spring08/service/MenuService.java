package com.kh.spring08.service;

import java.io.IOException;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.kh.spring08.entity.Menu;

public interface MenuService {
	//메뉴 등록 기능
	void save(Menu menu, MultipartFile file) throws IllegalStateException, IOException;
	
	//다운로드 처리 기능
	ResponseEntity<ByteArrayResource> download(int no) throws IOException;
}
