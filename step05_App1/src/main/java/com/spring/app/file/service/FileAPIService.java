package com.spring.app.file.service;

import java.util.List;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.spring.app.common.dto.PageRequestDTO;
import com.spring.app.common.dto.PageResponseDTO;
import com.spring.app.file.dto.FileDTO;
import com.spring.app.file.dto.FileResponseDto;
import com.spring.app.file.entity.AttachmentFile;
import com.spring.app.file.repository.FileAPIRepository;
import com.spring.app.notice.entity.Notice;
import com.spring.app.notice.repository.NoticeQueryDSLRepository;
import com.spring.app.notice.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileAPIService {
	private final NoticeQueryDSLRepository noticeQueryDSLRepository;
	private final FileAPIRepository fileApiRepository;
	
	
	@Transactional
	public FileDTO getFileList(PageRequestDTO pageRequest) {
		
		Pageable pageable = PageRequest.of(pageRequest.getPage(), pageRequest.getSize());
		Page<Notice> result = noticeQueryDSLRepository.findByContentContaining(pageRequest.getKeyword(), pageable);
		
		Function<AttachmentFile, FileDTO> fn = (entity -> entityToDto(entity));

	}

	// Entity -> DTO
	public FileDTO entityToDto(AttachmentFile entity) {
		
		FileDTO dto = FileDTO.builder()
								.attachmentFileNo(entity.getAttachmentFileNo())
								.filePath(entity.getFilePath())
								.attachmentFileName(entity.getAttachmentFileName())
								.attachmentOriginalFileName(entity.getAttachmentOriginalFileName())
								.attachmentFileSize(entity.getAttachmentFileSize())
								.noticeNo(entity.getNoticeNo())
								.build();
		return dto;
	}

	public PageResponseDTO<FileResponseDto, AttachmentFile> getAllByNoticeNo(Long noticeno) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveFiles(List<MultipartFile> files, Long noticeno) {
		// TODO Auto-generated method stub
		
	}
	

	

}
