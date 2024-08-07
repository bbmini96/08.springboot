package com.spring.app.common.dto;

import lombok.Data;

@Data
public class PageRequestDTO {
	// 현재 요청 페이지 번호
	private int page;
	
	// 페이지당 출력 데이터 개수
	private int size;
	
	// 검색 쿼리
	private String keyword;
	
	// 기본값 처리
	public PageRequestDTO() {
		this(0,10);
	};
	
	public PageRequestDTO(int page, int size) {
		this.page = page;
		this.size = size;
	};
	
	public PageRequestDTO(int page, int size, String keyword) {
		this.page = page;
		this.size = size;
		this.keyword = keyword;
	};
	
}
