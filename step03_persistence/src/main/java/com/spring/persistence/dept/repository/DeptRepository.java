package com.spring.persistence.dept.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.persistence.dept.entity.Dept;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Integer> {

	// Query Method
	/*
	 *	- 자동으로 쿼리 생성, 실행결과 반환
	 *	- 컴파일시 오류 확인
	 *	- 정해진 규칙에서만 사용해야 한다
	 *	- 쿼리 생성 및 실행 속도 느림
	 *	- 복잡한 조건의 쿼리 생성하는데 한계가 있다
	 */
	// find + By entity의 필드명(멤버 변수명)
	// SELECT * FROM dept WHERE loc =?
	List<Dept> findByLoc(String loc);

	// SELECT * FROM dept LIMIT 3;
	List<Dept> findTop3By(); // top3만 가져오기

	// SELECT * FROM dept WEHRE deptno > ?
	List<Dept> findByDeptnoGreaterThan(int deptno);

	// SELECT * FROM dept where loc LIKE ?
	List<Dept> findByLocContaining(String loc);
	
	//	SELECT * FROM dept WHERE deptno IN (?, ?)
	List<Dept> findByDeptnoIn(List<Integer> deptno);
	
//	List<Dept> findBy(Limit limit);

	// 2) Native Query, JPQL
	// @Query(nativeQuery = true)를 명시 해야한다.
	/*
	 * 2-1) Native Query
	 * 	- 실행 속도 빠름
	 * 	- SQL 문법 그대로 활용
	 * 	- 직관적이다
	 * 	- DB종류에 의존적이다
	 * 	- 안정성이 떨어진다
	 * 
	 * 2-2) JPQL
	 * 	- 유지보수에 유용하다
	 * 	- 반환 타입 지정 -> 컴파일시 오류 발견 가능
	 * 	- 문법이 까다롭다 
	 */
	@Query(value = "SELECT * FROM dept LIMIT :offset, :cnt", nativeQuery = true)
	List<Dept> findBy(int offset, int cnt);

}
