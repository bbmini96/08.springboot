package com.spring.app.notice.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.app.common.dto.PageRequestDTO;
import com.spring.app.common.dto.PageResponseDTO;
import com.spring.app.notice.dto.NoticeDTO;
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
	public List<Notice> getNoticeList() {
		return noticeRepository.findAll();
	}

	@Transactional
	public PageResponseDTO<NoticeDTO, Notice> getNoticeList(PageRequestDTO pageRequest) {
		Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
		Page<Notice> result = noticeRepository.findAll(pageable);
		
		Function<Notice, NoticeDTO> fn = (entity -> entityToDto(entity));
		
		return new PageResponseDTO<>(result, fn);
	}
	
	@Transactional
	public Page<Notice> getNoticePageList() {
		return noticeRepository.findAll(PageRequest.of(0, 10, Sort.by("no").descending().and(Sort.by("content").ascending())));
	}
	
	// Emtity -> DTO
	public NoticeDTO entityToDto(Notice entity) {
		NoticeDTO dto = NoticeDTO.builder()
								.no(entity.getNo())
								.userId(entity.getUserId())
								.userName(entity.getUserName())
								.title(entity.getTitle())
								.content(entity.getContent())
								.hit(entity.getHit())
								.build();
		return dto;
	}
	
	// DTO -> Entity
	public Notice entityToDto(NoticeDTO dto) {
		
		Notice entity = Notice.builder()
								.userId(dto.getUserId())
								.userName(dto.getUserName())
								.title(dto.getTitle())
								.content(dto.getContent())
								.build();
		
		return entity;
	}

	
	
}
