package com.spring.app.file.dto;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class FileDTO {

	private Long attachmentFileNo;
	private String filePath;
	private String attachmentFileName;
	private String attachmentOriginalFileName;
	private Long attachmentFileSize;
	private Long noticeNo;
	
	@Builder
	public FileDTO(Long attachmentFileNo, String filePath, String attachmentFileName, String attachmentOriginalFileName,
			Long attachmentFileSize, Long noticeNo) {
		super();
		this.attachmentFileNo = attachmentFileNo;
		this.filePath = filePath;
		this.attachmentFileName = attachmentFileName;
		this.attachmentOriginalFileName = attachmentOriginalFileName;
		this.attachmentFileSize = attachmentFileSize;
		this.noticeNo = noticeNo;
	}
	
	
}
