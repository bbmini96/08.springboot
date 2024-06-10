package com.spring.di.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Component
public class Employee {
	
	private int no;
	private String name;
	
//	@Autowired		// 필드 객체 선언을 통한 의존성 주입
//	private Car car;
	private final Car car;
	
//	public Employee() {		// final을 했을때 기본 생성자를 사용할 수 없다
//		System.out.println("Employee 생성자 ");
//	}
	
	@Autowired
	public Employee(Car car) {
		this.car = car;
	}
	
	@Builder
	public Employee(int no, String name, Car car) {
		System.out.println("Employee 사용자 정의 생성자 ");
		this.no = no;
		this.name = name;
		this.car = car;
	}
	
//	@Autowired	// setter 메서드를 통한 의존성 주입
//	public void setCar(Car car) {
//		this.car = car;
//	}
	
	
	
	
}
