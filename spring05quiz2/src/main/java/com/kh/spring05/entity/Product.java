package com.kh.spring05.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //setter getter toString
@NoArgsConstructor

@AllArgsConstructor
@Builder//빌더 패턴을 사용할 수있또록 내부 클래스를 구축
public class Product {

	private int no;
	private String name;
	private int price;
	private Date reg;
	
}
