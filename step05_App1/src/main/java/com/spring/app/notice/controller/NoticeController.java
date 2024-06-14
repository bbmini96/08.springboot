package com.spring.app.notice.controller;

import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.common.dto.PageRequestDTO;
import com.spring.app.common.dto.PageResponseDTO;
import com.spring.app.notice.dto.NoticeDTO;
import com.spring.app.notice.entity.Notice;
import com.spring.app.notice.service.NoticeService;

import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class NoticeController {
	
	private final NoticeService noticeService;
	
	@GetMapping("/api/query-notice")
	public PageResponseDTO<NoticeDTO,Notice> getNoticeByContent(PageRequestDTO pageRequest){
		System.out.println(pageRequest);
		return noticeService.getNoticeByContent(pageRequest);
	}
	
	
//	@CrossOrigin(origins = "http://localhost:3004")
	@PostMapping("/notice")
	public void insertNotice() {
		
		IntStream.rangeClosed(1, 300)
					.forEach((i) -> {
						
						Notice notice = Notice.builder()
											.userId("User ID : " + i)
											.userName("User Name : " + i)
											.title("Title : " + i)
											.content("Content : " + i)
											.hit(i)
											.build();
						
						noticeService.insertNotice(notice);
					});
	}
	
//	@CrossOrigin(origins = "http://localhost:3004")
	@GetMapping("/api/notice")
	public PageResponseDTO<NoticeDTO, Notice> getNoticeList(PageRequestDTO pageRequest) {
		return noticeService.getNoticeList(pageRequest);
	}
	
	@GetMapping("/api/notice/page")
	public Page<Notice> getNoticePageList() {
		return noticeService.getNoticePageList();
	}
	
}
