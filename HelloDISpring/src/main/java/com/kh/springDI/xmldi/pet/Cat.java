package com.kh.springDI.xmldi.pet;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component("cat")
public class Cat implements Pet {

	private String name;
	
	private int age;
	
	private String gender;
	
	@Override
	public String bark() {
		return "미야옹";
	}

}
