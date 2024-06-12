package com.spring.persistence.dept.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.persistence.dept.entity.Dept;
import com.spring.persistence.dept.repository.DeptQuerydslRepository;
import com.spring.persistence.dept.service.DeptService;

@RestController()
public class DeptAPIController {

	private final DeptService deptService;
	
	@Autowired
	private DeptQuerydslRepository dslDeptRepository;

	public DeptAPIController(DeptService deptService) {
		this.deptService = deptService;
	}

	// http://localhost:8080/api/depts
	@GetMapping("/api/depts")
	public List<Dept> getDeptList() {
//		return deptService.getDeptList();
		return dslDeptRepository.findAllDSL();
	}

	// http://localhost:8080/api/depts/{deptno}
	@GetMapping("/api/depts/deptno/{deptno}")
	public Dept getDeptByDeptno(@PathVariable int deptno) { // deptno로 부터 받아온다 By
//		return deptService.getDeptByDeptno(deptno);
		return dslDeptRepository.findDeptnoDSL(deptno);
	}

	// insert @PostMapping - ? ? ? -> ->
	@PostMapping("/api/depts")
	public int insertDept(@RequestBody Dept dept) {
		return deptService.insertDept(dept);
	}

	// delete @DeleteMapping -> deptno
	@DeleteMapping("/api/depts/{deptno}")
	public void deleteDeptByDeptno(@PathVariable int deptno) {
		deptService.deleteDeptByDeptno(deptno);
	}

	// update @PutMapping ->
	@PutMapping("/api/depts")
	public void updateDept(@RequestBody Dept dept) {
//		deptService.updateDept(dept);
		dslDeptRepository.updateDeptDSL(dept);
	}

	@GetMapping("/api/depts/loc/{loc}")
	public List<Dept> getDeptListByLoc(@PathVariable String loc) {
		return deptService.getDeptListByLoc(loc);
	}

	@GetMapping("/api/depts/top3")
	public List<Dept> getDeptListTop3() {
		return deptService.getDeptListTop3();
	}

	@GetMapping("/api/depts/after/{deptno}")
	public List<Dept> getDeptListDeptnoAfter(@PathVariable int deptno) {
		return deptService.getDeptListDeptnoAfter(deptno);
	}

	@GetMapping("/api/depts/like/{loc}")
	public List<Dept> getDeptListLikeLoc(@PathVariable String loc) {
		return deptService.getDeptListLikeLoc(loc);
	}

	@GetMapping("/api/depts/in")
	public List<Dept> getDeptListByDeptnoList() {
		List<Integer> deptnoList = List.of(40,50,70);
		return deptService.getDeptListByDeptnoList(deptnoList);
	}
	
//	@GetMapping("/api/depts/limit/{cnt}")
//	public List<Dept> getDeptListUsingLimit(@PathVariable int cnt) {
//		return deptService.getDeptListUsingLimit(cnt);
//	}
	
	@GetMapping("/api/depts/{offset}/limit/{cnt}")
	public List<Dept> getDeptListLimit(@PathVariable int offset, @PathVariable int cnt) {
		return deptService.getDeptListLimit(offset, cnt);
	}
}
