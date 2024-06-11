package com.spring.persistence.dept.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@Setter
@EqualsAndHashCode
@Entity
public class Dept {
	@Id
	@Column(name = "deptno")
	private int deptno;

	@Column(name = "dname")
	private String dname;

	@Column(name = "loc")
	private String loc;

	@Builder
	public Dept(int deptno, String dname, String loc) {
		this.deptno = deptno;
		this.dname = dname;
		this.loc = loc;
	}

}
