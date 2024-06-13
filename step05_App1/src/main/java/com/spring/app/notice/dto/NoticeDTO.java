package com.spring.app.notice.dto;
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
public class NoticeDTO {
	private Long no;
	private String userId;
	private String userName;
	private String title;
	private String content;
	private int hit;
	
	@Builder
	public NoticeDTO(Long no, String userId, String userName, String title, String content, int hit) {
		super();
		this.no = no;
		this.userId = userId;
		this.userName = userName;
		this.title = title;
		this.content = content;
		this.hit = hit;
	}
}
