package com.spring.di.test;

import com.spring.di.model.Car;
import com.spring.di.model.Employee;



public class DITest {

	public static void main(String[] args) {
		
		Car car1 = Car.builder()
						.no(1)
						.brand("car1")
						.price(1000)
						.build();
		
		System.out.println(car1);
		
		Employee emp1 = Employee.builder()
								.no(1001)
								.name("emp1")
								.car(car1)
								.build();
		
		System.out.println(emp1);
		
							

	}

}
