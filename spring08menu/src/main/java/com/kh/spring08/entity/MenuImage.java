package com.kh.spring08.entity;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MenuImage { // table menu_image
	private int file_no;
	private String file_name;
	private long file_size;
	private String file_type;
	private Date file_time;
	private int menu_no;
}
