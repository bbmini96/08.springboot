package com.spring.persistence.dept.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.spring.persistence.dept.entity.Dept;
import com.spring.persistence.dept.entity.QDept;

@Repository
public class DeptQuerydslRepository extends QuerydslRepositorySupport {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	QDept qDept = QDept.dept;
	public DeptQuerydslRepository() {
		super(Dept.class);
	}
	
	// JPAQueryFactory 주입 ?? 
	public List<Dept> findAllDSL() {
		QDept qDept = QDept.dept;
		return jpaQueryFactory
						.selectFrom(qDept)
						.fetch();
	}
	
	public Dept findDeptnoDSL(int deptno) {
		return (Dept) jpaQueryFactory.selectFrom(qDept)
							.where(qDept.deptno.eq(deptno))
							.fetch();
	}
	
	public void updateDeptDSL(Dept dept) {
		QDept qDept = QDept.dept;
		jpaQueryFactory.update(qDept)
						.set(qDept.deptno, dept.getDeptno())
						.set(qDept.dname, dept.getDname())
						.set(qDept.loc, dept.getLoc())
						.execute();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
