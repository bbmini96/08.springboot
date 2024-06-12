package com.spring.app.notice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
public class Notice {
	
	// 번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	// 자동 no 생성
	private long no;
	
	// 작성자id
	@Column(name = "user_id")
	private String userId;


	// 작성자명
	@Column(name = "user_name")
	private String userName;


	// 제목
	@Column(name = "title")
	private String title;


	// 내용
	@Column(name = "content")
	private String content;


	// 조회수
	@Column(name = "hit")
	private int hit;
	
	@Builder
	public Notice(long no, String userId, String userName, String title, String content, int hit) {
		super();
		this.no = no;
		this.userId = userId;
		this.userName = userName;
		this.title = title;
		this.content = content;
		this.hit = hit;
	}
}

