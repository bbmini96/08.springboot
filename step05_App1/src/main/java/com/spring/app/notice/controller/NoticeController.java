package com.spring.app.notice.controller;

import java.util.List;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.app.notice.entity.Notice;
import com.spring.app.notice.service.NoticeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class NoticeController {

	private final NoticeService noticeService;

	@PostMapping("/notice")
	public void insertNotice() {

		IntStream.rangeClosed(1, 300).forEach((i) -> {
			Notice notice = Notice.builder().userId("User id: " + i).userName("User Name: " + i).title("Title: " + i)
					.content("Content: " + i).hit(i).build();
			noticeService.insertNotice(notice);

		});

	}

//	@CrossOrigin(origins = "http://localhost:3000")		// 백엔드서버 주소값이 달라도 교차로 연결 시켜준다(백엔드와 프론트앤드 통신 연결)
	@GetMapping("/api/notice")
	public Page<Notice> getNoticeList(@RequestParam("page") int page) {
		return noticeService.getNoticeList(page);
	}
//	@GetMapping("/api/notice")
//	public List<Notice> getNoticeList() {
//		return noticeService.getNoticeList();
//	}
	
	@GetMapping("/api/notice/page")
	public Page<Notice> getNoticePageList(){
		return noticeService.getNoticePageList();
	}
	
	

}
