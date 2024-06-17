package com.spring.app.file.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.app.file.entity.AttachmentFile;

@Repository
public interface FileAPIRepository extends JpaRepository<AttachmentFile, Long>{
	
	public Page<AttachmentFile> findAllByNoticeNo(Long noticeno, Pageable pageable);
	public AttachmentFile findByNoticeNoAndAttachmentFileNo(Long noticeno, Long attachmentFileNo);

}
