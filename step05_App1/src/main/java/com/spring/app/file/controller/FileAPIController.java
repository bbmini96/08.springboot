package com.spring.app.file.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.spring.app.common.dto.PageResponseDTO;
import com.spring.app.file.dto.FileResponseDto;
import com.spring.app.file.entity.AttachmentFile;
import com.spring.app.file.service.FileAPIService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class FileAPIController {
	
    private final FileAPIService fileService;
	String fileName = "";
	
	// file업로드와 맵핑
	@PostMapping("/notice/{noticeno}/files")
	public ResponseEntity uploadFile(@PathVariable Long noticeno, List<MultipartFile> files) { // 여러개 파일 가져올때
			try {
				fileService.saveFiles(files, noticeno);
			} catch (IllegalStateException e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
			}
			return ResponseEntity.ok().build();
	}

	@GetMapping("/notice/{noticeno}/files")
    public PageResponseDTO<FileResponseDto, AttachmentFile> getAllByNoticeNo(@PathVariable Long noticeno) {
        return fileService.getAllByNoticeNo(noticeno);
    }
	
	@GetMapping("/api/notice/{noticeno}/files/{fileno}")
	public ResponseEntity<Resource> downloadFile(@PathVariable Long noticeno, @PathVariable Long fileno){
		// logic
		/* 1) 물리적인 파일 선택
		 * 2) 리소스화(inputStream)
		 * 3) return(header) 
		 */
		// 1,2)
		System.out.println(fileName);
		Resource resource = null;
		Path path = Paths.get("C:\\t\\" + fileName);
		
		try {
			resource = new InputStreamResource(Files.newInputStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// ***headers
		// Content-Type: MediaType.APPLICATION_OCTET_STEAM
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		// Content-Disposition: 전송되는 리소스의 처리 방법 지정(다운로드 파일 설정)
		headers.setContentDisposition(ContentDisposition
										.builder("attachment")
										.filename("file-test1.txt")		// 다운로드 받을때 이름
										.build());
		
		
		// 3)
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	

}
