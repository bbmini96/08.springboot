package com.spring.app.notice.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.app.notice.entity.Notice;


@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long>{
	
	public Page<Notice> findByContentContaining(String content, Pageable pageable);
	
	@Query(value = "SELECT * FROM notice LIMIT :offset, :cnt", nativeQuery = true)
	List<Notice> findBy(int offset, int cnt);
	
}
