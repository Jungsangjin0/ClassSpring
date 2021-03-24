package com.kh.spring05.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class Product {

	private int no;
	private String name;
	private int price;
	private Date reg;
	
}
