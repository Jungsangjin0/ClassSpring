package com.kh.springDI.job;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component("wizard")
public class wizard implements Job {
	
	@Override
	public String work() {
		return "대지의 기원~!";
	}

}
