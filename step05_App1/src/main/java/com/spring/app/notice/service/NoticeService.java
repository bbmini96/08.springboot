package com.spring.app.notice.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.notice.entity.Notice;
import com.spring.app.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	private final NoticeRepository noticeRepository;
	
	@Transactional
	public void insertNotice(Notice notice) {
		noticeRepository.save(notice);
	}

	@Transactional
	public Page<Notice> getNoticeList(int page) {
		return noticeRepository.findAll(PageRequest.of(page, 10));
	}
//	@Transactional
//	public List<Notice> getNoticeList() {
//		return noticeRepository.findAll();
//	}
	@Transactional
	public Page<Notice> getNoticePageList() {
		return noticeRepository.findAll(PageRequest.of(0, 10, Sort.by("no").descending().and(Sort.by("content").ascending())));
	}

}
