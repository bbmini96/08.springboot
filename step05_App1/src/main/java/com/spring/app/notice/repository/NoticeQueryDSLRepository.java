package com.spring.app.notice.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.app.notice.entity.Notice;
import com.spring.app.notice.entity.QNotice;

import jakarta.transaction.Transactional;

@Repository
public class NoticeQueryDSLRepository extends QuerydslRepositorySupport {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	private QNotice qNotice;
	
	public NoticeQueryDSLRepository() {
		super(Notice.class);
		this.qNotice = QNotice.notice;
	}
	
	@Transactional
	public Page<Notice> findByContentContaining(String keyword, Pageable pageable) {
		List<Notice> noticeList = jpaQueryFactory.selectFrom(qNotice)
													.where(toConatainsExpression(keyword))
													.offset(pageable.getOffset())
													.limit(pageable.getPageSize())
													.fetch();
		
		JPAQuery<Long> total = jpaQueryFactory.select(qNotice.count())
												.from(qNotice)
												.where(toConatainsExpression(keyword));
		
		return PageableExecutionUtils.getPage(noticeList, pageable, total::fetchOne);
	}
	
	private BooleanExpression toConatainsExpression(String keyword) {
		if(!StringUtils.hasText(keyword)) {
			return null;
		}
		return qNotice.content.contains(keyword);
	}
	
}


