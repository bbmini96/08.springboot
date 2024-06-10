package com.spring.mvc.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.mvc.model.Developer;

import jakarta.servlet.http.HttpServletRequest;

@Controller		// bean이자 servlet으로 연결
public class MVCController {
	
	// http://localhost:8080/test1
	@GetMapping("/test1")	// 서버 연결
	public void test1() {
		System.out.println("MVCController: test1()");
	}
//	@PostMapping("/test1")	// 서버 연결
//	public void test11() {
//		System.out.println("MVCController: test1(): post");
//	}
	
	// http://localhost:8080/test2?id=dev
	@GetMapping("/test2")
	public void test2(HttpServletRequest request) {
		System.out.println("MVCController: test2()");
		String id = request.getParameter("id");
		System.out.println("id: " + id);
	}
	
	// http://localhost:8080/test3?id=dev
	@GetMapping("/test3")
	public void test3(@RequestParam("id") String idValue) { 	// @RequestParam 요청해준 key값을 받아준다(key값이 다를때 사용할 수 있음)
		System.out.println("MVCController: test3()");
		System.out.println("id: " + idValue);
	}
	
	// http://localhost:8080/test4?id=dev&no=1001	---> Developer 객체
	/*
	 * @ModelAttribute
	 * 1) get - Query Params (O)
	 * 2) get - form-data (O), urlencoded (X)
	 * 3) post - form-data (O), urlencoded (O)
	 * 
	 */
	@GetMapping("/test4")
	public void test4(@ModelAttribute Developer dev) { 		//  객체를 맵핑
		System.out.println("MVCController: test4()");
		System.out.println(dev);
		
	}
	
	// http://localhost:8080/test5	=> JSON으로 연결하기(@RequestBoby)
	@PostMapping("/test5")
	public void test5(@RequestBody Developer dev) { 		//  객체를 맵핑
		System.out.println("MVCController: test5()");
		System.out.println(dev);
		
	}
	
	// @Pathvariable
	// http://localhost:8080/test6	
	@GetMapping("/test6/dev/{no}/project/{pid}")
	public void test6(@PathVariable int no, @PathVariable int pid) { 		
		System.out.println("MVCController: test6()");
		System.out.println(no);
		System.out.println(pid);
	}
	
	// http://localhost:8080/test7
//	@GetMapping("/test7")
//	public void test7(@RequestHeader("user-data") String userData) {		
//		System.out.println("MVCController: test7()");
//		System.out.println(userData);
//	}
	@GetMapping("/test7")
	public void test7(@RequestHeader Map<String,String> headers) {	// key값을 모를땐 map으로 받을 수 있다 		
		System.out.println("MVCController: test7()");
//		System.out.println(headers);
		
		// 반복문 출력
		for(Map.Entry<String, String> entry : headers.entrySet()) {
			System.out.println(entry.getKey() + ":  " + entry.getValue());
		}

		
	}
	
	
	
	// http://localhost:8080/test8	
	@GetMapping(value = "/test8", consumes = {MediaType.APPLICATION_JSON_VALUE,
											MediaType.APPLICATION_FORM_URLENCODED_VALUE} )	// consumes: 지정된 타입으로만 메소드가 실행될 수 있도록 설정
	public void test8() { 		
		System.out.println("MVCController: test8()");
		
	}
	
	
	// http://localhost:8080/test9	 => test.jsp로 이동
	@GetMapping(value = "/test9")	
	public String test9() { 		
		System.out.println("MVCController: test9()");
		
		return "test";
	}
	
	
}
