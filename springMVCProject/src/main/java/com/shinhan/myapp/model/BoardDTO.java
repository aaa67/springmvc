package com.shinhan.myapp.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String pic;
	private Date create_date;
	}
