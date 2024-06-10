package com.spring.di;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.di.model.Car;
import com.spring.di.model.Employee;

@SpringBootTest
class Step01DiApplicationTests {
	
//	private Car car;
	
	@Autowired	// 필드를 통한 의존성 주입
	private Employee emp;
	
//	@Autowired		// 생성자 의존성 주입
//	public Step01DiApplicationTests(Car car) {
//		this.car = car;
//	}
	
//	@Autowired		
//	public Step01DiApplicationTests(Employee emp) {
//		this.emp = emp;
//	}

//	@Test
//	void contextLoads() {
//	}
	
	@Test
	public void testDI() {
		System.out.println("--testDI()--");
//		System.out.println(car);
		System.out.println(emp.getCar().getNo());
	}
}
