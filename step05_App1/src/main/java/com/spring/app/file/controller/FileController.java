package com.spring.app.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
	
	// properties에서 파일 경로 지정해서 사용하기
	@Value("${file.save.path}")
	String savePath;	
	
	@GetMapping("/file-test")	// fileTest.jsp로 이동
	public String moveFileTestView() {
		return "fileTest";
	}
	
	// file업로드와 맵핑
	@PostMapping("/file-upload")
//	public void uploadFile(MultipartFile files) {	// HttpServletRequest를 springboot에서 자동 맵핑 => jsp의 name값과 변수명이 같게 해야한다
	
	public void uploadFile(List<MultipartFile> files) {	// 여러개 파일 가져올때		
		// request
		for(MultipartFile file:files) {
//			String savePath = "C:\\test";		// 경로지정
			String fileName = UUID.randomUUID().toString()+"_" + file.getOriginalFilename();	// 난수를 만들어서 저장
			
			
			try {
				if(!new File(savePath).exists()) {
					new File(savePath).mkdir();		// 폴더가 없으면 폴더를 만들어라
					}
				file.transferTo(new File(savePath+ "\\" + fileName));	// 해당 위치에 파일을 저장하겠다
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}	
			
		}

	}
	
}
