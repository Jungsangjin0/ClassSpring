package com.kh.springDI.human;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class Human {
	@Value("국윤창")
	private String name;
	@Value("36")
	private int age;
	
	@Value("남자")
	private String gender;
	
	//필드 변수에 주입하는 방법
//	@Autowired
//	@Qualifier("wizard")
//	private Job job;

	//생성자를 통한 주입
//	@Autowired
	//생성자 인자가 하나일 때는 autowired생략이 가능하다
//	public Human(@Qualifier("wizard")Job job) {
//		super();
//		this.job = job;
//	}
	
	
}
