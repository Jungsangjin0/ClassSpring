package com.kh.spring08.repository;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class ImageFileDaoImpl implements ImageFileDao{

	private final String path = "E:\\spring\\upload\\menu";
	//E:\
	
	@Override
	public void save(MultipartFile file, int file_no) throws IllegalStateException, IOException {
		File tartget = new File(path, String.valueOf(file_no));
		file.transferTo(tartget);
		
	}

}
