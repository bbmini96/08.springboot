package com.spring.persistence.dept.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import com.spring.persistence.dept.entity.Dept;
import com.spring.persistence.dept.repository.DeptRepository;

@Service
public class DeptService {

	private final DeptRepository deptRepository;

	public DeptService(DeptRepository deptRepository) {
		this.deptRepository = deptRepository;
	}

	public List<Dept> getDeptList() {
		return deptRepository.findAll();
	}

	public Dept getDeptByDeptno(int deptno) {
		return deptRepository.findById(deptno).orElseThrow(() -> new NoSuchElementException("해당 부서 존재 x")); // 에러가 있으면
		// 받아준다
	}

	public int insertDept(Dept dept) {
		return deptRepository.save(dept).getDeptno();
	}

	public void deleteDeptByDeptno(int deptno) {
		deptRepository.findById(deptno).orElseThrow(() -> new NoSuchElementException("해당 부서 존재 x"));

	}

	public void updateDept(Dept dept) {
		Dept exDept = deptRepository.findById(dept.getDeptno()).orElseThrow(() -> new NoSuchElementException("없는 부서"));

		exDept.setLoc(dept.getLoc());
	}

	public List<Dept> getDeptListByLoc(String loc) {
		return deptRepository.findByLoc(loc);

	}

	public List<Dept> getDeptListTop3() {
		return deptRepository.findTop3By(); // Deptno의 크기가 가장 큰 숫자부터 3개
	}

	public List<Dept> getDeptListDeptnoAfter(int deptno) {
		return deptRepository.findByDeptnoGreaterThan(deptno);
	}

	public List<Dept> getDeptListLikeLoc(String loc) {
		return deptRepository.findByLocContaining(loc);
	}


	public List<Dept> getDeptListByDeptnoList(List<Integer> deptnoList) {
		return deptRepository.findByDeptnoIn(deptnoList);
	}


//	
//	public List<Dept> getDeptListUsingLimit(int cnt) {
//		return deptRepository.findBy(cnt);
//	}
	
	public List<Dept> getDeptListLimit(int offset, int cnt) {
		return deptRepository.findBy(offset, cnt);
	}
}
