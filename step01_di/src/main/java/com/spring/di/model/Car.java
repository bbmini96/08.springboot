package com.spring.di.model;

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
public class Car {
	
	private int no;
	private String brand;
	private int price;

	public Car() {
		System.out.println("Car 기본 생성자");
	}
	
	@Builder
	public Car(int no, String brand, int price) {
		System.out.println("Car 사용자 정의 생성자");
		this.no = no;
		this.brand = brand;
		this.price = price;
	}
	
}
