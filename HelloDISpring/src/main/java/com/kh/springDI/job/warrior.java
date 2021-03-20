package com.kh.springDI.job;

import org.springframework.stereotype.Component;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component("warrior")
public class warrior implements Job {
	
	
	@Override
	public String work() {
		return "용 잡으러가~";
	}

}
