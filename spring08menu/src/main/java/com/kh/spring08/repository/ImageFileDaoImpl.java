package com.kh.spring08.repository;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
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

	@Override
	public byte[] load(int file_no) throws IOException {
		
		File target = new File(path, String.valueOf(file_no));
		byte[] data = FileUtils.readFileToByteArray(target); //common-io의 명령
		
		return data;
	}

}
